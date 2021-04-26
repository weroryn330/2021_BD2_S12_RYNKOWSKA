ALTER TABLE pass
    ADD CONSTRAINT id_spa FOREIGN KEY ( id_spa )
        REFERENCES spas ( id_spa );
