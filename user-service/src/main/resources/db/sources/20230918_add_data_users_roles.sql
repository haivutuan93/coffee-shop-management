-- Insert data into roles
INSERT INTO roles (id, role_name)
VALUES (1, 'Owner'),
       (2, 'Operator'),
       (3, 'Customer');

-- Insert data into users
-- Password for all users is "123"
INSERT INTO users (id, username, password, email, mobile_number, regular_address, total_score, created_by, updated_by)
VALUES (1, 'customer1', '$2a$10$KOBvS8mjZnxuVqDKGPrL6u8nLkQFZ6LcXBQB8hDiiPaT0SjkdcXFy', 'customer1@example.com', '1234567890', 'Address 1', 0, 'admin', 'admin'),
       (2, 'customer2', '$2a$10$KOBvS8mjZnxuVqDKGPrL6u8nLkQFZ6LcXBQB8hDiiPaT0SjkdcXFy', 'customer2@example.com', '1234567891', 'Address 2', 0, 'admin', 'admin'),
       (3, 'customer3', '$2a$10$KOBvS8mjZnxuVqDKGPrL6u8nLkQFZ6LcXBQB8hDiiPaT0SjkdcXFy', 'customer3@example.com', '1234567892', 'Address 3', 0, 'admin', 'admin'),
       (4, 'operator1', '$2a$10$KOBvS8mjZnxuVqDKGPrL6u8nLkQFZ6LcXBQB8hDiiPaT0SjkdcXFy', 'operator1@example.com', '1234567893', 'Address 4', 0, 'admin', 'admin'),
       (5, 'owner1',    '$2a$10$KOBvS8mjZnxuVqDKGPrL6u8nLkQFZ6LcXBQB8hDiiPaT0SjkdcXFy', 'owner1@example.com',    '1234567894', 'Address 5', 0, 'admin', 'admin');

-- Insert data into user_roles
INSERT INTO user_roles (user_id, role_id, shop_id)
VALUES (1, 3, NULL),
       (2, 3, NULL),
       (3, 3, NULL),
       (4, 2, 1),
       (5, 1, 1);
