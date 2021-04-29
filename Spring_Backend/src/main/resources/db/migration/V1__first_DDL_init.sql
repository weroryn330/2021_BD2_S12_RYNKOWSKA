CREATE TABLE discounts (
                           id_discount  INTEGER NOT NULL,
                           percentage   INTEGER,
                           uses         INTEGER,
                           length       INTEGER
);

ALTER TABLE discounts ADD CONSTRAINT discounts_pk PRIMARY KEY ( id_discount );

CREATE TABLE invoices (
                          id_invoice           INTEGER NOT NULL,
                          invoice_date         DATE,
                          billing_address      VARCHAR(80),
                          billing_city         VARCHAR(40),
                          billing_state        VARCHAR(30),
                          billing_country      VARCHAR(30),
                          billing_postal_code  VARCHAR(30),
                          total                FLOAT,
                          users_id_customer    INTEGER NOT NULL
);

ALTER TABLE invoices ADD CONSTRAINT invoices_pk PRIMARY KEY ( id_invoice );

CREATE TABLE pass (
                      id_invoice_item        INTEGER NOT NULL,
                      unit_price             FLOAT,
                      invoices_id_invoice    INTEGER NOT NULL,
                      start_date             DATE,
                      end_date               DATE,
                      discounts_id_discount  INTEGER NOT NULL,
                      first_name             VARCHAR(30),
                      last_name              VARCHAR(30),
                      birth_date             DATE,
                      uses                   INTEGER,
                      uses_left              INTEGER
);

CREATE UNIQUE INDEX pass__idx ON
    pass (
        discounts_id_discount
    ASC );

ALTER TABLE pass ADD CONSTRAINT pass_pk PRIMARY KEY ( id_invoice_item );

CREATE TABLE price_list (
                            id_price_list         INTEGER NOT NULL,
                            start_date            DATE,
                            end_date              DATE,
                            percentage            INTEGER,
                            pass_id_invoice_item  INTEGER NOT NULL
);

CREATE UNIQUE INDEX price_list__idx ON
    price_list (
        pass_id_invoice_item
    ASC );

ALTER TABLE price_list ADD CONSTRAINT price_list_pk PRIMARY KEY ( id_price_list );

CREATE TABLE raports (
                         id_raport          INTEGER NOT NULL,
                         name               VARCHAR(70),
                         "date"             DATE,
                         path               VARCHAR(128),
                         users_id_customer  INTEGER NOT NULL
);

ALTER TABLE raports ADD CONSTRAINT raports_pk PRIMARY KEY ( id_raport );

CREATE TABLE roles (
                       id_role    INTEGER NOT NULL,
                       role_name  VARCHAR(30)
);

ALTER TABLE roles ADD CONSTRAINT roles_pk PRIMARY KEY ( id_role );

CREATE TABLE ski_lift (
                          id_ski_lift  INTEGER NOT NULL,
                          name         VARCHAR(128),
                          height       INTEGER,
                          is_opened    CHAR(1)
);

ALTER TABLE ski_lift ADD CONSTRAINT ski_lift_pk PRIMARY KEY ( id_ski_lift );

CREATE TABLE ski_lift_pass (
                               pass_id_invoice_item  INTEGER NOT NULL,
                               ski_lift_id_ski_lift  INTEGER NOT NULL,
                               uses                  INTEGER
);

ALTER TABLE ski_lift_pass ADD CONSTRAINT includes_pk PRIMARY KEY ( pass_id_invoice_item,
                                                                   ski_lift_id_ski_lift );

CREATE TABLE ski_lift_schedule (
                                   id_ski_lift_schedule  INTEGER NOT NULL,
                                   start_date            DATE,
                                   end_date              DATE,
                                   opens_time            DATE,
                                   closes_time           DATE,
                                   ski_lift_id_ski_lift  INTEGER NOT NULL
);

ALTER TABLE ski_lift_schedule ADD CONSTRAINT ski_lift_schedule_pk PRIMARY KEY ( id_ski_lift_schedule );

CREATE TABLE users (
                       id_customer    INTEGER NOT NULL,
                       first_name     VARCHAR(30),
                       last_name      VARCHAR(30),
                       address        VARCHAR(80),
                       city           VARCHAR(40),
                       voivodeship    VARCHAR(25),
                       country        VARCHAR(30),
                       postal_code    VARCHAR(30),
                       phone          VARCHAR(20),
                       email          VARCHAR(50),
                       roles_id_role  INTEGER NOT NULL,
                       password       VARCHAR(70)
);

CREATE UNIQUE INDEX users__idx ON
    users (
        roles_id_role
    ASC );

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( id_customer );

ALTER TABLE ski_lift_pass
    ADD CONSTRAINT includes_pass_fk FOREIGN KEY ( pass_id_invoice_item )
        REFERENCES pass ( id_invoice_item );

ALTER TABLE ski_lift_pass
    ADD CONSTRAINT includes_ski_lift_fk FOREIGN KEY ( ski_lift_id_ski_lift )
        REFERENCES ski_lift ( id_ski_lift );

ALTER TABLE invoices
    ADD CONSTRAINT invoices_users_fk FOREIGN KEY ( users_id_customer )
        REFERENCES users ( id_customer );

ALTER TABLE pass
    ADD CONSTRAINT pass_discounts_fk FOREIGN KEY ( discounts_id_discount )
        REFERENCES discounts ( id_discount );

ALTER TABLE pass
    ADD CONSTRAINT pass_invoices_fk FOREIGN KEY ( invoices_id_invoice )
        REFERENCES invoices ( id_invoice );

ALTER TABLE price_list
    ADD CONSTRAINT price_list_pass_fk FOREIGN KEY ( pass_id_invoice_item )
        REFERENCES pass ( id_invoice_item );

ALTER TABLE raports
    ADD CONSTRAINT raports_users_fk FOREIGN KEY ( users_id_customer )
        REFERENCES users ( id_customer );

ALTER TABLE ski_lift_schedule
    ADD CONSTRAINT ski_lift_schedule_ski_lift_fk FOREIGN KEY ( ski_lift_id_ski_lift )
        REFERENCES ski_lift ( id_ski_lift );

ALTER TABLE users
    ADD CONSTRAINT users_roles_fk FOREIGN KEY ( roles_id_role )
        REFERENCES roles ( id_role );
