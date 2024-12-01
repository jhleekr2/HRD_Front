-- 텍스트 전달 파라미터 테이블
DROP TABLE paramdata;

CREATE TABLE paramdata (
	datano NUMBER PRIMARY KEY,
	title VARCHAR2(1000),
	data1 VARCHAR2(1000),
	data2 VARCHAR2(1000)
);

DROP SEQUENCE paramdata_seq;
CREATE SEQUENCE paramdata_seq;


-- 파일 전달 파라미터 테이블
DROP TABLE uploadfile;
CREATE TABLE uploadfile (
	fileno NUMBER PRIMARY KEY,
	origin_name VARCHAR2(1000),
	stored_name VARCHAR2(1000),
	datano NUMBER,
	FOREIGN KEY ( datano )
		REFERENCES paramdata ( datano )
);

DROP SEQUENCE uploadfile_seq;
CREATE SEQUENCE uploadfile_seq;

-- 파일 업로드 테스트

SELECT * FROM paramdata;
SELECT * FROM uploadfile;

-- Outer Join 적용
SELECT * FROM paramdata P, uploadfile U
WHERE p.datano = u.datano (+);
