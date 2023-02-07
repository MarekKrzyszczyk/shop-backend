--liquibase formatted sql
--changeset mkrzyszczyk:23
alter table `order` add user_id bigint;
--changeset mkrzyszczyk:24
alter table `order` add constraint fk_order_user_id foreign key (user_id) references users(id);