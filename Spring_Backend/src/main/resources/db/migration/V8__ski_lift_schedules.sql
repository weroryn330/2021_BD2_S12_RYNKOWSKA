DELETE FROM ski_lift_schedules WHERE id_ski_lift_schedule = 1;
DELETE FROM ski_lift_schedules WHERE id_ski_lift_schedule = 3;
UPDATE ski_lift_schedules SET id_ski_lift_schedule = 2 WHERE id_ski_lift_schedule = 3;
INSERT INTO ski_lift_schedules VALUES (3, '2021-01-01', '2021-12-31', 3);
INSERT INTO ski_lift_schedules VALUES (5, '2021-01-01', '2021-12-31', 4);
INSERT INTO ski_lift_schedules VALUES (6, '2021-01-01', '2021-12-31', 5);
INSERT INTO ski_lift_schedules VALUES (7, '2021-01-01', '2021-12-31', 6);
INSERT INTO ski_lift_schedules VALUES (8, '2021-01-01', '2021-12-31', 7);
INSERT INTO ski_lift_schedules VALUES (9, '2021-01-01', '2021-12-31', 8);
INSERT INTO ski_lift_schedules VALUES (10, '2021-01-01', '2021-12-31', 9);
INSERT INTO ski_lift_schedules VALUES (11, '2021-01-01', '2021-12-31', 10);

