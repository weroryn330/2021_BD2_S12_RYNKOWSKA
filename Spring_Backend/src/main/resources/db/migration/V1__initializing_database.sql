CREATE TABLE age_discounts (
                               id_age_discount           INTEGER NOT NULL,
                               age_min                   INTEGER,
                               age_max                   INTEGER,
                               percentage                INTEGER,
                               price_list_id_price_list  INTEGER NOT NULL
);

CREATE TABLE invoices (
                          id_invoice           INTEGER NOT NULL,
                          invoice_date         DATE,
                          billing_address      VARCHAR(80),
                          billing_city         VARCHAR(40),
                          billing_state        VARCHAR(30),
                          billing_country      VARCHAR(30),
                          billing_postal_code  VARCHAR(30),
                          total                FLOAT,
                          users_id_user        INTEGER NOT NULL
);

CREATE TABLE passes (
                        id_pass                   INTEGER NOT NULL,
                        unit_price                FLOAT,
                        invoices_id_invoice       INTEGER NOT NULL,
                        start_date                DATE,
                        end_date                  DATE,
                        first_name                VARCHAR(30),
                        last_name                 VARCHAR(30),
                        birth_date                DATE,
                        uses_total                INTEGER,
                        uses_left                 INTEGER,
                        price_list_id_price_list  INTEGER NOT NULL
);

CREATE TABLE price_lists (
                             id_price_list  INTEGER NOT NULL,
                             start_date     DATE,
                             end_date       DATE
);

CREATE TABLE quantity_passes (
                                 id_quantity_pass          INTEGER NOT NULL,
                                 quantity                  INTEGER,
                                 price                     FLOAT,
                                 price_list_id_price_list  INTEGER NOT NULL
);

CREATE TABLE roles (
                       id_role    INTEGER NOT NULL,
                       role_name  VARCHAR(30)
);

CREATE TABLE ski_lift_schedules (
                                    id_ski_lift_schedule  INTEGER NOT NULL,
                                    start_date            DATE,
                                    end_date              DATE,
                                    opens_time            DATE,
                                    closes_time           DATE,
                                    ski_lift_id_ski_lift  INTEGER NOT NULL
);

CREATE TABLE ski_lifts (
                           id_ski_lift  INTEGER NOT NULL,
                           name         VARCHAR(128),
                           height       INTEGER,
                           is_opened    CHAR(1)
);

CREATE TABLE time_passes (
                             id_time_pass              INTEGER NOT NULL,
                             hours                     INTEGER,
                             price                     FLOAT,
                             price_list_id_price_list  INTEGER NOT NULL
);

CREATE TABLE usages (
                        id_usage                INTEGER NOT NULL,
                        passes_id_invoice_item  INTEGER NOT NULL,
                        ski_lift_id_ski_lift    INTEGER NOT NULL,
                        use_timestamp           TIMESTAMP,
                        success_flag            CHAR(1)
);

CREATE TABLE users (
                       id_user        INTEGER NOT NULL,
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

ALTER TABLE age_discounts ADD CONSTRAINT age_discounts_pk PRIMARY KEY ( id_age_discount );
ALTER TABLE usages ADD CONSTRAINT usages_pk PRIMARY KEY ( id_usage );
ALTER TABLE invoices ADD CONSTRAINT invoices_pk PRIMARY KEY ( id_invoice );
ALTER TABLE passes ADD CONSTRAINT passes_pk PRIMARY KEY ( id_pass );
ALTER TABLE price_lists ADD CONSTRAINT price_list_pk PRIMARY KEY ( id_price_list );
ALTER TABLE quantity_passes ADD CONSTRAINT quantity_passes_pk PRIMARY KEY ( id_quantity_pass );
ALTER TABLE roles ADD CONSTRAINT roles_pk PRIMARY KEY ( id_role );
ALTER TABLE ski_lift_schedules ADD CONSTRAINT ski_lift_schedule_pk PRIMARY KEY ( id_ski_lift_schedule );
ALTER TABLE ski_lifts ADD CONSTRAINT ski_lift_pk PRIMARY KEY ( id_ski_lift );
ALTER TABLE time_passes ADD CONSTRAINT time_passes_pk PRIMARY KEY ( id_time_pass );

CREATE UNIQUE INDEX users__idx ON
    users (
        roles_id_role
    ASC );

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( id_user );

ALTER TABLE age_discounts
    ADD CONSTRAINT age_discounts_price_list_fk FOREIGN KEY ( price_list_id_price_list )
        REFERENCES price_lists ( id_price_list )
        NOT DEFERRABLE;

ALTER TABLE invoices
    ADD CONSTRAINT invoices_users_fk FOREIGN KEY ( users_id_user )
        REFERENCES users ( id_user )
        NOT DEFERRABLE;

ALTER TABLE passes
    ADD CONSTRAINT passes_invoices_fk FOREIGN KEY ( invoices_id_invoice )
        REFERENCES invoices ( id_invoice )
        NOT DEFERRABLE;

ALTER TABLE passes
    ADD CONSTRAINT passes_price_list_fk FOREIGN KEY ( price_list_id_price_list )
        REFERENCES price_lists ( id_price_list )
        NOT DEFERRABLE;

ALTER TABLE quantity_passes
    ADD CONSTRAINT quantity_passes_price_list_fk FOREIGN KEY ( price_list_id_price_list )
        REFERENCES price_lists ( id_price_list )
        NOT DEFERRABLE;

ALTER TABLE ski_lift_schedules
    ADD CONSTRAINT ski_lift_schedule_ski_lift_fk FOREIGN KEY ( ski_lift_id_ski_lift )
        REFERENCES ski_lifts ( id_ski_lift )
        NOT DEFERRABLE;

ALTER TABLE time_passes
    ADD CONSTRAINT time_passes_price_list_fk FOREIGN KEY ( price_list_id_price_list )
        REFERENCES price_lists ( id_price_list )
        NOT DEFERRABLE;

ALTER TABLE usages
    ADD CONSTRAINT usages_passes_fk FOREIGN KEY ( passes_id_invoice_item )
        REFERENCES passes ( id_pass )
        NOT DEFERRABLE;

ALTER TABLE usages
    ADD CONSTRAINT usages_ski_lift_fk FOREIGN KEY ( ski_lift_id_ski_lift )
        REFERENCES ski_lifts ( id_ski_lift )
        NOT DEFERRABLE;

ALTER TABLE users
    ADD CONSTRAINT users_roles_fk FOREIGN KEY ( roles_id_role )
        REFERENCES roles ( id_role )
        NOT DEFERRABLE;
