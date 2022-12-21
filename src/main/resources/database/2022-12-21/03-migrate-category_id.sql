--liquibase formatted sql
--changeset mkrzyszczyk:8

insert into category(id, name, description, slug) values (1, 'Other', '', 'other');
update product set category_id = 1;
alter table product modify category_id bigint not null;