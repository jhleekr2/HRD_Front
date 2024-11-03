package mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {

	//SqlSession 객체를 생성하기 위한 팩토리 객체
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		
		//마이바티스 Configuration XML 파일의 경로
		String res = "mybatis/mybatis-config.xml";
		
		//XML 파일을 프로그램으로 가져옴 -> 입력 스트림 필요!
		try {
			// Configuration XML 파일을 이용하여 문자 입력 스트림으로 변환
			Reader reader = Resources.getResourceAsReader(res);
			// 여기서 반드시 org.apache.ibatis.io.Resources를 import 해야 한다
			// 안그러면 에러 난다!

			// 싱글톤 유지
			if (sqlSessionFactory == null) {

				// 입력스트림으로 읽어들인 설정 파일을 이용하여
				// SqlSessionFactory 객체 생성
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
				// DB와 Mapper의 위치들이 전부 등록되어 있다
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//private 생성자
	private MyBatisConnectionFactory() {}
	
	//싱글톤 객체를 반환하는 메소드
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
