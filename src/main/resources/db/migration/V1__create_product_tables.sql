CREATE TABLE brand (
    brand_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE product (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    brand_id INT REFERENCES brand(brand_id) ON DELETE SET NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE image (
    image_id SERIAL PRIMARY KEY,
    src VARCHAR(255) NOT NULL,
    alt VARCHAR(255),
    is_thumbnail BOOLEAN NOT NULL DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE color (
    color_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE size (
    size_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE product_variant (
    product_variant_id SERIAL PRIMARY KEY,
    product_id INT REFERENCES product(product_id) ON DELETE CASCADE,
    color_id INT REFERENCES color(color_id) ON DELETE SET NULL,
    size_id INT REFERENCES size(size_id) ON DELETE SET NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at timestamp,
    updated_at timestamp,
    UNIQUE(product_id, color_id, size_id)
);

CREATE TABLE product_image (
    product_image_id SERIAL PRIMARY KEY,
    product_id INTEGER,
    image_id INTEGER,
    CONSTRAINT fk_product_image_product FOREIGN KEY(product_id) REFERENCES product(product_id),
    CONSTRAINT fk_product_image_image FOREIGN KEY(image_id) REFERENCES image(image_id)
);