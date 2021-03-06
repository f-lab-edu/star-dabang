DROP TABLE IF EXISTS order_product;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS my_menu;
DROP TABLE IF EXISTS office_stock;
DROP TABLE IF EXISTS product_option;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS additional_option;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS manager;
DROP TABLE IF EXISTS office;
DROP TABLE IF EXISTS member;

CREATE TABLE member
(
    member_id BIGINT       NOT NULL AUTO_INCREMENT,
    passwd    VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL UNIQUE,
    phone     VARCHAR(25)  NOT NULL,
    nickname  VARCHAR(125) NOT NULL,
    birth     VARCHAR(10)  NOT NULL,
    token     VARCHAR(255),

    PRIMARY KEY (member_id),
    UNIQUE INDEX IDX_MEMBERS (email)
);

CREATE TABLE office
(
    office_id   MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
    office_name VARCHAR(50)        NOT NULL,
    address     VARCHAR(255)       NOT NULL,
    location    GEOMETRY( POINT) NOT NULL,

    PRIMARY KEY (office_id)
);

CREATE TABLE manager
(
    manager_id INT UNSIGNED       NOT NULL AUTO_INCREMENT,
    office_id  MEDIUMINT UNSIGNED NOT NULL,
    name       VARCHAR(20)        NOT NULL,
    passwd     VARCHAR(255)       NOT NULL,
    role       VARCHAR(10)        NOT NULL,
    token      VARCHAR(255),

    PRIMARY KEY (manager_id),
    FOREIGN KEY (office_id) REFERENCES office (office_id)
);

CREATE TABLE product_category
(
    category_id   MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(20)        NOT NULL UNIQUE,
    category_type VARCHAR(10)        NOT NULL,

    PRIMARY KEY (category_id)
);

CREATE TABLE additional_option
(
    option_id    INT UNSIGNED      NOT NULL AUTO_INCREMENT,
    option_name  VARCHAR(10)       NOT NULL UNIQUE,
    option_price SMALLINT UNSIGNED NOT NULL,
    max_quantity TINYINT UNSIGNED  NOT NULL,

    PRIMARY KEY (option_id)
);

CREATE TABLE product
(
    product_id   INT UNSIGNED       NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(20)        NOT NULL UNIQUE,
    category_id  MEDIUMINT UNSIGNED NOT NULL,
    price        MEDIUMINT UNSIGNED NOT NULL,
    description  VARCHAR(255)       NOT NULL,
    image        VARCHAR(255)       NOT NULL,
    is_active    BOOLEAN            NOT NULL,
    created_at   DATE,

    PRIMARY KEY (product_id),
    FOREIGN KEY (category_id) REFERENCES product_category (category_id)
);

CREATE TABLE product_option
(
    product_id INT UNSIGNED     NOT NULL,
    option_id  INT UNSIGNED     NOT NULL,
    quantity   TINYINT UNSIGNED NOT NULL,

    PRIMARY KEY (product_id, option_id),
    FOREIGN KEY (option_id) REFERENCES additional_option (option_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
);

CREATE TABLE office_stock
(
    office_id   MEDIUMINT UNSIGNED NOT NULL,
    product_id  INT UNSIGNED       NOT NULL,
    stock_value SMALLINT UNSIGNED  NOT NULL,

    PRIMARY KEY (office_id, product_id),
    FOREIGN KEY (office_id) REFERENCES office (office_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE my_menu
(
    my_menu_id   BIGINT       NOT NULL AUTO_INCREMENT,
    my_menu_name VARCHAR(10)  NOT NULL,
    product_id   INT UNSIGNED NOT NULL,
    member_id    BIGINT       NOT NULL,
    option_info  JSON         NOT NULL,

    PRIMARY KEY (my_menu_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE orders
(
    order_id        BIGINT             NOT NULL AUTO_INCREMENT,
    member_id       BIGINT             NOT NULL,
    office_id       MEDIUMINT UNSIGNED NOT NULL,
    order_time      DATETIME           NOT NULL,
    approve_time    DATETIME,
    completion_time DATETIME,

    PRIMARY KEY (order_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id) ON DELETE CASCADE,
    FOREIGN KEY (office_id) REFERENCES office (office_id) ON DELETE CASCADE
);


CREATE TABLE order_product
(
    order_product_id BIGINT             NOT NULL AUTO_INCREMENT,
    order_id         BIGINT             NOT NULL,
    product_id       INT UNSIGNED       NOT NULL,
    quantity         TINYINT UNSIGNED   NOT NULL,
    option_info      JSON               NOT NULL,
    price            MEDIUMINT UNSIGNED NOT NULL,

    PRIMARY KEY (order_product_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
);