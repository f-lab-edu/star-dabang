INSERT INTO members(email, passwd, nickName, phone, birth)
VALUES ('test1@naver.com', SHA2('12345', 256), '테스트', '01012345678', '20201010');
INSERT INTO members(email, passwd, nickName, phone, birth)
VALUES ('test2@naver.com', SHA2('12345', 256), '테스트', '01012345678', '20201010');
INSERT INTO members(email, passwd, nickName, phone, birth)
VALUES ('test3@naver.com', SHA2('12345', 256), '테스트', '01012345678', '20201010');
INSERT INTO members(email, passwd, nickName, phone, birth)
VALUES ('test4@naver.com', SHA2('12345', 256), '테스트', '01012345678', '20201010');
INSERT INTO members(email, passwd, nickName, phone, birth)
VALUES ('test5@naver.com', SHA2('12345', 256), '테스트', '01012345678', '20201010');

INSERT INTO office(office_name, address, location_x, location_y)
VALUES ('본사', '제주도 제주시 돌하르방 12-34', '127.232323323', '37.3737372323');

INSERT INTO manager(office_id, name, passwd, rule)
VALUES (1, 'root', SHA2('root', 256), 'ADMIN');