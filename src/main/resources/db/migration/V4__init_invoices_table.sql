drop table if exists invoices;

CREATE TABLE invoices (
                          id_invoice           INTEGER NOT NULL,
                          invoice_date         DATE,
                          billing_address      VARCHAR(80),
                          billing_city         VARCHAR(40),
                          billing_state        VARCHAR(30),
                          billing_country      VARCHAR(30),
                          billing_postal_code  VARCHAR(30),
                          total                FLOAT,
                          id_customer          INTEGER NOT NULL
);

ALTER TABLE invoices ADD CONSTRAINT invoices_pk PRIMARY KEY ( id_invoice );
