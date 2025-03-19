CREATE TABLE sales_point (
    sales_point_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address_id VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    business_hours VARCHAR(255),
    geolocation VARCHAR(255),
    is_main_sales_point BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE sales_point_image (
    sales_point_image_id SERIAL PRIMARY KEY,
    sales_point_id INTEGER,
    image_id INTEGER,
    CONSTRAINT fk_sales_point_image_sales_point FOREIGN KEY(sales_point_id) REFERENCES sales_point(sales_point_id),
    CONSTRAINT fk_sales_point_image_image FOREIGN KEY(image_id) REFERENCES image(image_id)
);