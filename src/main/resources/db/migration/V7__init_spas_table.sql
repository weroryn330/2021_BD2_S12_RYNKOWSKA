drop table if exists spas;

CREATE TABLE spas (
                      id_spa  INTEGER NOT NULL,
                      name    VARCHAR(128)
);

ALTER TABLE spas ADD CONSTRAINT spas_pk PRIMARY KEY ( id_spa );
