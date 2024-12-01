package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Paramdata;
import dto.Uploadfile;

public interface FileService {

	/**
	 * multipart/form-data 인코딩으로 전달된 요청 데이터를 처리한다
	 * 파일 업로드 라이브러리 활용
	 * (Commons-Fileupload)
	 * 
	 * 전달 데이터를 DB에 삽입한다
	 * 
	 * @param req - 요청 정보 객체
	 * @return 파일 업로드 처리 성공(true) / 실패(false)
	 */
	public boolean fileupload(HttpServletRequest req);

	/**
	 * 파일 전체 목록 반환
	 * 
	 * @return 조회된 전체 파일 목록 객체
	 */
	public List<Uploadfile> list();

	/**
	 * datano를 이용하여 정보를 조회한다
	 * 
	 * @param datano - 조회할 datano
	 * @return 조회된 정보 DTO 객체
	 */
	public Paramdata info(Paramdata datano);

	/**
	 * 전달파라미터 datano, title, data1, data2 를 추출한다
	 * 
	 * @param req - 요청 정보 객체
	 * @return 추출한 정보를 저장한 DTO 객체
	 */
	public Paramdata getParamdata(HttpServletRequest req);

	/**
	 * 전달된 데이터를 이용하여 정보를 수정한다
	 * 
	 * @param paramdata - 수정에 활용할 데이터
	 */
	public void edit(Paramdata paramdata);

}
