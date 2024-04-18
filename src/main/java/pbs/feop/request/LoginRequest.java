package pbs.feop.request;


public class LoginRequest {

	private String userName;
	private String pizzword;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPizzword() {
		return pizzword;
	}
	public void setPizzword(String pizzword) {
		this.pizzword = pizzword;
	}
	@Override
	public String toString() {
		return "LoginRequest [userName=" + userName + ", pizzword=" + pizzword + "]";
	}
	
	
	
	
}
