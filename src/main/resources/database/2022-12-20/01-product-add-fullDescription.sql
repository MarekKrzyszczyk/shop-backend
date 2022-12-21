--liquibase formatted sql
--changeset mkrzyszczyk:5

alter table product add column full_description varchar(255) after description;