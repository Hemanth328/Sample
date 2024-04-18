package pbs.feop.service;

import pbs.feop.request.LoginRequest;
import pbs.feop.request.SignUpForm;
import pbs.feop.request.UnlockAccount;

// ILogin => UserService
public interface ILogin {

	public String loginUser(LoginRequest login);
	
	public boolean signUp(SignUpForm signUpForm); // instead of UserDetails they have taken SignUpForm class
	
	public boolean unlockAccount(UnlockAccount unlockAccount);
	
	public String forgetPassword(String mail);
	
}
