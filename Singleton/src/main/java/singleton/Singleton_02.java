package singleton;

public class Singleton_02 {
	
	//데이터 - 테스트 쉽게 하기위해 원래 써야하는 private가 아닌 public 사용했음
	public String data = "Apple";
	
	//private 디폴트 생성자 - 외부에서 생성하는 코드를 차단!
	private Singleton_02() {}
	
	//자신 클래스에 대한 객체 선언 - 생성을 안했기 때문에 초기화값은 null
	private static Singleton_02 instance;
	
	//static block, 정적 변수 초기화 블록
	static {
//		try적고 ctrl + space 눌러서 자동완성
		try {
			
			//싱글톤 객체 생성
			// -> 객체를 생성하며 추가 코드를 적을 수 있다
			instance = new Singleton_02();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//싱글톤 객체 반환 메소드
	public static Singleton_02 getInstance() {
		return instance;
	}
}
