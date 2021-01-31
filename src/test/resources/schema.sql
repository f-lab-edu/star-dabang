-- DROP TABLE member IF EXISTS;
drop table if exists member;

create table member
(
    member_id bigint       not null auto_increment,
    passwd    varchar(25)  not null,
    email     varchar(255) not null,
    phone     varchar(25)  not null,
    nickname  varchar(125) not null,
    birth     varchar(10)  not null,
    primary key (member_id)
);
