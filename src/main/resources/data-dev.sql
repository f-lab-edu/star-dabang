INSERT INTO member(email, passwd, nickName, phone, birth)
VALUES ('test1@naver.com', SHA2('12345', 256), '테스트', '01012345678', '20201010');
INSERT INTO member(email, passwd, nickName, phone, birth)
VALUES ('test2@naver.com', SHA2('12345', 256), '테스트', '01012345678', '20201010');
INSERT INTO member(email, passwd, nickName, phone, birth)
VALUES ('test3@naver.com', SHA2('12345', 256), '테스트', '01012345678', '20201010');
INSERT INTO member(email, passwd, nickName, phone, birth)
VALUES ('test4@naver.com', SHA2('12345', 256), '테스트', '01012345678', '20201010');
INSERT INTO member(email, passwd, nickName, phone, birth)
VALUES ('test5@naver.com', SHA2('12345', 256), '테스트', '01012345678', '20201010');

INSERT INTO office(office_name, address, latitude, longitude)
VALUES ('본사', '제주도 제주시 돌하르방 12-34', '37.3737372323', '127.232323323');
INSERT INTO office(office_name, address, latitude, longitude)
VALUES ('신논현점', '서울시 서초구 강남대로 465', '37.5038887717', '127.024111957');

INSERT INTO manager(office_id, name, passwd, role)
VALUES (1, 'root', SHA2('root', 256), 'ADMIN');

INSERT INTO additional_option(option_name, option_price, max_quantity)
VALUES ('옵션1', 100, 10);
INSERT INTO additional_option(option_name, option_price, max_quantity)
VALUES ('옵션2', 100, 10);
INSERT INTO additional_option(option_name, option_price, max_quantity)
VALUES ('옵션3', 100, 10);
INSERT INTO additional_option(option_name, option_price, max_quantity)
VALUES ('옵션4', 100, 10);
INSERT INTO additional_option(option_name, option_price, max_quantity)
VALUES ('옵션5', 100, 10);

INSERT INTO product_category(category_name, category_type)
VALUES ('라떼1', 'DRINK');
INSERT INTO product_category(category_name, category_type)
VALUES ('라떼2', 'DRINK');
INSERT INTO product_category(category_name, category_type)
VALUES ('라떼3', 'DRINK');
INSERT INTO product_category(category_name, category_type)
VALUES ('라떼4', 'DRINK');
INSERT INTO product_category(category_name, category_type)
VALUES ('라떼5', 'DRINK');

INSERT INTO product(product_name, category_id, price, description, image, is_active, created_at)
VALUES ('아메리카노', 1, 9000, '제품 설명', 'image_url', 1, '2020-10-10');
INSERT INTO product(product_name, category_id, price, description, image, is_active, created_at)
VALUES ('녹차라떼', 2, 8000, '제품 설명', 'image_url', 1, '2020-10-10');
INSERT INTO product(product_name, category_id, price, description, image, is_active, created_at)
VALUES ('민트초코라떼', 3, 1000, '제품 설명', 'image_url', 1, '2020-10-10');
INSERT INTO product(product_name, category_id, price, description, image, is_active, created_at)
VALUES ('딸기우유', 4, 150000, '제품 설명', 'image_url', 1, '2020-10-10');
INSERT INTO product(product_name, category_id, price, description, image, is_active, created_at)
VALUES ('초코우유', 5, 200000, '제품 설명', 'image_url', 1, '2020-10-10');

INSERT INTO product_option(product_id, option_id, quantity)
VALUES (1, 2, 3);
INSERT INTO product_option(product_id, option_id, quantity)
VALUES (1, 3, 5);
INSERT INTO product_option(product_id, option_id, quantity)
VALUES (2, 1, 3);
INSERT INTO product_option(product_id, option_id, quantity)
VALUES (2, 4, 4);
INSERT INTO product_option(product_id, option_id, quantity)
VALUES (3, 3, 3);
INSERT INTO product_option(product_id, option_id, quantity)
VALUES (4, 1, 4);