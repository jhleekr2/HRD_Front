package dto;

public class ParamUpload {

	//조인된 SQL조회했을때 테이블 자바로 꺼내는 방법
	//두개의 테이블 멤버필드를 모두 일일히 정의해주거나, 한쪽만 객체로 정의하거나
	//둘다 객체로 정의해도 된다
	
	//아니면, 기존 dto에
//	private Uploadfile uploadfile = new Uploadfile(); 이런 식으로 추가 정의해주는
//	dto에 계속 조인을 추가하는 방법도 된다
	
	private Paramdata paramdata = new Paramdata();
	private Uploadfile uploadfile = new Uploadfile();
	//멤버필드가 객체 2개가 들어간다(이미 만들어진 DTO 2개 이용)
	
	//대신 꺼낼때는 pu.getParamdata().getTitle();로 두번 꺼내야 한다.
	
}
