--liquibase formatted sql
--changeset mkrzyszczyk:4

alter table product add column slug varchar(255) after image;
alter table product add constraint ui_product_slug unique key(slug);