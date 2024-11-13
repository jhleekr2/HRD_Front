-- logintb 테이블
--loginno NUMBER PRIMARY KEY
--loginid VARCHAR2(500) NOT NULL
--loginpw VARCHAR2(500) NOT NULL

-- login_seq 시퀀스

-- 위 조건에 맞는 DB풀이
CREATE TABLE logintb (
	loginno NUMBER PRIMARY KEY,
	loginid VARCHAR2(500) NOT NULL,
	loginpw VARCHAR2(500) NOT NULL
);

CREATE SEQUENCE login_seq;

SELECT * FROM logintb;

INSERT INTO logintb ( loginno, loginid, loginpw)
VALUES ( login_seq.nextval, ?, ?);

-- 만일, 꼬여서 초기화해야한다면
DROP SEQUENCE login_seq;
DROP TABLE logintb;
-- 차례로 실행한다

SELECT * FROM logintb
WHERE loginid = #{loginid }
AND loginpw = #{loginpw };

-- 테스트 데이터
INSERT INTO logintb ( loginno, loginid, loginpw)
VALUES ( login_seq.nextval, 'TEST', 'TEST');

INSERT INTO logintb ( loginno, loginid, loginpw)
VALUES ( login_seq.nextval, 'a', 'b');

-- 데이터가 있는지 확인
SELECT * FROM logintb
WHERE loginid = 'a'
AND loginpw = 'b';

-- 더 간단한 처리법 - 하지만 필요한 PK를 알 수 없어서 여기선 사용 불가
SELECT count(*) FROM logintb
WHERE loginid = 'a'
AND loginpw = 'b';