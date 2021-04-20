ALTER TABLE invoice_items
    ADD CONSTRAINT id_pass FOREIGN KEY ( id_pass )
        REFERENCES pass ( id_pass );
