CREATE TABLE reservations (
    id BIGSERIAL PRIMARY KEY,
    equipment_id BIGINT NOT NULL,
    equipment_name VARCHAR(255) NOT NULL,
    renter_id BIGINT NOT NULL,
    owner_id BIGINT NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);
