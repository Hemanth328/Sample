package pbs.feop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pbs.feop.request.LoginRequest;
import pbs.feop.request.SignUpForm;
import pbs.feop.request.UnlockAccount;
import pbs.feop.service.ILogin;

// LoginController = UserController
@Controller
public class LoginController {

	@Autowired
	private ILogin login;
	
	@GetMapping("/")
	public String indexPage() {
		
		return "index";
	}
	
	@GetMapping("/signup")
	public String signUpPage(Model model) {
		
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model) {
		
		System.out.println("LoginController.handleSignUp()");
		
		boolean status = login.signUp(form);
		
		if(status) {
			model.addAttribute("succMsg", "Account created successfully, please Check your mail");
		} else {
			model.addAttribute("errMsg", "Given mailId already exists !");
		}
		
		return "signup";
	}
	
	
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		
		UnlockAccount unlockAcc = new UnlockAccount();
		unlockAcc.setEmail(email);
		
		model.addAttribute("unlock", unlockAcc);
		
		return "unlock";
	}
	
	
	@PostMapping("/unlock")
	public String handleUnlockPage(@ModelAttribute UnlockAccount account, Model model) {	
		
		if(account.getNewPizzword().equals(account.getConfirmNewPizzword())) {
			
			boolean status = login.unlockAccount(account);
			
			if(status) {
				model.addAttribute("succMsg", "Account unlocked Successfully !");
				model.addAttribute("unlock", account);
			}
			else {
				model.addAttribute("errMsg", "Entered Wrong Password!");
				model.addAttribute("unlock", account);
			}
			
		}
		else {
			model.addAttribute("errMsg", "NewPassword and Confirm Password Mismatch !");
			model.addAttribute("unlock", account);
		}
		
		
		return "unlock";
	}
	
	
	@GetMapping("/login")
	public String loginPage(Model  model) {
		
		System.out.println("This page has been rendered");
		
		model.addAttribute("loginReq", new LoginRequest());
		
		return "login";
	}
	
	
	@PostMapping("/login")
	public String handleLoginPage(@ModelAttribute("loginReq") LoginRequest loginReq, Model model) {
		
		System.out.println(loginReq);
		
		String result = login.loginUser(loginReq);
		
		if(! result.equals("Successful Login")) {
			model.addAttribute("result", result);
		}
		else {
			return "redirect:/dashboard";
		}
		
		System.out.println("This page has been rendered in Post");
		
		return "login";
	}
	
	@GetMapping("/forgot")
	public String forgetPizzward() {
		
		return "forgotPwd";
	}
	
	@PostMapping("/forgot")
	public String handleforgetpizzward(@RequestParam("email") String mail, Model model) {
		
		String result = login.forgetPassword(mail);
		
		if(!result.equals("Password has been sent to your mailId"))
			model.addAttribute("errMsg", result);
		else
			model.addAttribute("succMsg", result);
		
		return "forgotPwd";
	}
	
}
