--liquibase formatted sql
--changeset mkrzyszczyk:25
alter table users add hash varchar(120);
--changeset mkrzyszczyk:26
alter table users add hash_date datetime;