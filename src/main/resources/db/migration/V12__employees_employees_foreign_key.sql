ALTER TABLE employees
    ADD CONSTRAINT reports_to FOREIGN KEY ( reports_to )
        REFERENCES employees ( id_employee );
