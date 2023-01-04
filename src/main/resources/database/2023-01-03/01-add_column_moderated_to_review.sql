--liquibase formatted sql
--changeset mkrzyszczyk:10

alter table review add column moderated boolean default false;