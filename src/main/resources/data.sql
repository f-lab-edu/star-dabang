INSERT INTO members(email, passwd, nickName, phone, birth)
VALUES ('test1@naver.com', '12345', '테스트', '01012345678', '20201010');
INSERT INTO members(email, passwd, nickName, phone, birth)
VALUES ('test2@naver.com', '12345', '테스트', '01012345678', '20201010');
INSERT INTO members(email, passwd, nickName, phone, birth)
VALUES ('test3@naver.com', '12345', '테스트', '01012345678', '20201010');
INSERT INTO members(email, passwd, nickName, phone, birth)
VALUES ('test4@naver.com', '12345', '테스트', '01012345678', '20201010');
INSERT INTO members(email, passwd, nickName, phone, birth)
VALUES ('test5@naver.com', '12345', '테스트', '01012345678', '20201010');

INSERT INTO office(office_name, address, location)
VALUES ('본사', '제주도 제주시 돌하르방 12-34', POINT(127.383838, 37.232323));

INSERT INTO manager(office_id, manager_name, passwd, rule)
VALUES (1, 'root', 'root', '0');