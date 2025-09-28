-- -- -- Inserts para tabela service
-- INSERT INTO service (id, description, name) VALUES (1, 'Banho completo conforme o porte e pelagem do pet', 'Banho');
-- INSERT INTO service (id, description, name) VALUES (2, 'Tosa para manter a higiene em partes específicas como barriga, bumbum, olhos e inferior das patas', 'Tosa higiênica');
-- INSERT INTO service (id, description, name) VALUES (3, 'Tosa geral do corpo e cabeça com a lâmina escolhida', 'Tosa na máquina');
-- INSERT INTO service (id, description, name) VALUES (4, 'Corpinho na máquina, e cabeça feita na tesoura', 'Tosa bebê');
-- INSERT INTO service (id, description, name) VALUES (5, 'Tosa higiênica com adicional de tirar todos os pelos das patas', 'Botinha');
-- INSERT INTO service (id, description, name) VALUES (6, 'Desembolo dos pelos do pet', 'Desembolo');
-- INSERT INTO service (id, description, name) VALUES (7, 'Escovação dos dentes do pet', 'Escovação dentária');
-- INSERT INTO service (id, description, name) VALUES (8, 'Hidratação dos pelos', 'Hidratação');
-- INSERT INTO service (id, description, name) VALUES (9, 'Corte das unhas do pet', 'Corte de unha');
--
-- -- Inserts para tabela employee
-- INSERT INTO employee (id, name) VALUES (1, 'Priscila');
-- INSERT INTO employee (id, name) VALUES (2, 'Rute');
-- INSERT INTO employee (id, name) VALUES (3, 'Keila');
-- INSERT INTO employee (id, name) VALUES (4, 'Taina');
--
-- -- Inserts para tabela employees_has_services
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (1, 1);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (1, 2);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (1, 3);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (1, 4);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (1, 5);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (1, 6);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (1, 7);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (1, 8);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (1, 9);
--
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (2, 1);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (2, 2);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (2, 5);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (2, 6);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (2, 7);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (2, 8);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (2, 9);
--
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (3, 1);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (3, 2);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (3, 5);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (3, 6);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (3, 7);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (3, 8);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (3, 9);
--
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (4, 1);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (4, 2);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (4, 5);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (4, 6);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (4, 7);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (4, 8);
-- INSERT INTO employees_has_services (employee_id, service_id) VALUES (4, 9);
--
-- -- Inserts para tabela service_price_and_duration
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (1, 30, 'curta', 'pp', 55.0,  1);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (2, 40, 'curta', 'p', 60.0,  1);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (3, 50, 'curta', 'm', 70.0,  1);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (4, 78, 'curta', 'g', 100.0,  1);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (5, 138, 'curta', 'gg', 140.0,  1);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (6, 30, 'longa', 'pp', 60.0,  1);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (7, 40, 'longa', 'p', 70.0,  1);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (8, 60, 'longa', 'm', 80.0,  1);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (9, 120, 'longa', 'g', 140.0,  1);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (10, 120, 'longa', 'gg', 140.0,  1);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (11, 10, 'curta', 'pp', 10.0,  2);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (12, 10, 'curta', 'p', 10.0,  2);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (13, 10, 'curta', 'm', 15.0,  2);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (14, 20, 'curta', 'g', 20.0,  2);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (15, 20, 'curta', 'gg', 20.0,  2);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (16, 10, 'longa', 'pp', 10.0,  2);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (17, 10, 'longa', 'p', 10.0,  2);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (18, 10, 'longa', 'm', 15.0,  2);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (19, 20, 'longa', 'g', 20.0,  2);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (20, 20, 'longa', 'gg', 20.0,  2);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (21, 60, 'curta', 'pp', 35.0,  3);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (22, 60, 'curta', 'p', 35.0,  3);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (23, 70, 'curta', 'm', 50.0,  3);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (24, 42, 'curta', 'g', 50.0,  3);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (25, 42, 'curta', 'gg', 50.0,  3);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (26, 60, 'longa', 'pp', 35.0,  3);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (27, 60, 'longa', 'p', 35.0,  3);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (28, 70, 'longa', 'm', 50.0,  3);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (29, 42, 'longa', 'g', 50.0,  3);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (30, 42, 'longa', 'gg', 50.0,  3);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (31, 40, 'curta', 'pp', 40.0,  4);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (32, 50, 'curta', 'p', 40.0,  4);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (33, 70, 'curta', 'm', 40.0,  4);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (34, 42, 'curta', 'g', 60.0,  4);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (35, 42, 'curta', 'gg', 60.0,  4);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (36, 40, 'longa', 'pp', 40.0,  4);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (37, 50, 'longa', 'p', 40.0,  4);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (38, 70, 'longa', 'm', 40.0,  4);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (39, 42, 'longa', 'g', 60.0,  4);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (40, 42, 'longa', 'gg', 60.0,  4);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (41, 20, 'curta', 'pp', 20.0,  5);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (42, 20, 'curta', 'p', 20.0,  5);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (43, 20, 'curta', 'm', 20.0,  5);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (44, 20, 'curta', 'g', 20.0,  5);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (45, 20, 'curta', 'gg', 20.0,  5);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (46, 20, 'longa', 'pp', 20.0,  5);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (47, 20, 'longa', 'p', 20.0,  5);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (48, 20, 'longa', 'm', 20.0,  5);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (49, 20, 'longa', 'g', 20.0,  5);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (50, 20, 'longa', 'gg', 20.0,  5);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (51, 30, 'curta', 'pp', 30.0,  6);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (52, 30, 'curta', 'p', 30.0,  6);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (53, 30, 'curta', 'm', 30.0,  6);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (54, 30, 'curta', 'g', 30.0,  6);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (55, 30, 'curta', 'gg', 30.0,  6);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (56, 30, 'longa', 'pp', 30.0,  6);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (57, 30, 'longa', 'p', 30.0,  6);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (58, 30, 'longa', 'm', 30.0,  6);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (59, 30, 'longa', 'g', 30.0,  6);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (60, 30, 'longa', 'gg', 30.0,  6);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (61, 5, 'curta', 'pp', 5.0,  7);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (62, 5, 'curta', 'p', 5.0,  7);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (63, 5, 'curta', 'm', 5.0,  7);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (64, 5, 'curta', 'g', 5.0,  7);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (65, 5, 'curta', 'gg', 5.0,  7);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (66, 5, 'longa', 'pp', 5.0,  7);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (67, 5, 'longa', 'p', 5.0,  7);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (68, 5, 'longa', 'm', 5.0,  7);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (69, 5, 'longa', 'g', 5.0,  7);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (70, 5, 'longa', 'gg', 5.0,  7);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (71, 5, 'curta', 'pp', 20.0,  8);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (72, 5, 'curta', 'p', 20.0,  8);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (73, 5, 'curta', 'm', 20.0,  8);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (74, 5, 'curta', 'g', 20.0,  8);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (75, 5, 'curta', 'gg', 20.0,  8);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (76, 10, 'longa', 'pp', 30.0,  8);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (77, 10, 'longa', 'p', 30.0,  8);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (78, 10, 'longa', 'm', 30.0,  8);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (79, 10, 'longa', 'g', 30.0,  8);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (80, 10, 'longa', 'gg', 30.0,  8);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (81, 10, 'curta', 'pp', 20.0,  9);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (82, 10, 'curta', 'p', 20.0,  9);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (83, 10, 'curta', 'm', 20.0,  9);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (84, 10, 'curta', 'g', 20.0,  9);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (85, 10, 'curta', 'gg', 20.0,  9);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (86, 10, 'longa', 'pp', 20.0,  9);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (87, 10, 'longa', 'p', 20.0,  9);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (88, 10, 'longa', 'm', 20.0,  9);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (89, 10, 'longa', 'g', 20.0,  9);
-- INSERT INTO service_price_and_duration (id, duration, pet_coat, pet_size, price, service_id) VALUES (90, 10, 'longa', 'gg', 20.0,  9);
--
-- INSERT INTO owner (
--     id, cep, complement, cpf, created_at, email, is_adm, last_update,
--     name, neighborhood, number, password, phone_number, street
-- ) VALUES
--       (  -- Hash da senha, senha real é 'Teste123.'
--           DEFAULT, '01234567', 'Apto Teste', '12345678901', NOW(), 'solarium@teste.com', true, NOW(),
--           'Solarium Teste', 'Centro', '123', '$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t.',
--           '11987654321', 'Rua dos Testes'
--       ),
--       (
--           DEFAULT, '76543210', 'Apto Teste', '98765432100', NOW(), 'maria.oliveira@email.com', false, NOW(),
--           'Maria Oliveira', 'Bairro Novo', '456', '"$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t."',
--           '11998765432', 'Avenida Brasil'
--       ),
--       (
--           DEFAULT, '12345678', 'Apto Teste', '45678912300', NOW(), 'carlos.souza@email.com', false, NOW(),
--           'Carlos Souza', 'Jardim América', '789', '"$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t."',
--           '11912345678', 'Rua das Palmeiras'
--       );
--
-- INSERT INTO pet (
--     id, age, breed, coat, created_at, last_update,
--     name, sex, size, species, owner_id
-- ) VALUES
--       (
--           DEFAULT, 2, 'Bulldog Francês', 'curta', '2025-05-22 10:00:00', '2025-05-22 10:30:00',
--           'Thor', 'macho', 'm', 'cachorro', 1
--       ),
--       (
--           DEFAULT, 1, 'Persa', 'longa', '2025-05-22 11:00:00', '2025-05-22 11:30:00',
--           'Luna', 'fêmea', 'p', 'gato', 2
--       ),
--       (
--           DEFAULT, 3, 'Golden Retriever', 'longa', '2025-05-22 12:00:00', '2025-05-22 12:30:00',
--           'Max', 'macho', 'g', 'cachorro', 3
--       );
--
-- INSERT INTO appointment (
--     id, created_at, end_date_time, is_finished, last_update,
--     services, start_date_time, total_price, employee_id, pet_id
-- ) VALUES
-- -- 03/06/2025 (poucos agendamentos - 1)
-- (DEFAULT, '2025-06-03 08:30:00', '2025-06-03 10:00:00', true, '2025-06-03 08:30:00', 'Banho, Desembolo', '2025-06-03 09:00:00', 70.00, 1, 1),
--
-- -- 04/06/2025 (médio - 2)
-- (DEFAULT, '2025-06-04 09:00:00', '2025-06-04 10:30:00', true, '2025-06-04 09:00:00', 'Corte de unha', '2025-06-04 09:30:00', 40.00, 2, 2),
-- (DEFAULT, '2025-06-04 10:00:00', '2025-06-04 12:00:00', true, '2025-06-04 10:00:00', 'Banho, Tosa bebê', '2025-06-04 10:30:00', 65.00, 3, 3),
--
-- -- 05/06/2025 (muito - 4)
-- (DEFAULT, '2025-06-05 08:00:00', '2025-06-05 09:30:00', true, '2025-06-05 08:00:00', 'Banho, Hidratação', '2025-06-05 08:30:00', 60.00, 1, 2),
-- (DEFAULT, '2025-06-05 09:30:00', '2025-06-05 11:00:00', true, '2025-06-05 09:30:00', 'Banho, Tosa higiênica', '2025-06-05 10:00:00', 70.00, 2, 1),
-- (DEFAULT, '2025-06-05 11:00:00', '2025-06-05 12:30:00', true, '2025-06-05 11:00:00', 'Banho', '2025-06-05 11:30:00', 50.00, 3, 3),
-- (DEFAULT, '2025-06-05 13:00:00', '2025-06-05 14:30:00', true, '2025-06-05 13:00:00', 'Banho, Escovação dentária', '2025-06-05 13:30:00', 75.00, 4, 1),
--
-- -- 06/06/2025 (poucos - 1)
-- (DEFAULT, '2025-06-06 09:00:00', '2025-06-06 10:30:00', true, '2025-06-06 09:00:00', 'Banho, Tosa na máquina', '2025-06-06 09:30:00', 65.00, 2, 3),
--
-- -- 07/06/2025 (médio - 3)
-- (DEFAULT, '2025-06-07 08:30:00', '2025-06-07 10:00:00', true, '2025-06-07 08:30:00', 'Banho', '2025-06-07 09:00:00', 50.00, 1, 2),
-- (DEFAULT, '2025-06-07 10:00:00', '2025-06-07 11:30:00', true, '2025-06-07 10:00:00', 'Banho, Desembolo', '2025-06-07 10:30:00', 70.00, 3, 1),
-- (DEFAULT, '2025-06-07 13:00:00', '2025-06-07 14:30:00', true, '2025-06-07 13:00:00', 'Banho, Hidratação', '2025-06-07 13:30:00', 60.00, 2, 3),
--
-- -- 08/06/2025 (mais agendamentos - 4)
-- (DEFAULT, '2025-06-08 08:00:00', '2025-06-08 09:30:00', true, '2025-06-08 08:00:00', 'Banho, Tosa bebê', '2025-06-08 08:30:00', 65.00, 3, 1),
-- (DEFAULT, '2025-06-08 09:30:00', '2025-06-08 11:00:00', true, '2025-06-08 09:30:00', 'Banho', '2025-06-08 10:00:00', 50.00, 4, 2),
-- (DEFAULT, '2025-06-08 11:00:00', '2025-06-08 12:30:00', true, '2025-06-08 11:00:00', 'Banho, Escovação dentária', '2025-06-08 11:30:00', 75.00, 1, 3),
-- (DEFAULT, '2025-06-08 14:00:00', '2025-06-08 15:30:00', true, '2025-06-08 14:00:00', 'Corte de unha', '2025-06-08 14:30:00', 40.00, 2, 2),
--
-- -- 09/06/2025 (hoje - médio - 2)
-- (DEFAULT, '2025-06-09 09:00:00', '2025-06-09 10:30:00', true, '2025-06-09 09:00:00', 'Banho, Hidratação', '2025-06-09 09:30:00', 60.00, 3, 2),
-- (DEFAULT, '2025-06-09 11:00:00', '2025-06-09 12:30:00', true, '2025-06-09 11:00:00', 'Banho, Desembolo', '2025-06-09 11:30:00', 70.00, 4, 3);
-- --

------- NEW

--Inserts para tabela Procedure
INSERT INTO procedure (id, description, name) VALUES (1, 'Banho completo conforme o porte e pelagem do pet', 'Banho');
INSERT INTO procedure (id, description, name) VALUES (2, 'Tosa para manter a higiene em partes específicas como barriga, bumbum, olhos e inferior das patas', 'Tosa higiênica');
INSERT INTO procedure (id, description, name) VALUES (3, 'Tosa geral do corpo e cabeça com a lâmina escolhida', 'Tosa na máquina');
INSERT INTO procedure (id, description, name) VALUES (4, 'Corpinho na máquina, e cabeça feita na tesoura', 'Tosa bebê');
INSERT INTO procedure (id, description, name) VALUES (5, 'Tosa higiênica com adicional de tirar todos os pelos das patas', 'Botinha');
INSERT INTO procedure (id, description, name) VALUES (6, 'Desembolo dos pelos do pet', 'Desembolo');
INSERT INTO procedure (id, description, name) VALUES (7, 'Escovação dos dentes do pet', 'Escovação dentária');
INSERT INTO procedure (id, description, name) VALUES (8, 'Hidratação dos pelos', 'Hidratação');
INSERT INTO procedure (id, description, name) VALUES (9, 'Corte das unhas do pet', 'Corte de unha');

-- Inserts para tabela employee
INSERT INTO employee (id, name) VALUES (1, 'Priscila');
INSERT INTO employee (id, name) VALUES (2, 'Rute');
INSERT INTO employee (id, name) VALUES (3, 'Keila');
INSERT INTO employee (id, name) VALUES (4, 'Taina');

-- Inserts para tabela employees_has_services
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (1, 1);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (1, 2);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (1, 3);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (1, 4);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (1, 5);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (1, 6);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (1, 7);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (1, 8);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (1, 9);

INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (2, 1);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (2, 2);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (2, 5);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (2, 6);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (2, 7);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (2, 8);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (2, 9);

INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (3, 1);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (3, 2);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (3, 5);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (3, 6);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (3, 7);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (3, 8);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (3, 9);

INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (4, 1);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (4, 2);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (4, 5);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (4, 6);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (4, 7);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (4, 8);
INSERT INTO employees_has_procedures (employee_id, procedure_id) VALUES (4, 9);

-- Inserts para tabela procedures_price_and_duration
--p
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (1, 30, 'curta', 'pp', 55.0,  1);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (2, 40, 'curta', 'p', 60.0,  1);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (3, 50, 'curta', 'm', 70.0,  1);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (4, 78, 'curta', 'g', 100.0,  1);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (5, 138, 'curta', 'gg', 140.0,  1);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (6, 30, 'longa', 'pp', 60.0,  1);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (7, 40, 'longa', 'p', 70.0,  1);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (8, 60, 'longa', 'm', 80.0,  1);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (9, 120, 'longa', 'g', 140.0,  1);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (10, 120, 'longa', 'gg', 140.0,  1);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (11, 10, 'curta', 'pp', 10.0,  2);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (12, 10, 'curta', 'p', 10.0,  2);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (13, 10, 'curta', 'm', 15.0,  2);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (14, 20, 'curta', 'g', 20.0,  2);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (15, 20, 'curta', 'gg', 20.0,  2);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (16, 10, 'longa', 'pp', 10.0,  2);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (17, 10, 'longa', 'p', 10.0,  2);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (18, 10, 'longa', 'm', 15.0,  2);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (19, 20, 'longa', 'g', 20.0,  2);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (20, 20, 'longa', 'gg', 20.0,  2);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (21, 60, 'curta', 'pp', 35.0,  3);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (22, 60, 'curta', 'p', 35.0,  3);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (23, 70, 'curta', 'm', 50.0,  3);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (24, 42, 'curta', 'g', 50.0,  3);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (25, 42, 'curta', 'gg', 50.0,  3);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (26, 60, 'longa', 'pp', 35.0,  3);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (27, 60, 'longa', 'p', 35.0,  3);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (28, 70, 'longa', 'm', 50.0,  3);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (29, 42, 'longa', 'g', 50.0,  3);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (30, 42, 'longa', 'gg', 50.0,  3);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (31, 40, 'curta', 'pp', 40.0,  4);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (32, 50, 'curta', 'p', 40.0,  4);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (33, 70, 'curta', 'm', 40.0,  4);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (34, 42, 'curta', 'g', 60.0,  4);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (35, 42, 'curta', 'gg', 60.0,  4);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (36, 40, 'longa', 'pp', 40.0,  4);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (37, 50, 'longa', 'p', 40.0,  4);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (38, 70, 'longa', 'm', 40.0,  4);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (39, 42, 'longa', 'g', 60.0,  4);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (40, 42, 'longa', 'gg', 60.0,  4);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (41, 20, 'curta', 'pp', 20.0,  5);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (42, 20, 'curta', 'p', 20.0,  5);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (43, 20, 'curta', 'm', 20.0,  5);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (44, 20, 'curta', 'g', 20.0,  5);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (45, 20, 'curta', 'gg', 20.0,  5);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (46, 20, 'longa', 'pp', 20.0,  5);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (47, 20, 'longa', 'p', 20.0,  5);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (48, 20, 'longa', 'm', 20.0,  5);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (49, 20, 'longa', 'g', 20.0,  5);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (50, 20, 'longa', 'gg', 20.0,  5);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (51, 30, 'curta', 'pp', 30.0,  6);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (52, 30, 'curta', 'p', 30.0,  6);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (53, 30, 'curta', 'm', 30.0,  6);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (54, 30, 'curta', 'g', 30.0,  6);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (55, 30, 'curta', 'gg', 30.0,  6);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (56, 30, 'longa', 'pp', 30.0,  6);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (57, 30, 'longa', 'p', 30.0,  6);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (58, 30, 'longa', 'm', 30.0,  6);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (59, 30, 'longa', 'g', 30.0,  6);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (60, 30, 'longa', 'gg', 30.0,  6);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (61, 5, 'curta', 'pp', 5.0,  7);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (62, 5, 'curta', 'p', 5.0,  7);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (63, 5, 'curta', 'm', 5.0,  7);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (64, 5, 'curta', 'g', 5.0,  7);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (65, 5, 'curta', 'gg', 5.0,  7);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (66, 5, 'longa', 'pp', 5.0,  7);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (67, 5, 'longa', 'p', 5.0,  7);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (68, 5, 'longa', 'm', 5.0,  7);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (69, 5, 'longa', 'g', 5.0,  7);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (70, 5, 'longa', 'gg', 5.0,  7);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (71, 5, 'curta', 'pp', 20.0,  8);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (72, 5, 'curta', 'p', 20.0,  8);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (73, 5, 'curta', 'm', 20.0,  8);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (74, 5, 'curta', 'g', 20.0,  8);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (75, 5, 'curta', 'gg', 20.0,  8);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (76, 10, 'longa', 'pp', 30.0,  8);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (77, 10, 'longa', 'p', 30.0,  8);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (78, 10, 'longa', 'm', 30.0,  8);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (79, 10, 'longa', 'g', 30.0,  8);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (80, 10, 'longa', 'gg', 30.0,  8);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (81, 10, 'curta', 'pp', 20.0,  9);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (82, 10, 'curta', 'p', 20.0,  9);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (83, 10, 'curta', 'm', 20.0,  9);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (84, 10, 'curta', 'g', 20.0,  9);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (85, 10, 'curta', 'gg', 20.0,  9);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (86, 10, 'longa', 'pp', 20.0,  9);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (87, 10, 'longa', 'p', 20.0,  9);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (88, 10, 'longa', 'm', 20.0,  9);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (89, 10, 'longa', 'g', 20.0,  9);
INSERT INTO procedure_price_and_duration (id, duration, pet_coat, pet_size, price, procedure_id) VALUES (90, 10, 'longa', 'gg', 20.0,  9);

INSERT INTO owner (
    id, cep, complement, cpf, created_at, email, is_adm, last_update,
    name, neighborhood, number, password, phone_number, street
) VALUES
      (  -- Hash da senha, senha real é 'Teste123.'
          DEFAULT, '01234567', 'Apto Teste', '12345678901', NOW(), 'solarium@teste.com', true, NOW(),
          'Solarium Teste', 'Centro', '123', '$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t.',
          '11987654321', 'Rua dos Testes'
      ),
      (
          DEFAULT, '76543210', 'Apto Teste', '98765432100', NOW(), 'maria.oliveira@email.com', false, NOW(),
          'Maria Oliveira', 'Bairro Novo', '456', '"$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t."',
          '11998765432', 'Avenida Brasil'
      ),
      (
          DEFAULT, '12345678', 'Apto Teste', '45678912300', NOW(), 'carlos.souza@email.com', false, NOW(),
          'Carlos Souza', 'Jardim América', '789', '"$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t."',
          '11912345678', 'Rua das Palmeiras'
      );

INSERT INTO pet (
    id, age, breed, coat, created_at, last_update,
    name, sex, size, species, owner_id
) VALUES
      (
          DEFAULT, 2, 'Bulldog Francês', 'curta', '2025-05-22 10:00:00', '2025-05-22 10:30:00',
          'Thor', 'macho', 'm', 'cachorro', 1
      ),
      (
          DEFAULT, 1, 'Persa', 'longa', '2025-05-22 11:00:00', '2025-05-22 11:30:00',
          'Luna', 'fêmea', 'p', 'gato', 2
      ),
      (
          DEFAULT, 3, 'Golden Retriever', 'longa', '2025-05-22 12:00:00', '2025-05-22 12:30:00',
          'Max', 'macho', 'g', 'cachorro', 3
      );

INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update,
    procedures, start_date_time, total_price, employee_id, pet_id
) VALUES
-- 03/06/2025 (poucos agendamentos - 1)
(DEFAULT, '2025-06-03 08:30:00', '2025-06-03 10:00:00', true, '2025-06-03 08:30:00', 'Banho, Desembolo', '2025-06-03 09:00:00', 70.00, 1, 1),

-- 04/06/2025 (médio - 2)
(DEFAULT, '2025-06-04 09:00:00', '2025-06-04 10:30:00', true, '2025-06-04 09:00:00', 'Corte de unha', '2025-06-04 09:30:00', 40.00, 2, 2),
(DEFAULT, '2025-06-04 10:00:00', '2025-06-04 12:00:00', true, '2025-06-04 10:00:00', 'Banho, Tosa bebê', '2025-06-04 10:30:00', 65.00, 3, 3),

-- 05/06/2025 (muito - 4)
(DEFAULT, '2025-06-05 08:00:00', '2025-06-05 09:30:00', true, '2025-06-05 08:00:00', 'Banho, Hidratação', '2025-06-05 08:30:00', 60.00, 1, 2),
(DEFAULT, '2025-06-05 09:30:00', '2025-06-05 11:00:00', true, '2025-06-05 09:30:00', 'Banho, Tosa higiênica', '2025-06-05 10:00:00', 70.00, 2, 1),
(DEFAULT, '2025-06-05 11:00:00', '2025-06-05 12:30:00', true, '2025-06-05 11:00:00', 'Banho', '2025-06-05 11:30:00', 50.00, 3, 3),
(DEFAULT, '2025-06-05 13:00:00', '2025-06-05 14:30:00', true, '2025-06-05 13:00:00', 'Banho, Escovação dentária', '2025-06-05 13:30:00', 75.00, 4, 1),

-- 06/06/2025 (poucos - 1)
(DEFAULT, '2025-06-06 09:00:00', '2025-06-06 10:30:00', true, '2025-06-06 09:00:00', 'Banho, Tosa na máquina', '2025-06-06 09:30:00', 65.00, 2, 3),

-- 07/06/2025 (médio - 3)
(DEFAULT, '2025-06-07 08:30:00', '2025-06-07 10:00:00', true, '2025-06-07 08:30:00', 'Banho', '2025-06-07 09:00:00', 50.00, 1, 2),
(DEFAULT, '2025-06-07 10:00:00', '2025-06-07 11:30:00', true, '2025-06-07 10:00:00', 'Banho, Desembolo', '2025-06-07 10:30:00', 70.00, 3, 1),
(DEFAULT, '2025-06-07 13:00:00', '2025-06-07 14:30:00', true, '2025-06-07 13:00:00', 'Banho, Hidratação', '2025-06-07 13:30:00', 60.00, 2, 3),

-- 08/06/2025 (mais agendamentos - 4)
(DEFAULT, '2025-06-08 08:00:00', '2025-06-08 09:30:00', true, '2025-06-08 08:00:00', 'Banho, Tosa bebê', '2025-06-08 08:30:00', 65.00, 3, 1),
(DEFAULT, '2025-06-08 09:30:00', '2025-06-08 11:00:00', true, '2025-06-08 09:30:00', 'Banho', '2025-06-08 10:00:00', 50.00, 4, 2),
(DEFAULT, '2025-06-08 11:00:00', '2025-06-08 12:30:00', true, '2025-06-08 11:00:00', 'Banho, Escovação dentária', '2025-06-08 11:30:00', 75.00, 1, 3),
(DEFAULT, '2025-06-08 14:00:00', '2025-06-08 15:30:00', true, '2025-06-08 14:00:00', 'Corte de unha', '2025-06-08 14:30:00', 40.00, 2, 2),

-- 09/06/2025 (hoje - médio - 2)
(DEFAULT, '2025-06-09 09:00:00', '2025-06-09 10:30:00', true, '2025-06-09 09:00:00', 'Banho, Hidratação', '2025-06-09 09:30:00', 60.00, 3, 2),
(DEFAULT, '2025-06-09 11:00:00', '2025-06-09 12:30:00', true, '2025-06-09 11:00:00', 'Banho, Desembolo', '2025-06-09 11:30:00', 70.00, 4, 3);
