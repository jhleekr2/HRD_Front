package dto;

public class Uploadfile {
//	FILENO
//	ORIGIN_NAME
//	STORED_NAME
//	DATANO
	
// 자바는 SQL과 달리 대소문자 구분을 하고, 단어사이를 _로 구분하는 것을 싫어하기 때문에
// DTO로 옮기면서 변수명이 달라진다
	
	private int fileno;
	private String originName;
	private String storedName;
	private int datano;

	public Uploadfile() {
	}

	public Uploadfile(int fileno, String originName, String storedName, int datano) {
		super();
		this.fileno = fileno;
		this.originName = originName;
		this.storedName = storedName;
		this.datano = datano;
	}

	@Override
	public String toString() {
		return "uploadfile [fileno=" + fileno + ", originName=" + originName + ", storedName=" + storedName
				+ ", datano=" + datano + "]";
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	public int getDatano() {
		return datano;
	}

	public void setDatano(int datano) {
		this.datano = datano;
	}
	
	
}