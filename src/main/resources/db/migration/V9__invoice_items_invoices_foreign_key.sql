ALTER TABLE invoice_items
    ADD CONSTRAINT id_invoice FOREIGN KEY ( id_invoice )
        REFERENCES invoices ( id_invoice );
