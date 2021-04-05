DROP TABLE IF EXISTS my_menu;
DROP TABLE IF EXISTS office_stock;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_option;
DROP TABLE IF EXISTS additional_option;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS product_type;
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
    office_id   INT             NOT NULL AUTO_INCREMENT,
    office_name VARCHAR(50)     NOT NULL,
    address     VARCHAR(255)    NOT NULL,
    latitude    DECIMAL(16, 14) NOT NULL,
    longitude   DECIMAL(17, 14) NOT NULL,

    PRIMARY KEY (office_id)
);

CREATE TABLE manager
(
    manager_id BIGINT       NOT NULL AUTO_INCREMENT,
    office_id  INT          NOT NULL,
    name       VARCHAR(20)  NOT NULL,
    passwd     VARCHAR(255) NOT NULL,
    role       VARCHAR(10)  NOT NULL,

    PRIMARY KEY (manager_id),
    FOREIGN KEY (office_id) REFERENCES office (office_id)
);

CREATE TABLE product_category
(
    category_id   INT         NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(20) NOT NULL UNIQUE,
    category_type VARCHAR(10) NOT NULL,

    PRIMARY KEY (category_id)
);

CREATE TABLE additional_option
(
    option_id    INT         NOT NULL AUTO_INCREMENT,
    option_name  VARCHAR(10) NOT NULL,
    option_price INT         NOT NULL,
    max_quantity INT         NOT NULL,

    PRIMARY KEY (option_id)
);

CREATE TABLE product_option
(
    product_option_id BIGINT NOT NULL AUTO_INCREMENT,
    product_id        BIGINT NOT NULL,
    option_id         INT    NOT NULL,
    quantity          INT    NOT NULL,

    PRIMARY KEY (product_option_id),
    FOREIGN KEY (option_id) REFERENCES additional_option (option_id)
);

CREATE TABLE product
(
    product_id   BIGINT       NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(20)  NOT NULL,
    category_id  INT          NOT NULL,
    price        INT          NOT NULL,
    option_id    BIGINT       NOT NULL,
    description  VARCHAR(255) NOT NULL,
    image        VARCHAR(255) NOT NULL,
    is_active    VARCHAR(1)   NOT NULL,
    created_at   DATE,

    PRIMARY KEY (product_id),
    FOREIGN KEY (category_id) REFERENCES product_category (category_id),
    FOREIGN KEY (product_id) REFERENCES product_option (product_option_id)
);

CREATE TABLE office_stock
(
    stock_id    BIGINT NOT NULL AUTO_INCREMENT,
    office_id   INT    NOT NULL,
    product_id  BIGINT NOT NULL,
    stock_value INT    NOT NULL,

    PRIMARY KEY (stock_id),
    FOREIGN KEY (office_id) REFERENCES office (office_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE my_menu
(
    my_menu_id   BIGINT      NOT NULL AUTO_INCREMENT,
    my_menu_name VARCHAR(10) NOT NULL,
    product_id   BIGINT      NOT NULL,
    member_id    BIGINT      NOT NULL,
    option_info  JSON        NOT NULL,

    PRIMARY KEY (my_menu_id),
    FOREIGN KEY (member_id) REFERENCES members (member_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);