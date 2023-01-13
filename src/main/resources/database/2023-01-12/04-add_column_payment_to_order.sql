--liquibase formatted sql
--changeset mkrzyszczyk:17

alter table `order` add column payment_id bigint after phone;
update `order` set payment_id = 1;
alter table `order` modify payment_id bigint not null;