package settings.dto;

public class Underscore {

	private String userId;
	private String userPw;
	
//	private String user_id;
//	private String user_pw;
//	이런 형태의 자바 변수선언은 추천되지 않는다
	public Underscore() {}
	
	public Underscore(String userId, String userPw) {
		super();
		this.userId = userId;
		this.userPw = userPw;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	@Override
	public String toString() {
		return "Underscore [userId=" + userId + ", userPw=" + userPw + "]";
	}
	
}
