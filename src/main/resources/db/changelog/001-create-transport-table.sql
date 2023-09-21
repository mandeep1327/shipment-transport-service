CREATE TABLE IF NOT EXISTS transport (
id SERIAL,
capacity DOUBLE PRECISION,
current_load DOUBLE PRECISION,
dimension JSONB,
PRIMARY KEY(id));