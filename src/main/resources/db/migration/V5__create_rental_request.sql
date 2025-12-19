ALTER TABLE rental_requests
ADD COLUMN IF NOT EXISTS rental_amount VARCHAR(50);

ALTER TABLE rental_requests
ADD COLUMN IF NOT EXISTS rental_email VARCHAR(150);

ALTER TABLE rental_requests
ADD COLUMN IF NOT EXISTS rental_phone_number VARCHAR(30);
