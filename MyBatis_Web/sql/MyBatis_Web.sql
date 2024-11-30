DROP TABLE underscore;
CREATE TABLE underscore (
	user_id VARCHAR2(500),
	user_pw VARCHAR2(500)
);

INSERT INTO underscore ( user_id, user_pw ) VALUES ('apple', 'alice');
INSERT INTO underscore ( user_id, user_pw ) VALUES ('banana', 'bob');
INSERT INTO underscore ( user_id, user_pw ) VALUES ('cherry', 'clare');
COMMIT;

SELECT * FROM underscore;

-------------------------------------------------

DROP TABLE test_member;
CREATE TABLE test_member (
	NO NUMBER PRIMARY KEY,
	id VARCHAR2(500),
	pw VARCHAR2(500)
);

DROP SEQUENCE test_member_seq;
CREATE SEQUENCE test_member_seq;

SELECT * FROM test_member;