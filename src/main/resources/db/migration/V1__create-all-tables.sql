CREATE TABLE category(
    category_id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE product(
    product_id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    brand TEXT NOT NULL,
    barcode TEXT NOT NULL,
    category_id TEXT NOT NULL,
    CONSTRAINT fk_category FOREIGN KEY (category_id)
    REFERENCES category(category_id)
);

CREATE TABLE users(
    user_id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    age INT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);