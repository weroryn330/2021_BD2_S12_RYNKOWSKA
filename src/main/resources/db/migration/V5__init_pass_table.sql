drop table if exists pass;

CREATE TABLE pass (
                      id_pass      INTEGER NOT NULL,
                      unit_price   FLOAT,
                      id_ski_lift  INTEGER,
                      id_spa       INTEGER
);

ALTER TABLE pass ADD CONSTRAINT pass_pk PRIMARY KEY ( id_pass );
