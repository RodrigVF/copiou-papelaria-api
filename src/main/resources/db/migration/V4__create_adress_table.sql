CREATE TABLE address (
    address_id SERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    number INT NOT NULL,
    complement VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) DEFAULT 'Brasil' NOT NULL ,
    postal_code VARCHAR(20) NOT NULL
);