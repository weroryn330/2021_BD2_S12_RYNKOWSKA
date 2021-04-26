ALTER TABLE invoices
    ADD CONSTRAINT id_customer FOREIGN KEY ( id_customer )
        REFERENCES customers ( id_customer );
