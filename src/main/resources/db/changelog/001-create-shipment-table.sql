CREATE TABLE IF NOT EXISTS shipment (
id SERIAL,
dimension JSONB,
weight integer,
source varchar,
destination varchar,
shipment_date timestamp,
PRIMARY KEY(id));
