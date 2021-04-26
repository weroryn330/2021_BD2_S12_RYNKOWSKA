drop table if exists invoice_items;


CREATE TABLE invoice_items (
                               id_invoice_item  INTEGER NOT NULL,
                               unit_price       FLOAT,
                               quantity         INTEGER,
                               id_invoice       INTEGER NOT NULL,
                               id_pass          INTEGER NOT NULL
);

ALTER TABLE invoice_items ADD CONSTRAINT invoice_items_pk PRIMARY KEY ( id_invoice_item );
