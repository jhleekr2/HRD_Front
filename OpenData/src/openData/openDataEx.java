package openData;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import openData.dto.Air;

public class openDataEx {

	public static void main(String[] args) {
		
		//시도별 실시간 평균자료 조회
		
		//요청 URL
		String url = "http://apis.data.go.kr/B552584/ArpltnStatsSvc/getCtprvnMesureLIst";

		//서비스키 (인코딩된 키) - 개인마다 다르고, 만료 후 재발급받으면 바뀜
		String serviceKey = "serviceKey=개인의 서비스키";
		
		//URL Encoding 적용하기
//		URLEncoder.encode("개인의 인코딩된 서비스키");

		//URL Encoding 풀어내기 ( Decode )
//		URLDecoder.decode("개인의 디코딩된 서비스키");

		//데이터 표출방식
		String returnType = "returnType=json";
	
		//항목명
		String itemCode = "itemCode=PM10";
		
		//자료 구분
		String dataGubun = "dataGubun=HOUR";
		//VO = 읽기전용Data관리객체
		
		//최근들어 전과달리 현업에서 컴퓨터공학과 정석적인 VO/DTO구분을
		//제대로 적용하려는 추세에 있다.
		
		//한 페이지 결과 수
		String numOfRows = "numOfRows=10";
		
		//페이지 번호
		String pageNo = "pageNo=1";
		
		//최종 요청 URL
//		String reqURL = 
//				url
//				+ "?" + serviceKey
//				+ "&" + returnType
//				+ "&" + itemCode
//				+ "&" + dataGubun
//				+ "&" + numOfRows
//				+ "&" + pageNo;
//		System.out.println( reqURL );
		//위의 코드 스타일은 성능상 상당히 좋지 않다
		// -> StringBuilder 사용!
				
		StringBuilder buildURL = new StringBuilder( url );
		
		buildURL.append("?").append(serviceKey);
		buildURL.append("&").append(returnType);
		buildURL.append("&").append(itemCode);
		buildURL.append("&").append(dataGubun);
		buildURL.append("&").append(numOfRows);
		buildURL.append("&").append(pageNo);
		
		System.out.println( buildURL );
		
		//------------------------------------------------------------

		//URL 객체
		// -> URL을 저장하고 연결을 수행할 수 있다
		URL urlInfo = null;
		
		//HTTP 연결 수행 객체
		// -> URL 객체를 통해 연결된 상태를 저장하고 관리할 수 있다
		// -> HTTP 통신을 수행한다
		HttpURLConnection httpConn = null;
		
		try {
			//접속 대상 URL  지정하기
			urlInfo = new URL( buildURL.toString() );
			
			//HTTP 요청(접속) 수행하기
			httpConn = (HttpURLConnection) urlInfo.openConnection();
			
			//http 응답 데이터 입력 스트림
			InputStream in = httpConn.getInputStream();
			
			//공공데이터 응답 데이터를 읽어 들여 StringBuilder에 저장한다
//			while( ( len = in.read(buf)) = -1) {} //좀 저급한 방법
			Scanner sc = new Scanner( in, "UTF-8");
			
			StringBuilder responseData = new StringBuilder();
			while( sc.hasNext() ) {
				responseData.append( sc.next() );
			}
			
			//응답 데이터 출력하기
			System.out.println( responseData );
			
			//------------------------------------------------------------

			//* Gson 라이브러리 API 객체

			//JsonElement	: JSON 데이터를 저장하는 객체
			//				: 자료형에 맞게 바꾸기 전 상태를 저장한다
			//JsonArray
			//JsonObject
			
			//------------------------------------------------------------

			// JsonText 형식으로 응답받은 데이터(responseData)를
			//JsonElement(JSON 객체) 로 변환 (파싱)
			//JsonParser - JS에 있는 JSON.parse()와 같은 기능
			//자체 Java 라이브러리에 없으므로 외부 라이브러리 gson에서 가져왔음
			JsonElement toJson = JsonParser.parseString( responseData.toString() );
			
			//응답 데이터 전체를 JsonObject 타입으로 반환한다
			JsonObject root = toJson.getAsJsonObject();

			//------------------------------------------------------------

//			JSON 응답 데이터 구조 및 타입분석
//			root -> Object
//			  "response" -> Object - 중괄호기 때문
//			     "header" -> Object
//			        "resultMsg" -> String
//			        "resultCode" -> String
//			      "body" -> Object
//			        "totalCount" -> Number
//			        "items" -> Array ( Object ) - 대괄호기 때문
//			        "pageNo" ->Number
//			        "numOfRows" -> Number
			
			//root.response 데이터
			JsonObject response = root.get("response").getAsJsonObject();
			
			//root.response.header
			JsonObject header = response.get("header").getAsJsonObject();
			String resultMsg = header.get("resultMsg").getAsString();
			String resultCode = header.get("resultCode").getAsString();
//				
			//root.response.body
			JsonObject body = response.get("body").getAsJsonObject();
			int totalCount = body.get("totalCount").getAsInt();
			int pageNoData = body.get("pageNo").getAsInt();
			int numOfRowsData = body.get("numOfRows").getAsInt();

			//root.response.body.items
			JsonArray items = body.get("items").getAsJsonArray();
			
			//------------------------------------------------------------

			System.out.println();
			System.out.println("-- items --");
			
			List<Air> airList = new ArrayList<>();

//			new Gson().fromJson(items,  List<?>.class);
				
			for( JsonElement item : items ) {
//				System.out.println( item );
				
//				Air air = new Air();
//				air.setSeoul( item.getAsJsonObject().get("seoul").getAsString() );
//				airList.add(air);
				
				Air air = new Gson().fromJson( item, Air.class);
				airList.add(air);
				
			}
			
			//최종 출력
			for( Air air : airList ) {
				System.out.println( air );
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
