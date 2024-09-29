package dto;

public class User {

	//UserTest 테이블의 각 컬럼을 멤버변수로 지정
	private int idx;
	private String userid;
	private String name;
	
	//테이블의 컬럼을 캡슐화함 - DTO
	public User() {}

	//모든 멤버필드 초기화 생성자 - alt + shift + s, o
	public User(int idx, String userid, String name) {
		super();
		this.idx = idx;
		this.userid = userid;
		this.name = name;
	}

	//toString() 메소드 재정의 - alt + shift + s, s
	@Override
	public String toString() {
		return "User [idx=" + idx + ", userid=" + userid + ", name=" + name + "]";
	}

	//getter, setter 메소드 - alt + shift + s, r, alt + a, alt + r
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
