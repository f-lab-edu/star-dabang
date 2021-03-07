DROP TABLE IF EXISTS food_my_menu;
DROP TABLE IF EXISTS drink_my_menu;
DROP TABLE IF EXISTS drink_option;
DROP TABLE IF EXISTS additional_option;
DROP TABLE IF EXISTS drink;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS manager;
DROP TABLE IF EXISTS office;
DROP TABLE IF EXISTS members;

CREATE TABLE members
(
    member_id BIGINT       NOT NULL AUTO_INCREMENT,
    passwd    VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL UNIQUE,
    phone     VARCHAR(25)  NOT NULL,
    nickname  VARCHAR(125) NOT NULL,
    birth     VARCHAR(10)  NOT NULL,

    PRIMARY KEY (member_id),
    UNIQUE INDEX IDX_MEMBERS (email)
);

CREATE TABLE office
(
    office_id   INT          NOT NULL AUTO_INCREMENT,
    office_name VARCHAR(50)  NOT NULL,
    address     VARCHAR(255) NOT NULL,
    location    POINT        NOT NULL,

    PRIMARY KEY (office_id)
);

CREATE TABLE manager
(
    manager_id   BIGINT       NOT NULL AUTO_INCREMENT,
    office_id    INT          NOT NULL,
    manager_name VARCHAR(50)  NOT NULL,
    passwd       VARCHAR(255) NOT NULL,
    rule         VARCHAR(1)   NOT NULL,

    PRIMARY KEY (manager_id),
    FOREIGN KEY (office_id) REFERENCES office (office_id)
);

CREATE TABLE category
(
    category_id   BIGINT      NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(10) NOT NULL,

    PRIMARY KEY (category_id)
);

CREATE TABLE drink
(
    drink_id    BIGINT       NOT NULL AUTO_INCREMENT,
    category_id BIGINT       NOT NULL,
    option_id   BIGINT       NOT NULL,
    drink_name  VARCHAR(50)  NOT NULL,
    price       INT          NOT NULL,
    description VARCHAR(255) NOT NULL,
    image       VARCHAR(255) NOT NULL,
    active      VARCHAR(1)   NOT NULL,
    created_at  DATE,

    PRIMARY KEY (drink_id),
    FOREIGN KEY (category_id) REFERENCES category (category_id)
);

CREATE TABLE additional_option
(
    additional_option_id    BIGINT NOT NULL AUTO_INCREMENT,
    additional_option_name  BIGINT NOT NULL,
    additional_option_limit BIGINT NOT NULL,
    price                   INT    NOT NULL,

    PRIMARY KEY (additional_option_id)
);

CREATE TABLE drink_option
(
    drink_option_id      BIGINT     NOT NULL AUTO_INCREMENT,
    additional_option_id BIGINT     NOT NULL,
    drink_id             BIGINT     NOT NULL,
    option_value         VARCHAR(1) NOT NULL DEFAULT '0',

    PRIMARY KEY (drink_option_id),
    FOREIGN KEY (additional_option_id) REFERENCES additional_option (additional_option_id),
    FOREIGN KEY (drink_id) REFERENCES drink (drink_id)
);

CREATE TABLE food
(
    food_id     BIGINT       NOT NULL AUTO_INCREMENT,
    food_name   VARCHAR(50)  NOT NULL,
    category_id BIGINT       NOT NULL,
    description VARCHAR(255) NOT NULL,
    price       INT          NOT NULL,
    heat        VARCHAR(1)   NOT NULL,
    image       VARCHAR(255) NOT NULL,
    created_at  DATE         NOT NULL,

    PRIMARY KEY (food_id),
    FOREIGN KEY (category_id) REFERENCES category (category_id)
);

CREATE TABLE drink_my_menu
(
    my_menu_id   BIGINT      NOT NULL AUTO_INCREMENT,
    my_menu_name VARCHAR(10) NOT NULL,
    drink_id     BIGINT      NOT NULL,
    member_id    BIGINT      NOT NULL,
    option_info  JSON        NOT NULL,

    PRIMARY KEY (my_menu_id),
    FOREIGN KEY (member_id) REFERENCES members (member_id),
    FOREIGN KEY (drink_id) REFERENCES drink (drink_id)
);

CREATE TABLE food_my_menu
(
    my_menu_id   BIGINT      NOT NULL AUTO_INCREMENT,
    my_menu_name VARCHAR(10) NOT NULL,
    food_id      BIGINT      NOT NULL,
    member_id    BIGINT      NOT NULL,

    PRIMARY KEY (my_menu_id),
    FOREIGN KEY (member_id) REFERENCES members (member_id),
    FOREIGN KEY (food_id) REFERENCES food (food_id)
);
