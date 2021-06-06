INSERT INTO usages (id_usage, passes_id_invoice_item, ski_lift_id_ski_lift, use_timestamp, success_flag)
VALUES (2, 2, 2, '2021-05-26 12:46:00', 1);
INSERT INTO usages (id_usage, passes_id_invoice_item, ski_lift_id_ski_lift, use_timestamp, success_flag)
VALUES (3, 1, 1, '2021-05-26 12:46:00', 1);
INSERT INTO usages (id_usage, passes_id_invoice_item, ski_lift_id_ski_lift, use_timestamp, success_flag)
VALUES (4, 2, 1, '2021-05-26 12:46:00', 0);
INSERT INTO usages (id_usage, passes_id_invoice_item, ski_lift_id_ski_lift, use_timestamp, success_flag)
VALUES (5, 2, 1, '2021-05-26 12:46:00', 1);
INSERT INTO usages (id_usage, passes_id_invoice_item, ski_lift_id_ski_lift, use_timestamp, success_flag)
VALUES (6, 2, 1, '2021-05-26 12:46:00', 1);

CREATE OR REPLACE VIEW ski_report_view AS
SELECT ps.id_pass, count(us.id_usage), sl.name, sl.height
FROM passes AS ps JOIN usages AS us ON ps.id_pass=us.passes_id_invoice_item
JOIN ski_lifts AS sl ON sl.id_ski_lift=us.ski_lift_id_ski_lift
GROUP BY sl.name, ps.id_pass, sl.height, us.success_flag HAVING us.success_flag='1';
