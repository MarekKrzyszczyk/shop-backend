--liquibase formatted sql
--changeset mkrzyszczyk:3

alter table product add column image varchar(128) after currency;

