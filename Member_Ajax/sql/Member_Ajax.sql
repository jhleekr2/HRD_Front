DROP TABLE MEMBER;

CREATE TABLE MEMBER (
	member_no NUMBER PRIMARY key
	,member_id VARCHAR2(20) NOT null
	,member_pw VARCHAR2(20) NOT null
	,member_nick VARCHAR2(30)
	,member_phone VARCHAR2(20)
	,member_postcode VARCHAR2(50)
	,member_addr1 VARCHAR2(50)
	,member_addr2 VARCHAR2(50)
	,insert_dat DATE DEFAULT sysdate NOT NULL
);
-- 강사 풀이
DROP TABLE member;
CREATE TABLE member (
	member_no NUMBER
	,member_id VARCHAR2(500) NOT null
	,member_pw VARCHAR2(500) NOT null
	,member_nick VARCHAR2(500)
	,member_phone VARCHAR2(100)
	,member_postcode VARCHAR2(20)
	,member_addr1 VARCHAR2(1000)
	,member_addr2 VARCHAR2(1000)
	,insert_dat DATE DEFAULT sysdate not null
);

ALTER TABLE member
DROP CONSTRAINT pk_member;

ALTER TABLE member
ADD CONSTRAINT pk_member PRIMARY KEY (member_no);

DROP SEQUENCE member_seq;
CREATE SEQUENCE member_seq;
