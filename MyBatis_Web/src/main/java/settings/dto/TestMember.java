package settings.dto;

import org.apache.ibatis.type.Alias;

@Alias("TM")
public class TestMember {
//Mybatis에서 개별적으로 별명 지정하기 위해 직접 DTO에 가서 annotation으로 지정한다
	private int no;
	private String id;
	private String pw;
	
	public TestMember() {}

	public TestMember(int no, String id, String pw) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "TestMember [no=" + no + ", id=" + id + ", pw=" + pw + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
}
