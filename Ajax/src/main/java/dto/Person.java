package dto;

public class Person {

	private int no;
	private String name;
	private String addr;
	
	public Person() {}

	public Person(int no, String name, String addr) {
		super();
		this.no = no;
		this.name = name;
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Person [no=" + no + ", name=" + name + ", addr=" + addr + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
