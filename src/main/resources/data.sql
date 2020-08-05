INSERT INTO customers (customer_id, name) VALUES
  (1, 'Jon'),
  (2, 'Paul'),
  (3, 'Tibor');

INSERT INTO tournaments (tournament_id, name) VALUES
  (1, 'Wimbledon'),
  (2, 'US Open');

INSERT INTO matches (match_id, tournament_id, start_date, playera, playerb) VALUES
-- Wimbledon
  (1, 1, {ts '2020-08-04 10:00:00.00'}, 'M Berrettini',  'A Bedene'),
  (2, 1, {ts '2020-08-04 16:00:00.00'}, 'B Schnur', 'M Baghdatis'),
  (3, 1, {ts '2020-08-05 14:00:00.00'}, 'D Köpfer', 'F Krajinović'),
  (4, 1, {ts '2020-08-05 16:00:00.00'}, 'M Ebden', 'D Schwartzman'),
  (5, 1, {ts '2020-08-06 13:00:00.00'}, 'L Pouille',  'R Gasquet'),
  (6, 1, {ts '2020-08-06 14:00:00.00'}, 'A Bublik', 'G Barrère'),
  (7, 1, {ts '2020-08-07 15:00:00.00'}, 'J Clarke', 'N Rubin'),
  (8, 1, {ts '2020-08-08 16:00:00.00'}, 'L Harris', 'R Federer'),
-- US Open
  (9, 2, {ts '2020-08-31 10:00:00.00'}, 'Kyle Edmund', 'Pablo Andújar'),
  (10, 2, {ts '2020-08-31 16:00:00.00'}, 'Taylor Fritz', 'Feliciano López'),
  (11, 2, {ts '2020-09-01 10:00:00.00'}, 'Guido Pella', 'Pablo Carreño Busta'),
  (12, 2, {ts '2020-09-01 16:00:00.00'}, 'Félix Auger-Aliassime', 'Denis Shapovalov'),
  (13, 2, {ts '2020-09-02 08:00:00.00'}, 'Fabio Fognini', 'Reilly Opelka'),
  (14, 2, {ts '2020-09-02 14:00:00.00'}, 'Roberto Bautista Agut', 'Mikhail Kukushkin'),
  (15, 2, {ts '2020-09-03 08:00:00.00'}, 'Karen Khachanov', 'Vasek Pospisil'),
  (16, 2, {ts '2020-09-03 16:00:00.00'}, 'Stefanos Tsitsipas', 'Andrey Rublev');

INSERT INTO licenses (license_id, customer_id, tournament_id, match_id, type) VALUES
  (1, 1, 1, NULL, 'tournament'), --Jon -> Wimbledon
  (2, 1, NULL, 9, 'match'), --Jon -> US Open Kyle Edmund vs. Pablo Andújar
  (3, 2, 2, NULL, 'tournament'), --Paul -> US Open
  (4, 2, NULL, 4, 'match'), --Paul -> Wimbledon M Ebden vs. D Schwartzman
  (5, 3, NULL, 1, 'match'), --Tibor -> Wimbledon M Berrettini vs. A Bedene
  (6, 3, NULL, 2, 'match'), --Tibor -> Wimbledon B Schnur vs. M Baghdatis
  (7, 3, NULL, 8, 'match'), --Tibor -> Wimbledon L Harris vs. R Federer
  (8, 3, NULL, 10, 'match'); --Tibor -> US Open Taylor Fritz vs. Feliciano López