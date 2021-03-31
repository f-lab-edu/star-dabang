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

INSERT INTO office(office_name, address, latitude, longitude)
VALUES ('본사', '제주도 제주시 돌하르방 12-34', '37.3737372323', '127.232323323');
INSERT INTO office(office_name, address, latitude, longitude)
VALUES ('신논현점', '서울시 서초구 강남대로 465', '37.5038887717', '127.024111957');

INSERT INTO manager(office_id, name, passwd, role)
VALUES (1, 'root', SHA2('root', 256), 'ADMIN');