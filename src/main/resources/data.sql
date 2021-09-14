
/* INSERTS */
INSERT INTO TYPES_EVENTS (NAME, DESCRIPTION)
VALUES ('Report Lixeira', 'Report de status da lixeira');

INSERT INTO TYPES_EVENTS (NAME, DESCRIPTION)
VALUES ('Reativação', 'Lixeira reativada');

INSERT INTO TYPES_EVENTS (NAME, DESCRIPTION)
VALUES ('Outros problemas', 'Outros problemas (texto livre)');

INSERT INTO TYPES_EVENTS (NAME, DESCRIPTION)
VALUES ('Manutenção', 'Problemas no sensor');

INSERT INTO TYPES_EVENTS (NAME, DESCRIPTION)
VALUES ('Fogo', 'Colocaram fogo na lixeira');

INSERT INTO TYPES_EVENTS (NAME, DESCRIPTION)
VALUES ('Outros', 'Lixeira com infiltração');

INSERT INTO ZONES
VALUES (1, 'Norte', 'Prédios lado Ipiranga', 0.0, 0.0);

INSERT INTO ZONES
VALUES (2, 'Leste', 'Prédios lado Colégio', 0.0, 0.0);

INSERT INTO ZONES
VALUES (3, 'Sul', 'Prédios lado Bento Gonçalves', 0.0, 0.0);

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Cheia', 'Lixeira ultrapasou capacidade máxima definida');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Vazia', 'Lixeira está com capacidade abaixo do definido');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Manutenção', 'A lixeira está em manutenção');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Ativa', 'A lixeira está em funcionamento');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Inativa', 'A lixeira está fora de funcionamento');

INSERT INTO TRASH_STATUS (NAME, DESCRIPTION)
VALUES ('Excluída', 'A lixeira não existe');

INSERT INTO TYPE_TRASH
VALUES (1, 'Orgânica', 'Lixo Orgânico');

INSERT INTO TYPE_TRASH
VALUES (2, 'Reciclável', 'Lixo Seco');

INSERT INTO PROFILES
VALUES (1, 'Operador', 'Operador de lixo');

INSERT INTO PROFILES
VALUES (2, 'Gestor', 'Gestor do sistema');

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('REPORT', CURRENT_TIMESTAMP, 'A', 1);

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('Reativação da lixeira', CURRENT_TIMESTAMP, 'R', 2);

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('Problema na lixeira', CURRENT_TIMESTAMP, 'O', 3);

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('Problema na lixeira', CURRENT_TIMESTAMP, 'E', 4);

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('Problema na lixeira', CURRENT_TIMESTAMP, 'E', 5);

INSERT INTO EVENTS (DESCRIPTION, CREATION_DATE, PROBLEM_STATUS, ID_TYPE_EVENTS)
VALUES ('Problema na lixeira', CURRENT_TIMESTAMP, 'E', 6);

INSERT INTO BUILDINGS (NAME, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Prédio 30', 3, -51.17425274979533, -30.060053397925284);

INSERT INTO BUILDINGS (NAME, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Prédio 32', 3, -51.17375798025555, -30.061252819256868);

INSERT INTO BUILDINGS (NAME, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Prédio 15', 2, -51.1745250245122, -30.05938690987892);

INSERT INTO BUILDINGS (NAME, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Prédio 17', 2, -51.17294444568525, -30.0604774916143);

INSERT INTO BUILDINGS (NAME, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Prédio 8', 1, -51.175090021263664, -30.058453659996886);

INSERT INTO BUILDINGS (NAME, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Prédio 7', 1, -51.17531098757787, -30.0579680827421);

INSERT INTO BUILDINGS (NAME, ID_ZONE, LONGITUDE, LATITUDE)
VALUES ('Prédio 6', 1, -51.17442317645368, -30.05816308937127);

INSERT INTO USERS (EMAIL, NAME, LOGIN, PASSWORD, DT_REGISTER, ID_PROFILE, ID_ZONE)
VALUES ('projetogarbus@gmail.com', 'Usuario Default Operador', 'garbus', 'garbus', CURRENT_TIMESTAMP, 1, 2);

INSERT INTO USERS (EMAIL, NAME, LOGIN, PASSWORD, DT_REGISTER, ID_PROFILE, ID_ZONE)
VALUES ('projetogarbus@gmail.com', 'Usuario Gestor Admin', 'admin', 'admin', CURRENT_TIMESTAMP, 2, 1);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Sala 101', 50.0, 50.0, 4, 1, 1, NULL, 'Prédio 30', 0.0, 0.0),
       ('Sanremo', 'Sala 102', 50.0, 40.0, 4, 2, 1, NULL, 'Prédio 30', 0.0, 0.0),
       ('Sanremo', 'Sala 201', 50.0, 30.0, 4, 1, 1, NULL, 'Prédio 30', 0.0, 0.0),
       ('Sanremo', 'Sala 301', 50.0, 10.0, 4, 2, 1, NULL, 'Prédio 30', 0.0, 0.0),
       ('Sanremo', 'Sala 302', 50.0, 60.0, 4, 1, 1, NULL, 'Prédio 30', 0.0, 0.0);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 1), (80, 'YELLOW', 1), (30, 'GREEN', 1),
       (9999999.99, 'RED', 2), (70, 'YELLOW', 2), (30, 'GREEN', 2),
       (9999999.99, 'RED', 3), (60, 'YELLOW', 3), (30, 'GREEN', 3),
       (9999999.99, 'RED', 4), (75, 'YELLOW', 4), (40, 'GREEN', 4),
       (9999999.99, 'RED', 5), (85, 'YELLOW', 5), (40, 'GREEN', 5);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Sala 101', 50.0, 50.0, 4, 1, 2, NULL, 'Prédio 32', 0.0, 0.0),
       ('Sanremo', 'Sala 102', 50.0, 40.0, 4, 2, 2, NULL, 'Prédio 32', 0.0, 0.0),
       ('Sanremo', 'Sala 201', 50.0, 30.0, 4, 1, 2, NULL, 'Prédio 32', 0.0, 0.0),
       ('Sanremo', 'Sala 301', 50.0, 10.0, 4, 2, 2, NULL, 'Prédio 32', 0.0, 0.0),
       ('Sanremo', 'Sala 302', 50.0, 60.0, 3, 1, 2, NULL, 'Prédio 32', 0.0, 0.0);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 6), (80, 'YELLOW', 6), (30, 'GREEN', 6),
       (9999999.99, 'RED', 7), (70, 'YELLOW', 7), (30, 'GREEN', 7),
       (9999999.99, 'RED', 8), (60, 'YELLOW', 8), (30, 'GREEN', 8),
       (9999999.99, 'RED', 9), (75, 'YELLOW', 9), (40, 'GREEN', 9),
       (9999999.99, 'RED', 10), (85, 'YELLOW', 10), (40, 'GREEN', 10);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
VALUES ('Sanremo', 'Sala 101', 50.0, 50.0, 4, 1, 6, NULL, 'Prédio 7', 0.0, 0.0),
       ('Sanremo', 'Sala 102', 50.0, 40.0, 4, 2, 6, NULL, 'Prédio 7', 0.0, 0.0),
       ('Sanremo', 'Sala 201', 50.0, 30.0, 4, 1, 6, NULL, 'Prédio 7', 0.0, 0.0),
       ('Sanremo', 'Sala 301', 50.0, 10.0, 3, 2, 6, NULL, 'Prédio 7', 0.0, 0.0),
       ('Sanremo', 'Sala 302', 50.0, 60.0, 4, 1, 6, NULL, 'Prédio 7', 0.0, 0.0);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 11), (80, 'YELLOW', 11), (30, 'GREEN', 11),
       (9999999.99, 'RED', 12), (70, 'YELLOW', 12), (30, 'GREEN', 12),
       (9999999.99, 'RED', 13), (60, 'YELLOW', 13), (30, 'GREEN', 13),
       (9999999.99, 'RED', 14), (75, 'YELLOW', 14), (40, 'GREEN', 14),
       (9999999.99, 'RED', 15), (85, 'YELLOW', 15), (40, 'GREEN', 15);

INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
VALUES ('Desconhecida', 'Rua da Cultura', 30.0, 20.0, 4, 2, null, 2, 'Rua da Cultura', -51.17369881404314, -30.05813817770304),
       ('Tramontina', 'RU', 30.0, 20.0, 4, 2, null, 1, 'RU', -51.17212436597426, -30.056996063132004),
       ('Sanremo', 'Frente do Prédio 32', 40.0, 30.0, 3, 2, null, 3, 'Frente do Prédio 32', -51.17360291508229, -30.06113649911188),
       ('Desconhecida', 'Entrada da Biblioteca', 35.0, 70.0, 4, 2, null, 2, 'Biblioteca', -51.17377239129156, -30.058777308968025),
       ('Plastisul', 'Atrás do Marista', 30.0, 40.0, 4, 1, null, 2, 'Atrás do Marista', -51.17274876874555, -30.05948219335331),
       ('Cleytinho S/A', 'Mock Físico', 3.0, 0.0, 4, 2, null, 3, 'Maza', -51.174445152282715, -30.062218206226824);

INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
VALUES (9999999.99, 'RED', 16), (66, 'YELLOW', 16), (33, 'GREEN', 16),
       (9999999.99, 'RED', 17), (70, 'YELLOW', 17), (30, 'GREEN', 17),
       (9999999.99, 'RED', 18), (80, 'YELLOW', 18), (50, 'GREEN', 18),
       (9999999.99, 'RED', 19), (85, 'YELLOW', 19), (30, 'GREEN', 19),
       (9999999.99, 'RED', 20), (85, 'YELLOW', 20), (30, 'GREEN', 20),
       (9999999.99, 'RED', 21), (80, 'YELLOW', 21), (40, 'GREEN', 21);

INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, OTHERS, DATE)
VALUES (1, 1, NULL, 20.0, NULL, '2021-06-29 19:32:10.123'),
       (4, 1, 1, 0.0, NULL, '2021-06-28 14:53:10.436'),
       (2, 1, 2, 0.0, NULL, '2021-06-29 16:03:10.1'),
       (1, 1, NULL, 40.0, NULL, '2021-06-29 20:02:05.123'),
       (1, 1, NULL, 50.0, NULL, '2021-06-29 21:32:13.3'),
       (1, 2, NULL, 20.0, NULL, '2021-06-29 16:32:34.123'),
       (1, 2, NULL, 40.0, NULL, '2021-06-29 18:12:54.123'),
       (1, 14, NULL, 3.0, NULL, '2021-06-29 12:10:34.256'),
       (1, 14, NULL, 10.0, NULL, '2021-06-29 18:12:54.123'),
       (5, 14, 2, 0.0, NULL, '2021-07-01 19:18:45.256'),
       (3, 18, 2, 0.0, 'Sumiu', '2021-07-01 19:19:45.17'),
       (1, 10, NULL, 40.0, NULL, '2021-06-29 18:12:54.123'),
       (1, 10, NULL, 60.0, NULL, '2021-06-29 22:11:45.624'),
       (6, 10, 2, 0.0, NULL, '2021-07-01 19:30:45.56'),
       (3, 21, 1, 0.0, 'Não funciona direito esse Arduíno', '2021-07-01 20:25:00.000'),
       (2, 21, 2, 0.0, NULL, '2021-07-02 00:32:00.000');

-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('3M', 'Lixeira Papeleira Amarela', 50.0, 80.0, 2, 2, NULL, 3, 'Pátio de entrada', -51.173494, -30.061085);
--
-- -- PREDIO 32
--
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('Sanremo', 'Lixeira Industrial Sanremo', 50.0, 30.0, 4, 1, NULL, 1, 'Entrada principal', -51.173491, -30.061278);
--
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('Betanin', 'Lixeira Papeleira Vermelha', 50.0, 10.0, 2, 2, NULL, 1, 'Saída biblioteca',-51.173929, -30.060899);
--
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('Plastisul', 'Lixeira Papeleira Verde', 40.0, 38.6, 4, 1, NULL, 2, 'Praça alimentação', -51.173183, -30.060866);
--
-- -- PREDIO 30
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('Sanremo', 'Lixeira Industrial Sanremo', 50.0, 30.0, 4, 1, NULL, 1, 'Entrada Tecnopuc', -51.173985, -30.060236);
--
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('3M', 'Lixeira Papeleira Amarela', 50.0, 80.0, 2, 2, NULL, 3, 'Estacionamento Colégio Marista', -51.174709, -30.059707);
--
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('Betanin', 'Lixeira Papeleira Vermelha', 50.0, 10.0, 2, 2, NULL, 2, 'Gramado', -51.174253, -30.059542);
--
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('Plastisul', 'Lixeira Papeleira Verde', 40.0, 38.6, 4, 1, NULL, 1, 'Espelho ''água', -51.173867, -30.059537);
--
-- -- PREDIO 15
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('Sanremo', 'Lixeira Industrial Sanremo', 50.0, 30.0, 4, 1, NULL, 1, 'Ao lado estacionamento', -51.174347, -30.059354);
--
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('3M', 'Lixeira Papeleira Amarela', 50.0, 80.0, 2, 2, 2, NULL, '4º Andar banheiro', -51.174768, -30.059085);
--
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('Betanin', 'Lixeira Papeleira Vermelha', 50.0, 10.0, 2, 2, 2, NULL, '3º Andar corredor', -51.174277, -30.058892);
--
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('Plastisul', 'Lixeira Papeleira Verde', 40.0, 38.6, 4, 1, 1, null, 'Perto porta saida', -51.173891, -30.058588);
--
--
--
-- --CASA DO CLEY - TERCEIRO ANDAR
-- INSERT INTO TRASHES (BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, LOCAL_DESCRIPTION, LONGITUDE, LATITUDE)
-- VALUES ('Cley SA', 'Cleytinho', 999.9, 30.0, 4, 1, 1, NULL, '2º Andar', -51.175211877770096, -30.062374183731382);

-- INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
-- VALUES (1, 1, 1, 21.2, CURRENT_TIMESTAMP);
--
-- INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
-- VALUES (1, 1, 1, 23.3, CURRENT_TIMESTAMP);
--
-- INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
-- VALUES (1, 1, 1, 53.2, CURRENT_TIMESTAMP);
--
-- INSERT INTO TRASHES_EVENTS (ID_EVENT, ID_TRASH, ID_USER, OCCUPATION, DATE)
-- VALUES (1, 1, 1, 82.2, CURRENT_TIMESTAMP);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 2);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (43.2, 'YELLOW', 2);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (16.8, 'GREEN', 2);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 1);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 1);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 1);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 5);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 5);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 5);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 4);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 4);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 4);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 3);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 3);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 3);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 6);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 6);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 6);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 7);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 7);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 7);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 8);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 8);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 8);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 9);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 9);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 9);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 10);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 10);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 10);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 11);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 11);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 11);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 12);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 12);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 12);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (9999999.99, 'RED', 13);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (70.0, 'YELLOW', 13);
--
-- INSERT INTO TRASHES_THRESHOLD (MAX_OCCUPATION, COLOR, ID_TRASH)
-- VALUES (20.0, 'GREEN', 13);