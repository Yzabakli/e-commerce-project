INSERT INTO roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, description)
VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Admin'),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Customer');

INSERT INTO users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                  first_name, last_name, phone_number, role_id, password)
values ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'admin', 'admin', '12345678',
        1,'$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK');

-- Abc1