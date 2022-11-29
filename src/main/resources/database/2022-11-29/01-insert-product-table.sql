--liquibase formatted sql
--changeset mkrzyszczyk:2

INSERT INTO `shop`.`product`(`name`,`category`,`description`,`price`,`currency`)
VALUES
    ('Produkt 1', 'kategoria 1', 'Opis 1', 10.99, 'PLN'),
    ('Produkt 2', 'kategoria 1', 'Opis 2', 10.99, 'PLN'),
    ('Produkt 3', 'kategoria 1', 'Opis 3', 10.99, 'PLN'),
    ('Produkt 4', 'kategoria 2', 'Opis 4', 10.99, 'PLN'),
    ('Produkt 5', 'kategoria 2', 'Opis 5', 10.99, 'PLN');