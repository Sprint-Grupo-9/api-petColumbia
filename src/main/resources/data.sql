-- ============================================================================
-- DATA.SQL - DADOS DE TESTE - CORRIGIDO
-- ============================================================================
--
-- CORREÇÃO: Ordem das colunas ajustada para corresponder à Entity
-- ORDEM CORRETA: (id, pet_offering_id, pet_size, pet_coat, price, duration)
-- ============================================================================

-- Tabela: pet_offering
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

-- Tabela: employee
INSERT INTO employee (id, name) VALUES
(1, 'Priscila'),
(2, 'Rute'),
(3, 'Keila'),
(4, 'Taina');

-- Tabela: employees_has_pet_offerings
INSERT INTO employees_has_pet_offerings (employee_id, pet_offering_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9),
(2, 1), (2, 2), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9),
(3, 1), (3, 2), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9),
(4, 1), (4, 2), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9);

-- Tabela: pet_offering_price_and_duration
-- ORDEM: (id, pet_offering_id, pet_size, pet_coat, price, duration)

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

-- Tabela: owner

-- Senha dos users: "Teste123."
INSERT INTO owner (
    id, cep, complement, cpf, created_at, email, is_adm, last_update,
    name, neighborhood, number, password, phone_number, street
) VALUES
(1, '01234567', 'Apto 101', '12345678901', NOW(), 'admin@petcolumbia.com', true, NOW(),
 'Administrador Sistema', 'Centro', '123', '$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t.',
 '11987654321', 'Rua Principal'),

(2, '12345678', 'Casa', '98765432100', NOW(), 'joao.silva@email.com', false, NOW(),
 'João Silva', 'Jardim das Flores', '456', '$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t.',
 '11998765432', 'Avenida Brasil'),

(3, '87654321', 'Apto 202', '45678912300', NOW(), 'maria.santos@email.com', false, NOW(),
 'Maria Santos', 'Vila Nova', '789', '$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t.',
 '11912345678', 'Rua das Palmeiras'),

(4, '11223344', 'Casa 2', '11122233344', NOW(), 'carlos.oliveira@email.com', false, NOW(),
 'Carlos Oliveira', 'Bairro Alto', '321', '$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t.',
 '11955566677', 'Rua do Sol'),

(5, '55667788', 'Apto 303', '99988877766', NOW(), 'ana.costa@email.com', false, NOW(),
 'Ana Costa', 'Centro Histórico', '654', '$2a$10$6DNYINTrKZPcxhmMR2fxyOoBcAKQEu8TZz.cbbU0RYmSrAiNZ9/t.',
 '11944455566', 'Avenida Central');

-- Tabela: pet
INSERT INTO pet (
    id, age, breed, coat, created_at, last_update,
    name, sex, size, species, owner_id
) VALUES
(1, 3, 'Labrador', 'curta', NOW(), NOW(),
 'Thor', 'macho', 'g', 'cachorro', 2),

(2, 2, 'Poodle', 'longa', NOW(), NOW(),
 'Mel', 'fêmea', 'p', 'cachorro', 2),

(3, 1, 'Persa', 'longa', NOW(), NOW(),
 'Luna', 'fêmea', 'p', 'gato', 3),

(4, 5, 'Golden Retriever', 'longa', NOW(), NOW(),
 'Max', 'macho', 'g', 'cachorro', 3),

(5, 2, 'Bulldog Francês', 'curta', NOW(), NOW(),
 'Bob', 'macho', 'm', 'cachorro', 4),

(6, 1, 'Shih Tzu', 'longa', NOW(), NOW(),
 'Princesa', 'fêmea', 'pp', 'cachorro', 4),

(7, 4, 'Vira-lata', 'curta', NOW(), NOW(),
 'Toby', 'macho', 'm', 'cachorro', 5),

(8, 3, 'Siamês', 'curta', NOW(), NOW(),
 'Miau', 'macho', 'p', 'gato', 5);

-- Tabela: appointment
-- Agendamentos antigos (fora dos últimos 7 dias)
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
(1, '2025-01-01 08:00:00', '2025-01-01 09:30:00', true, '2025-01-01 08:00:00', 'Pet comportado',
 'Banho, Corte de unha', '2025-01-01 09:00:00', false, 120.0, 1, 1),

(2, '2025-01-01 10:00:00', '2025-01-01 11:10:00', true, '2025-01-01 10:00:00', null,
 'Banho, Tosa higiênica', '2025-01-01 10:30:00', false, 80.0, 2, 2),

(3, '2025-01-05 09:00:00', '2025-01-05 10:05:00', true, '2025-01-05 09:00:00', 'Primeira vez no petshop',
 'Banho, Escovação dentária', '2025-01-05 09:30:00', false, 75.0, 3, 3);

-- Agendamentos dos últimos 7 dias (para dashboard)
-- Usando DATEADD para calcular datas dinâmicas relativas a hoje

-- Hoje
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
(4, CURRENT_TIMESTAMP, DATEADD('HOUR', 1, DATEADD('HOUR', 9, CURRENT_DATE)), false, CURRENT_TIMESTAMP, 'Cliente VIP',
 'Banho, Tosa na máquina, Corte de unha', DATEADD('HOUR', 9, CURRENT_DATE), false, 140.0, 1, 1),

(5, CURRENT_TIMESTAMP, DATEADD('MINUTE', 50, DATEADD('HOUR', 14, CURRENT_DATE)), false, CURRENT_TIMESTAMP, null,
 'Banho, Hidratação', DATEADD('HOUR', 14, CURRENT_DATE), false, 90.0, 2, 2);

-- Ontem (dia -1)
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
(6, DATEADD('DAY', -1, CURRENT_TIMESTAMP), DATEADD('HOUR', 1, DATEADD('HOUR', 10, DATEADD('DAY', -1, CURRENT_DATE))), true, DATEADD('DAY', -1, CURRENT_TIMESTAMP), 'Pet muito peludo',
 'Banho, Desembolo', DATEADD('HOUR', 10, DATEADD('DAY', -1, CURRENT_DATE)), true, 120.0, 3, 3),

(7, DATEADD('DAY', -1, CURRENT_TIMESTAMP), DATEADD('MINUTE', 120, DATEADD('HOUR', 15, DATEADD('DAY', -1, CURRENT_DATE))), true, DATEADD('DAY', -1, CURRENT_TIMESTAMP), 'Banho relaxante',
 'Banho, Hidratação, Escovação dentária', DATEADD('HOUR', 15, DATEADD('DAY', -1, CURRENT_DATE)), false, 165.0, 4, 4),

(8, DATEADD('DAY', -1, CURRENT_TIMESTAMP), DATEADD('MINUTE', 60, DATEADD('HOUR', 11, DATEADD('DAY', -1, CURRENT_DATE))), true, DATEADD('DAY', -1, CURRENT_TIMESTAMP), null,
 'Tosa higiênica, Botinha, Corte de unha', DATEADD('HOUR', 11, DATEADD('DAY', -1, CURRENT_DATE)), false, 55.0, 1, 5);

-- Dia -2
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
(9, DATEADD('DAY', -2, CURRENT_TIMESTAMP), DATEADD('HOUR', 1, DATEADD('HOUR', 9, DATEADD('DAY', -2, CURRENT_DATE))), true, DATEADD('DAY', -2, CURRENT_TIMESTAMP), 'Muito dengosa',
 'Banho, Tosa bebê', DATEADD('HOUR', 9, DATEADD('DAY', -2, CURRENT_DATE)), false, 100.0, 2, 6),

(10, DATEADD('DAY', -2, CURRENT_TIMESTAMP), DATEADD('MINUTE', 50, DATEADD('HOUR', 16, DATEADD('DAY', -2, CURRENT_DATE))), true, DATEADD('DAY', -2, CURRENT_TIMESTAMP), null,
 'Banho, Corte de unha', DATEADD('HOUR', 16, DATEADD('DAY', -2, CURRENT_DATE)), false, 90.0, 3, 7);

-- Dia -3
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
(11, DATEADD('DAY', -3, CURRENT_TIMESTAMP), DATEADD('MINUTE', 45, DATEADD('HOUR', 10, DATEADD('DAY', -3, CURRENT_DATE))), true, DATEADD('DAY', -3, CURRENT_TIMESTAMP), 'Pet agitado',
 'Banho, Tosa higiênica, Escovação dentária', DATEADD('HOUR', 10, DATEADD('DAY', -3, CURRENT_DATE)), false, 80.0, 4, 8),

(12, DATEADD('DAY', -3, CURRENT_TIMESTAMP), DATEADD('HOUR', 1, DATEADD('HOUR', 14, DATEADD('DAY', -3, CURRENT_DATE))), true, DATEADD('DAY', -3, CURRENT_TIMESTAMP), 'Primeira tosa',
 'Banho, Tosa na máquina', DATEADD('HOUR', 14, DATEADD('DAY', -3, CURRENT_DATE)), false, 110.0, 1, 1),

(13, DATEADD('DAY', -3, CURRENT_TIMESTAMP), DATEADD('MINUTE', 50, DATEADD('HOUR', 11, DATEADD('DAY', -3, CURRENT_DATE))), true, DATEADD('DAY', -3, CURRENT_TIMESTAMP), null,
 'Banho, Hidratação, Corte de unha', DATEADD('HOUR', 11, DATEADD('DAY', -3, CURRENT_DATE)), true, 110.0, 2, 2);

-- Dia -4
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
(14, DATEADD('DAY', -4, CURRENT_TIMESTAMP), DATEADD('HOUR', 1, DATEADD('HOUR', 9, DATEADD('DAY', -4, CURRENT_DATE))), true, DATEADD('DAY', -4, CURRENT_TIMESTAMP), 'Gatinha tranquila',
 'Banho, Escovação dentária, Corte de unha', DATEADD('HOUR', 9, DATEADD('DAY', -4, CURRENT_DATE)), false, 95.0, 3, 3),

(15, DATEADD('DAY', -4, CURRENT_TIMESTAMP), DATEADD('MINUTE', 120, DATEADD('HOUR', 15, DATEADD('DAY', -4, CURRENT_DATE))), true, DATEADD('DAY', -4, CURRENT_TIMESTAMP), 'Banho completo',
 'Banho, Desembolo, Hidratação', DATEADD('HOUR', 15, DATEADD('DAY', -4, CURRENT_DATE)), false, 190.0, 4, 4);

-- Dia -5
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
(16, DATEADD('DAY', -5, CURRENT_TIMESTAMP), DATEADD('MINUTE', 60, DATEADD('HOUR', 10, DATEADD('DAY', -5, CURRENT_DATE))), true, DATEADD('DAY', -5, CURRENT_TIMESTAMP), 'Pet alegre',
 'Banho, Tosa higiênica, Botinha', DATEADD('HOUR', 10, DATEADD('DAY', -5, CURRENT_DATE)), false, 85.0, 1, 5),

(17, DATEADD('DAY', -5, CURRENT_TIMESTAMP), DATEADD('HOUR', 1, DATEADD('HOUR', 14, DATEADD('DAY', -5, CURRENT_DATE))), true, DATEADD('DAY', -5, CURRENT_TIMESTAMP), 'Tosa estilo bebê',
 'Banho, Tosa bebê, Corte de unha', DATEADD('HOUR', 14, DATEADD('DAY', -5, CURRENT_DATE)), false, 120.0, 2, 6),

(18, DATEADD('DAY', -5, CURRENT_TIMESTAMP), DATEADD('MINUTE', 50, DATEADD('HOUR', 16, DATEADD('DAY', -5, CURRENT_DATE))), true, DATEADD('DAY', -5, CURRENT_TIMESTAMP), null,
 'Banho, Escovação dentária', DATEADD('HOUR', 16, DATEADD('DAY', -5, CURRENT_DATE)), false, 75.0, 3, 7);

-- Dia -6
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
(19, DATEADD('DAY', -6, CURRENT_TIMESTAMP), DATEADD('MINUTE', 45, DATEADD('HOUR', 9, DATEADD('DAY', -6, CURRENT_DATE))), true, DATEADD('DAY', -6, CURRENT_TIMESTAMP), 'Gato manhoso',
 'Banho, Corte de unha', DATEADD('HOUR', 9, DATEADD('DAY', -6, CURRENT_DATE)), false, 70.0, 4, 8),

(20, DATEADD('DAY', -6, CURRENT_TIMESTAMP), DATEADD('HOUR', 1, DATEADD('HOUR', 11, DATEADD('DAY', -6, CURRENT_DATE))), true, DATEADD('DAY', -6, CURRENT_TIMESTAMP), 'Banho express',
 'Banho, Tosa higiênica', DATEADD('HOUR', 11, DATEADD('DAY', -6, CURRENT_DATE)), false, 120.0, 1, 1),

(21, DATEADD('DAY', -6, CURRENT_TIMESTAMP), DATEADD('MINUTE', 50, DATEADD('HOUR', 15, DATEADD('DAY', -6, CURRENT_DATE))), true, DATEADD('DAY', -6, CURRENT_TIMESTAMP), 'Cliente frequente',
 'Banho, Hidratação, Corte de unha', DATEADD('HOUR', 15, DATEADD('DAY', -6, CURRENT_DATE)), true, 110.0, 2, 2);

-- Dia -7
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
(22, DATEADD('DAY', -7, CURRENT_TIMESTAMP), DATEADD('HOUR', 1, DATEADD('HOUR', 10, DATEADD('DAY', -7, CURRENT_DATE))), true, DATEADD('DAY', -7, CURRENT_TIMESTAMP), 'Pet muito fofo',
 'Banho, Tosa bebê, Hidratação', DATEADD('HOUR', 10, DATEADD('DAY', -7, CURRENT_DATE)), false, 120.0, 3, 3),

(23, DATEADD('DAY', -7, CURRENT_TIMESTAMP), DATEADD('MINUTE', 120, DATEADD('HOUR', 14, DATEADD('DAY', -7, CURRENT_DATE))), true, DATEADD('DAY', -7, CURRENT_TIMESTAMP), 'Cachorro grande',
 'Banho, Desembolo, Tosa higiênica', DATEADD('HOUR', 14, DATEADD('DAY', -7, CURRENT_DATE)), false, 170.0, 4, 4),

(24, DATEADD('DAY', -7, CURRENT_TIMESTAMP), DATEADD('MINUTE', 60, DATEADD('HOUR', 16, DATEADD('DAY', -7, CURRENT_DATE))), true, DATEADD('DAY', -7, CURRENT_TIMESTAMP), null,
 'Banho, Tosa na máquina, Corte de unha', DATEADD('HOUR', 16, DATEADD('DAY', -7, CURRENT_DATE)), false, 105.0, 1, 5);

-- ============================================================================
-- RESET DAS SEQUÊNCIAS DE AUTO-INCREMENT
-- ============================================================================
-- Importante: Resetar as sequências após inserções manuais com IDs específicos
-- para evitar conflitos de chave primária ao inserir novos registros
-- ============================================================================

ALTER TABLE pet_offering ALTER COLUMN id RESTART WITH 10;
ALTER TABLE employee ALTER COLUMN id RESTART WITH 5;
ALTER TABLE pet_offering_price_and_duration ALTER COLUMN id RESTART WITH 91;
ALTER TABLE owner ALTER COLUMN id RESTART WITH 6;
ALTER TABLE pet ALTER COLUMN id RESTART WITH 9;
ALTER TABLE appointment ALTER COLUMN id RESTART WITH 25;

