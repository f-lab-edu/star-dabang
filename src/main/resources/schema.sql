DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(15)  NOT NULL,
    tel      VARCHAR(11)  NOT NULL,
    birthday DATE         NOT NULL,
    UNIQUE INDEX IDX_USERS (email)
);