drop table if exists employees;

CREATE TABLE employees (
                           id_employee  INTEGER NOT NULL,
                           first_name   VARCHAR(30),
                           last_name    VARCHAR(30),
                           title        VARCHAR(30),
                           birth_date   DATE,
                           hire_date    DATE,
                           address      VARCHAR(80),
                           city         VARCHAR(40),
                           voivodeship  VARCHAR(25),
                           country      VARCHAR(30),
                           postal_code  VARCHAR(30),
                           phone        VARCHAR(20),
                           email        VARCHAR(50),
                           reports_to   INTEGER
);

ALTER TABLE employees ADD CONSTRAINT employees_pk PRIMARY KEY ( id_employee );
