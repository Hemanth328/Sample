package pbs.feop.request;

import lombok.Data;

@Data
public class UnlockAccount {

	private String email;
	private String oldPizzword;
	private String newPizzword;
	private String confirmNewPizzword;
}
