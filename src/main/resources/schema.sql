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

