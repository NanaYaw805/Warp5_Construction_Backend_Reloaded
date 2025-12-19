CREATE TABLE IF NOT EXISTS equipment (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    owner_id BIGINT NOT NULL,
    owner_name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    description TEXT,
    price NUMERIC(10,2),
    rating NUMERIC(2,1),
    availability BOOLEAN DEFAULT TRUE,
    image_one VARCHAR(255),
    image_two VARCHAR(255),
    image_three VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW()
);
