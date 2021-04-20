drop table if exists customers;

CREATE TABLE customers (
                           id_customer       INTEGER NOT NULL,
                           first_name        VARCHAR(30),
                           last_name         VARCHAR(30),
                           address           VARCHAR(80),
                           city              VARCHAR(40),
                           voivodeship       VARCHAR(25),
                           country           VARCHAR(30),
                           postal_code       VARCHAR(30),
                           phone             VARCHAR(20),
                           email             VARCHAR(50),
                           support_employee  INTEGER
);

ALTER TABLE customers ADD CONSTRAINT customers_pk PRIMARY KEY ( id_customer );
