INSERT INTO shops (id, name, address, latitude, longitude, contact_details, queue_quantity, queue_size, opening_time, closing_time, created_by, updated_by)
VALUES (1, 'Coffee Shop 1', '123 Main St', 40.7128, -74.0060, '0123456789', 2, 10, '08:00:00', '18:00:00', 'admin', 'admin'),
       (2, 'Coffee Shop 2', '456 Elm St', 40.7128, -74.0060, '0123456789', 3, 10, '08:00:00', '18:00:00', 'admin', 'admin'),
       (3, 'Coffee Shop 3', '789 Oak St', 40.7128, -74.0060, '0123456789', 1, 10, '08:00:00', '18:00:00', 'admin', 'admin');

INSERT INTO queues (id, shop_id, current_quantity_orders, queue_size)
VALUES (1, 1, 5, 10),
       (2, 1, 7, 10),
       (3, 2, 4, 10),
       (4, 2, 6, 10),
       (5, 2, 2, 10),
       (6, 3, 3, 10);

INSERT INTO products (id, name, description, default_price)
VALUES (1, 'Espresso', 'Strong coffee', 3.0),
       (2, 'Latte', 'Coffee with milk', 4.0),
       (3, 'Cappuccino', 'Coffee with foam and milk', 4.5);

INSERT INTO shop_products (id, shop_id, product_id, price, quantity_remaining, created_by, updated_by)
VALUES (1, 1, 1, 3.2, 100, 'admin', 'admin'),
       (2, 1, 2, 4.1, 100, 'admin', 'admin'),
       (3, 1, 3, 4.6, 100, 'admin', 'admin'),
       (4, 2, 1, 2.9, 100, 'admin', 'admin'),
       (5, 2, 2, 3.8, 100, 'admin', 'admin'),
       (6, 2, 3, 4.4, 100, 'admin', 'admin'),
       (7, 3, 1, 3.1, 100, 'admin', 'admin'),
       (8, 3, 2, 4.2, 100, 'admin', 'admin'),
       (9, 3, 3, 4.7, 100, 'admin', 'admin');