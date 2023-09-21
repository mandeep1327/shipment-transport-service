CREATE TABLE IF NOT EXISTS load_assignment (
id SERIAL,
shipment JSONB,
transport JSONB,
PRIMARY KEY(id));