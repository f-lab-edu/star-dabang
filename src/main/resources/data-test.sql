INSERT INTO office(office_name, address, location)
VALUES ('본사', '제주도 제주시 돌하르방 12-34', 'POINT(127.232323323 37.3737372323)');

INSERT INTO manager(office_id, name, passwd, role)
VALUES (1, 'root', HASH('SHA256', STRINGTOUTF8('root'), 256), 'ADMIN');
