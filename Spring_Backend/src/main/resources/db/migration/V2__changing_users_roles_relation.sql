CREATE TABLE users_roles (
    users_id_user INTEGER NOT NULL,
    roles_id_role INTEGER NOT NULL
);

ALTER TABLE users_roles ADD CONSTRAINT users_roles_pk PRIMARY KEY ( users_id_user,roles_id_role );

ALTER TABLE users DROP CONSTRAINT users_roles_fk;

ALTER TABLE users DROP COLUMN roles_id_role;

ALTER TABLE users_roles
    ADD CONSTRAINT users_fk FOREIGN KEY (users_id_user)
    REFERENCES users (id_user)
    NOT DEFERRABLE;

ALTER TABLE users_roles
    ADD CONSTRAINT roles_fk FOREIGN KEY (roles_id_role)
    REFERENCES roles (id_role)
    NOT DEFERRABLE;