Create SEQUENCE serial_seq;
ALTER TABLE usages ALTER COLUMN id_usage SET DEFAULT nextval(serial_seq);
ALTER TABLE usages ALTER COLUMN id_usage SET NOT NULL;
ALTER SEQUENCE serial_seq OWNED BY usages.id_usage;