CREATE OR REPLACE VIEW ski_report_view AS
SELECT ps.id_pass,
       us.use_timestamp,
       sl.name,
       sl.height
FROM passes ps
         JOIN usages us ON ps.id_pass = us.passes_id_invoice_item
         JOIN ski_lifts sl ON sl.id_ski_lift = us.ski_lift_id_ski_lift
WHERE us.success_flag = '1';
