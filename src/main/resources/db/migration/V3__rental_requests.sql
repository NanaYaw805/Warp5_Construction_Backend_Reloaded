CREATE TABLE IF NOT EXISTS rental_requests (
    id SERIAL PRIMARY KEY,

    equipment_id BIGINT NOT NULL,
    equipment_name VARCHAR(150),

    renter_id BIGINT NOT NULL,
    renter_name VARCHAR(150),

    owner_id BIGINT NOT NULL,

    start_date DATE NOT NULL,
    end_date DATE NOT NULL,

    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);
