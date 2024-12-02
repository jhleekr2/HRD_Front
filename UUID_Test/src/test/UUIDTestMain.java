package test;

import java.util.UUID;

public class UUIDTestMain {

	public static void main(String[] args) {
		
		//UUID, Universal Unique IDentifier
		// 범용 고유 식별자
		
		// 32자리의 16진수형태의 문자열을 생성한다
		
		// 8 - 4 - 4 - 4 - 12 개씩 구분해서 하나의 문자열로 생성한다
		
		//------------------------------------------------------------------

		//랜덤 UUID값 생성
		UUID uuid = UUID.randomUUID();
		System.out.println( uuid);
		
		//전체 UUID중에서 12자리만 추출하기
		String uid = uuid.toString();
		System.out.println( uid.split("-")[4] );
		
		//조합해서 추출하기
		System.out.println( uid.split("-")[2] + uid.split("-")[4] );
		
	}	
}
