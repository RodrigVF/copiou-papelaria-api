CREATE TABLE permissions (
    permission_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    role_id INT REFERENCES roles(role_id) ON DELETE SET NULL,
    last_login timestamp DEFAULT NULL,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE user_logs (
    user_log_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(user_id),
    activity_type VARCHAR(100),
    description TEXT NOT NULL,
    created_at timestamp
);

CREATE TABLE role_permissions (
    role_permission_id SERIAL PRIMARY KEY,
    role_id INT NOT NULL REFERENCES roles(role_id),
    permission_id INT NOT NULL REFERENCES permissions(permission_id),
    UNIQUE (role_id, permission_id)
);