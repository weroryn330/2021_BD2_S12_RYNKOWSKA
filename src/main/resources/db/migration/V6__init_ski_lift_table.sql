drop table if exists ski_lift;

CREATE TABLE ski_lift (
                          id_ski_lift  INTEGER NOT NULL,
                          name         VARCHAR(128)
);

ALTER TABLE ski_lift ADD CONSTRAINT ski_lift_pk PRIMARY KEY ( id_ski_lift );
