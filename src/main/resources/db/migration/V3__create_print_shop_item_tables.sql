CREATE TABLE print_shop_item (
    print_shop_item_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price NUMERIC(10,2) NOT NULL,
    duration BIGINT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE print_shop_item_image (
    print_shop_item_image_id SERIAL PRIMARY KEY,
    print_shop_item_id INTEGER,
    image_id INTEGER,
    CONSTRAINT fk_print_shop_item_image_print_shop_item FOREIGN KEY(print_shop_item_id) REFERENCES print_shop_item(print_shop_item_id),
    CONSTRAINT fk_print_shop_item_image_image FOREIGN KEY(image_id) REFERENCES image(image_id)
);