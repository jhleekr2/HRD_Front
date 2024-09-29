package dao.face;

import java.util.List;

import dto.User;

public interface UserDao {

	// User 테이블에 대한 정보는 dto.User 클래스에 따로 저장
	// (실제로는 userTest테이블이다)
	
	/**
	 * 전체 행을 조회한다
	 * @return List<User> - 테이블 전체 행을 조회한 결과 리스트 객체 
	 */
	
	public List<User> selectAll();
	
	/**
	 * 새로운 정보를 삽입한다
	 * @param data - 새로 삽입할 데이터
	 * @return 영향받은 행 수
	 */
	
	public int insertUser(User data);
	
	/**
	 * 특정 ID번호를 조회한다
	 * @param idx - 조회할 ID 번호
	 * @return 조회된 결과
	 */
	
	public User selectByIdx(int idx);
	
	/**
	 * 입력된 ID번호에 해당하는 행을 삭제한다
	 * @param idx - 삭제할 ID 번호
	 */
	
	public void deleteByIdx(int idx);

}
