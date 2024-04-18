package pbs.feop.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import pbs.feop.constants.AppConstants;
import pbs.feop.entity.UserDetails;
import pbs.feop.repo.UserDetailsRepo;
import pbs.feop.request.LoginRequest;
import pbs.feop.request.SignUpForm;
import pbs.feop.request.UnlockAccount;
import pbs.feop.utility.EmailPizzword;
import pbs.feop.utility.PzdUtility;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements ILogin {
	
	
	private UserDetailsRepo usdRepo;
	
	private EmailPizzword emailPizzword;
	
	private HttpSession httpSession;
	
	@Override
	public boolean signUp(SignUpForm signUpForm) {
		
		UserDetails check = usdRepo.findByUserMail(signUpForm.getUserMail());
		
		if(check!= null)
			return false;		
		
		UserDetails user = new UserDetails();
		
		BeanUtils.copyProperties(signUpForm, user);
		
		// TODO : generate Random Password and set to object
		String tempPazz = PzdUtility.generateRandomPazzwd();
		
		user.setUserPazz(tempPazz);
		
		// TODO : Set account status as Locked
		user.setUserAccStatus(AppConstants.ACC_STATUS);
		
		// TODO : Insert a record
		UserDetails details = usdRepo.save(user);
		
		// TODO : Send a mail to user to unlock the account
		String to = signUpForm.getUserMail();
		String subject = "Please unlock your account, "+signUpForm.getUserName().toUpperCase()+ " Gaming Oraganization Ltd";
		
		StringBuffer body = new StringBuffer();
		body.append("<h1> Please unlock your account by using the temporary password provided below</h1>");
		body.append("Temporary password : "+tempPazz);
		body.append("<br/>");
		body.append("<br/>");
		body.append("<a href= \"http://52.66.245.82:8080/unlock?email="+to+"\">Click here to unlock your account");
		
		boolean result = emailPizzword.senMail(to, subject, body.toString());
		
		
		return result;
	}
	
	
	
	@Override
	public boolean unlockAccount(UnlockAccount unlockAccount) {
		
		System.out.println(unlockAccount);
		
		UserDetails user = usdRepo.findByUserMail(unlockAccount.getEmail());
		
		
		if(user!= null) {
			
			if(unlockAccount.getOldPizzword().equals(user.getUserPazz())) {				
				user.setUserPazz(unlockAccount.getConfirmNewPizzword());
				user.setUserAccStatus(AppConstants.CHANGE_ACC_STATUS);
				
				usdRepo.save(user);
			}
			
			else
				return false;
			
		}		
		else
			return false;
		
		UserDetails check = usdRepo.findByUserMail(unlockAccount.getEmail());
		System.out.println(check);
		
		return true;
	}
	
	
	@Override
	public String loginUser(LoginRequest login) {
		
		UserDetails userdetails = usdRepo.findByUserMailAndUserPazz(login.getUserName(), login.getPizzword());
		
		if(userdetails.getUserMail() != login.getUserName() || userdetails.getUserPazz() != login.getPizzword())
			return "Invalid Credentials";
		
		if(userdetails.getUserAccStatus().equals("LOCKED"))
			return "Your Account has been locked, please unlock your account";
		
		if(userdetails != null) {
			httpSession.setAttribute("userId", userdetails.getUserId());
			return "Successful Login";
		}
		
		return "User Does Not Exists";
	}
	
	
	@Override
	public String forgetPassword(String mail) {
		
		UserDetails details = usdRepo.findByUserMail(mail);
		
		if(details != null) {
			String to = details.getUserMail();
			
			String subject = "Your Password | ABHISRI Gaming Oraganization Ltd";
			
			StringBuffer body = new StringBuffer("");
			
			body.append("<h3> Here is your password, Happy Logging </h3>");
			body.append("<h4> Password : "+details.getUserPazz()+"</h4>");
			body.append("<h4> Don't worry this is a Spring Boot Application generated mail.</h4>");
			
			boolean status = emailPizzword.senMail(to, subject, body.toString());
			
			if(!status)
				return "Techinal Glitch unfolded";
			
			return "Password sent to your mailId";
		}
		
		return "User Not Found";
	}

}
