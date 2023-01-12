--liquibase formatted sql
--changeset mkrzyszczyk:16

create table payment(
                        id bigint not null auto_increment PRIMARY KEY,
                        name varchar(64) not null,
                        type varchar(32) not null,
                        default_payment boolean default false,
                        note text
);
insert into payment(id, name, type, default_payment, note)
values (1, 'Bank transfer', 'BANK_TRANSFER', true, 'Please make a transfer to the account:\n30 1030 1739 5825 1518 9904 4499\n adding the order number in the title');