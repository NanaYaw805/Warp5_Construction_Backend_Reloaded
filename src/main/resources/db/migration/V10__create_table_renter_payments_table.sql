CREATE TABLE IF NOT EXISTS payment (
    id BIGSERIAL PRIMARY KEY,

    rental_request_id BIGINT,
    renter_id BIGINT NOT NULL,
    owner_id BIGINT NOT NULL,
    equipment_id BIGINT NOT NULL,

    amount DOUBLE PRECISION NOT NULL,
    currency VARCHAR(10) DEFAULT 'GHS',

    payment_provider VARCHAR(50) NOT NULL,
    reference VARCHAR(100) UNIQUE NOT NULL,

    status VARCHAR(30) DEFAULT 'PENDING',

    created_at TIMESTAMP DEFAULT now()
);
