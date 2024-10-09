package singleton;

//싱글톤 패턴의 큰 단점들을 모두 해결한 코드
//앞으로 무거운 JDBC코드를 로드하는 과정에서 계속 사용될 예정

public class Singleton_03 {
	
	//데이터
	public String data = "Apple";
	
	//private 디폴트 생성자 - 외부에서 생성하는 코드를 차단!
	private Singleton_03() {}
	
	//자신 클래스에 대한 객체 선언 - 생성을 안했기 때문에 초기화값은 null
	//사람에 따라서 코드스타일이 다른데, null이라고 명시를 안해주는 사람들도 있다
	private static Singleton_03 instance;
//	private static Singleton_03 instance = null;

	//싱글톤 객체 반환 메소드
	public static Singleton_03 getInstance() {
		
//		int n; //지역변수의 초기값은 쓰레기값
		
		//instance를 new로 객체 생성한적이 없다면
		if( instance == null) {
			
			try {
				//싱글톤 객체 생성
				instance = new Singleton_03();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return instance; // 이때반환되는 값 - null
	}
}
