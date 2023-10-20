-- Inserimento di dati di esempio nel database

-- Inserimento dei clienti
INSERT INTO customer (name, surname, username, password, tax_code, role, is_available)
VALUES ('Paolo', 'Pacello', 'drPacello', 'dr_pacello99', '00122', 1, TRUE);

INSERT INTO customer (name, surname, username, password, tax_code, role, is_available)
VALUES ('Stefano', 'Ronci', 'stefRon', 'abc123_!', '00133', 1, TRUE);

-- Inserimento degli amministratori
INSERT INTO administrator (is_available, role, name, surname, username, password)
VALUES (TRUE, 0, 'Luca', 'Rossi', 'lRossiAdmin', 'aaa90_!');

-- Inserimento dei condomini
INSERT INTO condominium (is_available, address, administrator_id)
VALUES (TRUE, 'via Kant 11', 1);

INSERT INTO condominium (is_available, address, administrator_id)
VALUES (TRUE, 'via Nazionale 111', 1);

-- Inserimento degli appartamenti
INSERT INTO apartment (floor_number, is_available, unit_number, condominium_id, customer_id)
VALUES (3, TRUE, 3, 1, 1);

INSERT INTO apartment (floor_number, is_available, unit_number, condominium_id, customer_id)
VALUES (1, TRUE, 7, 2, 2);

-- Inserimento delle scansioni (meter)

INSERT INTO scan (is_available, scan_date, apartment_id, mc_liter)
VALUES (TRUE, '2023-12-18', 1 ,100);

INSERT INTO scan (is_available, scan_date, apartment_id, mc_liter)
VALUES (TRUE, '2023-12-18', 2, 300);

-- Inserimento delle fatture (bill) con il riferimento alla scansione (meter)
INSERT INTO bill (cost, payment_day, delivering_Day, is_available, scan_id)
VALUES (200, '2023-12-15', '2023-10-15', TRUE, 1);

-- Inserimento dei tecnici
INSERT INTO technician (is_available, workload, role, name, surname, username, password)
VALUES (TRUE, 5, 3, 'Edwar', 'Azzaro', 'hacker', '123456ee!');

-- Inserimento delle segretarie
INSERT INTO secretary (is_available, role, name, surname, username, password)
VALUES (TRUE, 2, 'Giulia', 'Bianchi', 'Giu99', 'giu_99_00');

-- Inserimento delle operazioni di intervento
INSERT INTO intervention (is_available, intervention_date, status, type, apartment_id, secretary_id, technician_id)
VALUES (TRUE, '2023-12-16', 3, 0, 1, 1, 1);

INSERT INTO intervention (is_available, intervention_date, status, type, apartment_id, secretary_id, technician_id)
VALUES (TRUE, '2023-12-15', 1, 1, 2, 1, 1);

INSERT INTO intervention (is_available, intervention_date, status, type, apartment_id, secretary_id, technician_id)
VALUES (TRUE, '2023-12-14', 2, 0, 1, 1, 1);

INSERT INTO intervention (is_available, intervention_date, status, type, apartment_id, secretary_id, technician_id)
VALUES (TRUE, '2023-12-19', 2, 0, 2, 1, 1);

INSERT INTO intervention (is_available, intervention_date, status, type, apartment_id, secretary_id, technician_id)
VALUES (TRUE, '2023-12-20', 2, 1, 2, 1, 1);