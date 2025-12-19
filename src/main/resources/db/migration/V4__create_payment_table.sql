CREATE TABLE IF NOT EXISTS payments (
    id SERIAL PRIMARY KEY,

    rental_request_id BIGINT NOT NULL,
    renter_id BIGINT NOT NULL,

    owner_id BIGINT NOT NULL,

    equipment_id BIGINT NOT NULL,

    amount DECIMAL(10,2) NOT NULL,

    reference VARCHAR(100) UNIQUE NOT NULL,
    status VARCHAR(20) NOT NULL,          -- PENDING, SUCCESS, FAILED
    payment_provider VARCHAR(20) NOT NULL, -- PAYSTACK

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_rental
        FOREIGN KEY (rental_request_id)
        REFERENCES rental_requests(id),

    CONSTRAINT fk_payment_renter
        FOREIGN KEY (renter_id)
        REFERENCES users(id)
);
