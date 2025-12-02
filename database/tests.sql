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
-- O CURRENT_DATE no PostgreSQL retorna a data sem a hora (meia-noite).

-- Hoje
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
      (4, NOW(), (CURRENT_DATE + INTERVAL '9 HOUR') + INTERVAL '1 HOUR', false, NOW(), 'Cliente VIP',
       'Banho, Tosa na máquina, Corte de unha', CURRENT_DATE + INTERVAL '9 HOUR', false, 140.0, 1, 1),

      (5, NOW(), (CURRENT_DATE + INTERVAL '14 HOUR') + INTERVAL '50 MINUTE', false, NOW(), null,
       'Banho, Hidratação', CURRENT_DATE + INTERVAL '14 HOUR', false, 90.0, 2, 2);

-- Ontem (dia -1)
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
    (6, NOW() - INTERVAL '1 DAY', ((CURRENT_DATE - INTERVAL '1 DAY') + INTERVAL '10 HOUR') + INTERVAL '1 HOUR', true, NOW() - INTERVAL '1 DAY', 'Pet muito peludo',
 'Banho, Desembolo', (CURRENT_DATE - INTERVAL '1 DAY') + INTERVAL '10 HOUR', true, 120.0, 3, 3),

(7, NOW() - INTERVAL '1 DAY', ((CURRENT_DATE - INTERVAL '1 DAY') + INTERVAL '15 HOUR') + INTERVAL '120 MINUTE', true, NOW() - INTERVAL '1 DAY', 'Banho relaxante',
 'Banho, Hidratação, Escovação dentária', (CURRENT_DATE - INTERVAL '1 DAY') + INTERVAL '15 HOUR', false, 165.0, 4, 4),

(8, NOW() - INTERVAL '1 DAY', ((CURRENT_DATE - INTERVAL '1 DAY') + INTERVAL '11 HOUR') + INTERVAL '60 MINUTE', true, NOW() - INTERVAL '1 DAY', null,
 'Tosa higiênica, Botinha, Corte de unha', (CURRENT_DATE - INTERVAL '1 DAY') + INTERVAL '11 HOUR', false, 55.0, 1, 5);

-- Dia -2
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
    (9, NOW() - INTERVAL '2 DAY', ((CURRENT_DATE - INTERVAL '2 DAY') + INTERVAL '9 HOUR') + INTERVAL '1 HOUR', true, NOW() - INTERVAL '2 DAY', 'Muito dengosa',
 'Banho, Tosa bebê', (CURRENT_DATE - INTERVAL '2 DAY') + INTERVAL '9 HOUR', false, 100.0, 2, 6),

(10, NOW() - INTERVAL '2 DAY', ((CURRENT_DATE - INTERVAL '2 DAY') + INTERVAL '16 HOUR') + INTERVAL '50 MINUTE', true, NOW() - INTERVAL '2 DAY', null,
 'Banho, Corte de unha', (CURRENT_DATE - INTERVAL '2 DAY') + INTERVAL '16 HOUR', false, 90.0, 3, 7);

-- Dia -3
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
    (11, NOW() - INTERVAL '3 DAY', ((CURRENT_DATE - INTERVAL '3 DAY') + INTERVAL '10 HOUR') + INTERVAL '45 MINUTE', true, NOW() - INTERVAL '3 DAY', 'Pet agitado',
 'Banho, Tosa higiênica, Escovação dentária', (CURRENT_DATE - INTERVAL '3 DAY') + INTERVAL '10 HOUR', false, 80.0, 4, 8),

(12, NOW() - INTERVAL '3 DAY', ((CURRENT_DATE - INTERVAL '3 DAY') + INTERVAL '14 HOUR') + INTERVAL '1 HOUR', true, NOW() - INTERVAL '3 DAY', 'Primeira tosa',
 'Banho, Tosa na máquina', (CURRENT_DATE - INTERVAL '3 DAY') + INTERVAL '14 HOUR', false, 110.0, 1, 1),

(13, NOW() - INTERVAL '3 DAY', ((CURRENT_DATE - INTERVAL '3 DAY') + INTERVAL '11 HOUR') + INTERVAL '50 MINUTE', true, NOW() - INTERVAL '3 DAY', null,
 'Banho, Hidratação, Corte de unha', (CURRENT_DATE - INTERVAL '3 DAY') + INTERVAL '11 HOUR', true, 110.0, 2, 2);

-- Dia -4
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
    (14, NOW() - INTERVAL '4 DAY', ((CURRENT_DATE - INTERVAL '4 DAY') + INTERVAL '9 HOUR') + INTERVAL '1 HOUR', true, NOW() - INTERVAL '4 DAY', 'Gatinha tranquila',
 'Banho, Escovação dentária, Corte de unha', (CURRENT_DATE - INTERVAL '4 DAY') + INTERVAL '9 HOUR', false, 95.0, 3, 3),

(15, NOW() - INTERVAL '4 DAY', ((CURRENT_DATE - INTERVAL '4 DAY') + INTERVAL '15 HOUR') + INTERVAL '120 MINUTE', true, NOW() - INTERVAL '4 DAY', 'Banho completo',
 'Banho, Desembolo, Hidratação', (CURRENT_DATE - INTERVAL '4 DAY') + INTERVAL '15 HOUR', false, 190.0, 4, 4);

-- Dia -5
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
    (16, NOW() - INTERVAL '5 DAY', ((CURRENT_DATE - INTERVAL '5 DAY') + INTERVAL '10 HOUR') + INTERVAL '60 MINUTE', true, NOW() - INTERVAL '5 DAY', 'Pet alegre',
 'Banho, Tosa higiênica, Botinha', (CURRENT_DATE - INTERVAL '5 DAY') + INTERVAL '10 HOUR', false, 85.0, 1, 5),

(17, NOW() - INTERVAL '5 DAY', ((CURRENT_DATE - INTERVAL '5 DAY') + INTERVAL '14 HOUR') + INTERVAL '1 HOUR', true, NOW() - INTERVAL '5 DAY', 'Tosa estilo bebê',
 'Banho, Tosa bebê, Corte de unha', (CURRENT_DATE - INTERVAL '5 DAY') + INTERVAL '14 HOUR', false, 120.0, 2, 6),

(18, NOW() - INTERVAL '5 DAY', ((CURRENT_DATE - INTERVAL '5 DAY') + INTERVAL '16 HOUR') + INTERVAL '50 MINUTE', true, NOW() - INTERVAL '5 DAY', null,
 'Banho, Escovação dentária', (CURRENT_DATE - INTERVAL '5 DAY') + INTERVAL '16 HOUR', false, 75.0, 3, 7);

-- Dia -6
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
    (19, NOW() - INTERVAL '6 DAY', ((CURRENT_DATE - INTERVAL '6 DAY') + INTERVAL '9 HOUR') + INTERVAL '45 MINUTE', true, NOW() - INTERVAL '6 DAY', 'Gato manhoso',
 'Banho, Corte de unha', (CURRENT_DATE - INTERVAL '6 DAY') + INTERVAL '9 HOUR', false, 70.0, 4, 8),

(20, NOW() - INTERVAL '6 DAY', ((CURRENT_DATE - INTERVAL '6 DAY') + INTERVAL '11 HOUR') + INTERVAL '1 HOUR', true, NOW() - INTERVAL '6 DAY', 'Banho express',
 'Banho, Tosa higiênica', (CURRENT_DATE - INTERVAL '6 DAY') + INTERVAL '11 HOUR', false, 120.0, 1, 1),

(21, NOW() - INTERVAL '6 DAY', ((CURRENT_DATE - INTERVAL '6 DAY') + INTERVAL '15 HOUR') + INTERVAL '50 MINUTE', true, NOW() - INTERVAL '6 DAY', 'Cliente frequente',
 'Banho, Hidratação, Corte de unha', (CURRENT_DATE - INTERVAL '6 DAY') + INTERVAL '15 HOUR', true, 110.0, 2, 2);

-- Dia -7
INSERT INTO appointment (
    id, created_at, end_date_time, is_finished, last_update, observations,
    pet_offerings, start_date_time, taxi_service, total_price, employee_id, pet_id
) VALUES
    (22, NOW() - INTERVAL '7 DAY', ((CURRENT_DATE - INTERVAL '7 DAY') + INTERVAL '10 HOUR') + INTERVAL '1 HOUR', true, NOW() - INTERVAL '7 DAY', 'Pet muito fofo',
 'Banho, Tosa bebê, Hidratação', (CURRENT_DATE - INTERVAL '7 DAY') + INTERVAL '10 HOUR', false, 120.0, 3, 3),

(23, NOW() - INTERVAL '7 DAY', ((CURRENT_DATE - INTERVAL '7 DAY') + INTERVAL '14 HOUR') + INTERVAL '120 MINUTE', true, NOW() - INTERVAL '7 DAY', 'Cachorro grande',
 'Banho, Desembolo, Tosa higiênica', (CURRENT_DATE - INTERVAL '7 DAY') + INTERVAL '14 HOUR', false, 170.0, 4, 4),

(24, NOW() - INTERVAL '7 DAY', ((CURRENT_DATE - INTERVAL '7 DAY') + INTERVAL '16 HOUR') + INTERVAL '60 MINUTE', true, NOW() - INTERVAL '7 DAY', null,
 'Banho, Tosa na máquina, Corte de unha', (CURRENT_DATE - INTERVAL '7 DAY') + INTERVAL '16 HOUR', false, 105.0, 1, 5);

-- Para as tabelas com IDENTITY (o PostgreSQL usa sequências internas):
SELECT setval(pg_get_serial_sequence('owner', 'id'), 5, true); -- O próximo será 6
SELECT setval(pg_get_serial_sequence('pet', 'id'), 8, true);   -- O próximo será 9
SELECT setval(pg_get_serial_sequence('appointment', 'id'), 24, true); -- O próximo será 25