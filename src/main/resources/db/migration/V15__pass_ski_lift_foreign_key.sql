ALTER TABLE pass
    ADD CONSTRAINT id_ski_lift FOREIGN KEY ( id_ski_lift )
        REFERENCES ski_lift ( id_ski_lift );
