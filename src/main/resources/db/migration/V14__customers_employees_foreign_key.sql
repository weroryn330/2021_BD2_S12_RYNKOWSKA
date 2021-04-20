ALTER TABLE customers
    ADD CONSTRAINT support_employee FOREIGN KEY ( support_employee )
        REFERENCES employees ( id_employee );
