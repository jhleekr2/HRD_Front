package dto;

public class Login {

	private int loginno;
	private String loginid;
	private String loginpw;
	
	public Login() {}

	public Login(int loginno, String loginid, String loginpw) {
		super();
		this.loginno = loginno;
		this.loginid = loginid;
		this.loginpw = loginpw;
	}

	@Override
	public String toString() {
		return "Login [loginno=" + loginno + ", loginid=" + loginid + ", loginpw=" + loginpw + "]";
	}

	public int getLoginno() {
		return loginno;
	}

	public void setLoginno(int loginno) {
		this.loginno = loginno;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getLoginpw() {
		return loginpw;
	}

	public void setLoginpw(String loginpw) {
		this.loginpw = loginpw;
	}
	
	
}
