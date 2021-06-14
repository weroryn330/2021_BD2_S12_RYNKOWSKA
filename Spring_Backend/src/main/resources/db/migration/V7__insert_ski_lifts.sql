INSERT INTO ski_lifts VALUES (3, 'Zywiec Zdroj', 1025);
INSERT INTO ski_lifts VALUES (4, 'Muszynianka', 1410);
INSERT INTO ski_lifts VALUES (5, 'Naleczowianka', 966);
INSERT INTO ski_lifts VALUES (6, 'Ustronianka', 1525);
INSERT INTO ski_lifts VALUES (7, 'Dobrowianka', 1772);
INSERT INTO ski_lifts VALUES (8, 'Jurajska', 1918);
INSERT INTO ski_lifts VALUES (9, 'Kropla Beskidu', 1863);
INSERT INTO ski_lifts VALUES (10, 'Polaris', 1655);
UPDATE ski_lifts SET height = 1000 WHERE id_ski_lift = 1;
UPDATE ski_lifts SET height = 2137 WHERE id_ski_lift = 2;

CREATE INDEX idx_ski_lifts ON ski_lifts(name, height);