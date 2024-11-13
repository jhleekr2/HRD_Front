DROP TABLE member;
CREATE TABLE member (
	userno NUMBER primary key,
	userid VARCHAR2(50),
	nick VARCHAR2(100),
	email VARCHAR2(300)
);

DROP SEQUENCE member_seq;
CREATE SEQUENCE member_seq;
