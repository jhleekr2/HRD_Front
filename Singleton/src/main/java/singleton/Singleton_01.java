package singleton;

//싱글톤 패턴(Singleton Pattern)
//- 특정 객체를 여러 번 생성하려고 시도할 때 매번 새로운 객체를 생성하지 않고
//하나의 인스턴스(객체)가 유지되도록 만들어진 패턴
//- 객체를 한 번만 생성하고 다음부터는 만들어놓은 객체를 그대로 사용한다
//- 객체를 생성(new)할 때 필요한 자원의 요구 수준이 높을 때
//- 객체를 프로그램에서 지속적, 반복적으로 사용할 때
//- 객체가 필요할 때마다 매번 생성하지 않고 한번 만들어둔 객체를 계속해서
//사용하기 위한 패턴이다

//싱글톤 패턴의 단점
//1.인스턴스를 생성할 때 추가 작업을 수행할 수 없다
//추가 작업 예시 : try catch구문
//-> 예외 처리 코드를 작성할 수 없다

//2. 인스턴스를 사용하지 않아도 미리 객체를 생성해놓는다
//-> Lazy Initialize 이용
//-> 객체 생성 시점을 최대한 뒤로 미뤄야한다

public class Singleton_01 {

	//데이터
	public String data = "Apple";
	
	//기본 생성자 - 외부생성 코드를 차단
	public Singleton_01() {}
	
	//자신 클래스에 대한 객체 생성
	// -> 프로그램이 실행될 때 딱 한번만 객체를 생성한다
	private static Singleton_01 instance = new Singleton_01();
	
	//싱글톤이 적용된 객체(instance변수)를 반환하는 getter메소드
	// -> 싱글톤 객체를 읽기 전용으로 전달한다
	public static Singleton_01 getInstance() {
		return instance;
	}
}
