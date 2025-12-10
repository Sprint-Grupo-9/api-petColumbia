INSERT INTO pet_offering (id, description, name) VALUES
    (1, 'Banho completo conforme o porte e pelagem do pet', 'Banho'),
    (2, 'Tosa para manter a higiene em partes específicas como barriga, bumbum, olhos e inferior das patas', 'Tosa higiênica'),
    (3, 'Tosa geral do corpo e cabeça com a lâmina escolhida', 'Tosa na máquina'),
    (4, 'Corpinho na máquina, e cabeça feita na tesoura', 'Tosa bebê'),
    (5, 'Tosa higiênica com adicional de tirar todos os pelos das patas', 'Botinha'),
    (6, 'Desembolo dos pelos do pet', 'Desembolo'),
    (7, 'Escovação dos dentes do pet', 'Escovação dentária'),
    (8, 'Hidratação dos pelos', 'Hidratação'),
    (9, 'Corte das unhas do pet', 'Corte de unha');

INSERT INTO employee (id, name) VALUES
    (1, 'Priscila'),
    (2, 'Rute'),
    (3, 'Keila'),
    (4, 'Taina');

INSERT INTO employees_has_pet_offerings (employee_id, pet_offering_id) VALUES
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9),
    (2, 1), (2, 2), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9),
    (3, 1), (3, 2), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9),
    (4, 1), (4, 2), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9);

-- Procedimento 1: Banho
INSERT INTO pet_offering_price_and_duration (id, pet_offering_id, pet_size, pet_coat, price, duration) VALUES
    (1, 1, 'pp', 'curta', 55.0, 30),
    (2, 1, 'p', 'curta', 60.0, 40),
    (3, 1, 'm', 'curta', 70.0, 50),
    (4, 1, 'g', 'curta', 100.0, 78),
    (5, 1, 'gg', 'curta', 140.0, 138),
    (6, 1, 'pp', 'longa', 60.0, 30),
    (7, 1, 'p', 'longa', 70.0, 40),
    (8, 1, 'm', 'longa', 80.0, 60),
    (9, 1, 'g', 'longa', 140.0, 120),
    (10, 1, 'gg', 'longa', 140.0, 120);

-- Procedimento 2: Tosa higiênica
INSERT INTO pet_offering_price_and_duration (id, pet_offering_id, pet_size, pet_coat, price, duration) VALUES
    (11, 2, 'pp', 'curta', 10.0, 10),
    (12, 2, 'p', 'curta', 10.0, 10),
    (13, 2, 'm', 'curta', 15.0, 10),
    (14, 2, 'g', 'curta', 20.0, 20),
    (15, 2, 'gg', 'curta', 20.0, 20),
    (16, 2, 'pp', 'longa', 10.0, 10),
    (17, 2, 'p', 'longa', 10.0, 10),
    (18, 2, 'm', 'longa', 15.0, 10),
    (19, 2, 'g', 'longa', 20.0, 20),
    (20, 2, 'gg', 'longa', 20.0, 20);

-- Procedimento 3: Tosa na máquina
INSERT INTO pet_offering_price_and_duration (id, pet_offering_id, pet_size, pet_coat, price, duration) VALUES
    (21, 3, 'pp', 'curta', 35.0, 60),
    (22, 3, 'p', 'curta', 35.0, 60),
    (23, 3, 'm', 'curta', 50.0, 70),
    (24, 3, 'g', 'curta', 50.0, 42),
    (25, 3, 'gg', 'curta', 50.0, 42),
    (26, 3, 'pp', 'longa', 35.0, 60),
    (27, 3, 'p', 'longa', 35.0, 60),
    (28, 3, 'm', 'longa', 50.0, 70),
    (29, 3, 'g', 'longa', 50.0, 42),
    (30, 3, 'gg', 'longa', 50.0, 42);

-- Procedimento 4: Tosa bebê
INSERT INTO pet_offering_price_and_duration (id, pet_offering_id, pet_size, pet_coat, price, duration) VALUES
    (31, 4, 'pp', 'curta', 40.0, 40),
    (32, 4, 'p', 'curta', 40.0, 50),
    (33, 4, 'm', 'curta', 40.0, 70),
    (34, 4, 'g', 'curta', 60.0, 42),
    (35, 4, 'gg', 'curta', 60.0, 42),
    (36, 4, 'pp', 'longa', 40.0, 40),
    (37, 4, 'p', 'longa', 40.0, 50),
    (38, 4, 'm', 'longa', 40.0, 70),
    (39, 4, 'g', 'longa', 60.0, 42),
    (40, 4, 'gg', 'longa', 60.0, 42);

-- Procedimento 5: Botinha
INSERT INTO pet_offering_price_and_duration (id, pet_offering_id, pet_size, pet_coat, price, duration) VALUES
    (41, 5, 'pp', 'curta', 20.0, 20),
    (42, 5, 'p', 'curta', 20.0, 20),
    (43, 5, 'm', 'curta', 20.0, 20),
    (44, 5, 'g', 'curta', 20.0, 20),
    (45, 5, 'gg', 'curta', 20.0, 20),
    (46, 5, 'pp', 'longa', 20.0, 20),
    (47, 5, 'p', 'longa', 20.0, 20),
    (48, 5, 'm', 'longa', 20.0, 20),
    (49, 5, 'g', 'longa', 20.0, 20),
    (50, 5, 'gg', 'longa', 20.0, 20);

-- Procedimento 6: Desembolo
INSERT INTO pet_offering_price_and_duration (id, pet_offering_id, pet_size, pet_coat, price, duration) VALUES
    (51, 6, 'pp', 'curta', 30.0, 30),
    (52, 6, 'p', 'curta', 30.0, 30),
    (53, 6, 'm', 'curta', 30.0, 30),
    (54, 6, 'g', 'curta', 30.0, 30),
    (55, 6, 'gg', 'curta', 30.0, 30),
    (56, 6, 'pp', 'longa', 30.0, 30),
    (57, 6, 'p', 'longa', 30.0, 30),
    (58, 6, 'm', 'longa', 30.0, 30),
    (59, 6, 'g', 'longa', 30.0, 30),
    (60, 6, 'gg', 'longa', 30.0, 30);

-- Procedimento 7: Escovação dentária
INSERT INTO pet_offering_price_and_duration (id, pet_offering_id, pet_size, pet_coat, price, duration) VALUES
    (61, 7, 'pp', 'curta', 5.0, 5),
    (62, 7, 'p', 'curta', 5.0, 5),
    (63, 7, 'm', 'curta', 5.0, 5),
    (64, 7, 'g', 'curta', 5.0, 5),
    (65, 7, 'gg', 'curta', 5.0, 5),
    (66, 7, 'pp', 'longa', 5.0, 5),
    (67, 7, 'p', 'longa', 5.0, 5),
    (68, 7, 'm', 'longa', 5.0, 5),
    (69, 7, 'g', 'longa', 5.0, 5),
    (70, 7, 'gg', 'longa', 5.0, 5);

-- Procedimento 8: Hidratação
INSERT INTO pet_offering_price_and_duration (id, pet_offering_id, pet_size, pet_coat, price, duration) VALUES
    (71, 8, 'pp', 'curta', 20.0, 5),
    (72, 8, 'p', 'curta', 20.0, 5),
    (73, 8, 'm', 'curta', 20.0, 5),
    (74, 8, 'g', 'curta', 20.0, 5),
    (75, 8, 'gg', 'curta', 20.0, 5),
    (76, 8, 'pp', 'longa', 30.0, 10),
    (77, 8, 'p', 'longa', 30.0, 10),
    (78, 8, 'm', 'longa', 30.0, 10),
    (79, 8, 'g', 'longa', 30.0, 10),
    (80, 8, 'gg', 'longa', 30.0, 10);

-- Procedimento 9: Corte de unha
INSERT INTO pet_offering_price_and_duration (id, pet_offering_id, pet_size, pet_coat, price, duration) VALUES
    (81, 9, 'pp', 'curta', 20.0, 10),
    (82, 9, 'p', 'curta', 20.0, 10),
    (83, 9, 'm', 'curta', 20.0, 10),
    (84, 9, 'g', 'curta', 20.0, 10),
    (85, 9, 'gg', 'curta', 20.0, 10),
    (86, 9, 'pp', 'longa', 20.0, 10),
    (87, 9, 'p', 'longa', 20.0, 10),
    (88, 9, 'm', 'longa', 20.0, 10),
    (89, 9, 'g', 'longa', 20.0, 10),
    (90, 9, 'gg', 'longa', 20.0, 10);