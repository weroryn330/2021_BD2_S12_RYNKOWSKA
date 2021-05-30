ALTER TABLE ski_lift_schedules DROP COLUMN opens_time;
ALTER TABLE ski_lift_schedules ADD COLUMN opens_time TIME WITHOUT TIME ZONE;
ALTER TABLE ski_lift_schedules DROP COLUMN  closes_time;
ALTER TABLE ski_lift_schedules ADD COLUMN closes_time TIME WITHOUT TIME ZONE;

INSERT INTO price_lists (id_price_list, start_date, end_date)
VALUES (1, '01-01-2020', '31-12-2020');
INSERT INTO price_lists (id_price_list, start_date, end_date)
VALUES (2, '01-01-2021', '31-12-2021');

INSERT INTO age_discounts (id_age_discount, age_min, age_max, percentage, price_list_id_price_list)
VALUES (1, 0, 5, 100, 1);
INSERT INTO age_discounts (id_age_discount, age_min, age_max, percentage, price_list_id_price_list)
VALUES (2, 6, 13, 50, 2);
INSERT INTO age_discounts (id_age_discount, age_min, age_max, percentage, price_list_id_price_list)
VALUES (3, 14, 18, 30, 2);

INSERT INTO users (id_user, first_name, last_name, address, city, voivodeship, country, postal_code, phone, email, password)
VALUES (1, 'Wiktoria', 'Fryta', 'Slowackiego 13', 'Katowice', 'Slaskie', 'Polska', '40-000', '123456789', 'WikiFryta22@interia.pl', 'qwerty');
INSERT INTO users (id_user, first_name, last_name, address, city, voivodeship, country, postal_code, phone, email, password)
VALUES (2, 'Marek', 'Pieczarek', 'Kebabowa 21', 'Katowice', 'Slaskie', 'Polska', '40-000', '987654321', 'PieczarekMarek1@interia.pl', '123456');
INSERT INTO users (id_user, first_name, last_name, address, city, voivodeship, country, postal_code, phone, email, password)
VALUES (3, 'Wiktoria', 'Fryta', 'Opolska 33', 'Katowice', 'Slaskie', 'Polska', '40-000', '555555555', 'AnkaSkakanka@interia.pl', 'qwerty12');
INSERT INTO users (id_user, first_name, last_name, address, city, voivodeship, country, postal_code, phone, email, password)
VALUES (4, 'Jan', 'Kowalski', 'Wojska Polskiego 13', 'Katowice', 'Slaskie', 'Polska', '40-000', '132123321', 'JanKowalski@interia.pl', 'admin');

INSERT INTO invoices (id_invoice, invoice_date, billing_address, billing_city, billing_state, billing_country, billing_postal_code, total, users_id_user)
VALUES (1, '22-04-2020', 'Slowackiego 13', 'Katowice', 'Slaskie', 'Polska', '40-000', 750.0, 1);
INSERT INTO invoices (id_invoice, invoice_date, billing_address, billing_city, billing_state, billing_country, billing_postal_code, total, users_id_user)
VALUES (2, '22-04-2020', 'Kebabowa 21', 'Katowice', 'Slaskie', 'Polska', '40-000', 600.0, 2);
INSERT INTO invoices (id_invoice, invoice_date, billing_address, billing_city, billing_state, billing_country, billing_postal_code, total, users_id_user)
VALUES (3, '22-04-2020', 'Opolska 33', 'Katowice', 'Slaskie', 'Polska', '40-000', 900.0, 3);

INSERT INTO passes (id_pass, unit_price, invoices_id_invoice, start_date, end_date, first_name, last_name, birth_date, uses_total, uses_left, price_list_id_price_list)
VALUES (1, 300.0, 1, '20-03-2020', '27-03-2020', 'Wiktoria', 'Fryta', '13-02-2000', 50, 49, 1);
INSERT INTO passes (id_pass, unit_price, invoices_id_invoice, start_date, end_date, first_name, last_name, birth_date, uses_total, uses_left, price_list_id_price_list)
VALUES (2, 450.0, 1, '20-03-2020', '27-03-2020', 'Wiktoria', 'Fryta', '13-02-2000', 100, 69, 1);
INSERT INTO passes (id_pass, unit_price, invoices_id_invoice, start_date, end_date, first_name, last_name, birth_date, uses_total, uses_left, price_list_id_price_list)
VALUES (3, 300.0, 2, '12-02-2020', '20-03-2020', 'Marek', 'Pieczarek', '13-02-1980', 50, 19, 1);
INSERT INTO passes (id_pass, unit_price, invoices_id_invoice, start_date, end_date, first_name, last_name, birth_date, uses_total, uses_left, price_list_id_price_list)
VALUES (4, 300.0, 3, '12-03-2020', '20-03-2020', 'Anka', 'Skakanka', '13-02-1990', 50, 19, 1);
INSERT INTO passes (id_pass, unit_price, invoices_id_invoice, start_date, end_date, first_name, last_name, birth_date, uses_total, uses_left, price_list_id_price_list)
VALUES (5, 150.0, 3, '12-03-2020', '20-03-2020', 'Anka', 'Skakanka', '13-02-1990', 20, 10, 1);
INSERT INTO passes (id_pass, unit_price, invoices_id_invoice, start_date, end_date, first_name, last_name, birth_date, uses_total, uses_left, price_list_id_price_list)
VALUES (6, 450.0, 3, '12-03-2020', '20-03-2020', 'Anka', 'Skakanka', '13-02-1990', 100, 19, 1);

INSERT INTO quantity_passes (id_quantity_pass, quantity, price, price_list_id_price_list)
VALUES (1, 100, 450.0, 1);
INSERT INTO quantity_passes (id_quantity_pass, quantity, price, price_list_id_price_list)
VALUES (2, 50, 300.0, 1);
INSERT INTO quantity_passes (id_quantity_pass, quantity, price, price_list_id_price_list)
VALUES (3, 20, 150.0, 1);

INSERT INTO ski_lifts (id_ski_lift, name, height, is_opened)
VALUES (1, 'Ciowianka', 690, 1);
INSERT INTO ski_lifts (id_ski_lift, name, height, is_opened)
VALUES (2, 'Kinia Pieninska', 420, 1);

INSERT INTO ski_lift_schedules (id_ski_lift_schedule, start_date, end_date, opens_time, closes_time, ski_lift_id_ski_lift)
VALUES (1, '01-01-2020', '31-12-2020', '07:00:00', '19:00:00', 1);
INSERT INTO ski_lift_schedules (id_ski_lift_schedule, start_date, end_date, opens_time, closes_time, ski_lift_id_ski_lift)
VALUES (2, '01-01-2021', '31-12-2021', '08:00:00', '20:00:00', 1);
INSERT INTO ski_lift_schedules (id_ski_lift_schedule, start_date, end_date, opens_time, closes_time, ski_lift_id_ski_lift)
VALUES (3, '01-01-2020', '31-12-2020', '07:00:00', '19:00:00', 2);
INSERT INTO ski_lift_schedules (id_ski_lift_schedule, start_date, end_date, opens_time, closes_time, ski_lift_id_ski_lift)
VALUES (4, '01-01-2021', '31-12-2021', '08:00:00', '20:00:00', 2);

INSERT INTO time_passes (id_time_pass, hours, price, price_list_id_price_list)
VALUES (1, 24, 600.0, 1);
INSERT INTO time_passes (id_time_pass, hours, price, price_list_id_price_list)
VALUES (2, 16, 500.0, 1);
INSERT INTO time_passes (id_time_pass, hours, price, price_list_id_price_list)
VALUES (3, 12, 400.0, 1);
INSERT INTO time_passes (id_time_pass, hours, price, price_list_id_price_list)
VALUES (4, 6, 300.0, 1);
INSERT INTO time_passes (id_time_pass, hours, price, price_list_id_price_list)
VALUES (5, 4, 250.0, 1);
INSERT INTO time_passes (id_time_pass, hours, price, price_list_id_price_list)
VALUES (6, 2, 140.0, 1);
INSERT INTO time_passes (id_time_pass, hours, price, price_list_id_price_list)
VALUES (7, 1, 80.0, 1);

INSERT INTO usages (id_usage, passes_id_invoice_item, ski_lift_id_ski_lift, use_timestamp, success_flag)
VALUES (1, 2, 1, '2021-05-26 12:46:00', 1);