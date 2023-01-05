--liquibase formatted sql
--changeset mkrzyszczyk:11

create table cart (
                        id bigint not null auto_increment PRIMARY KEY,
                        created datetime not null
);