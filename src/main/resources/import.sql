-- insert admin (username a, password aa)
INSERT INTO IWUser (id, enabled, roles, username, password)
VALUES (1, TRUE, 'ADMIN,MENTOR,USER', 'a',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
INSERT INTO IWUser (id, enabled, roles, username, password)
VALUES (2, TRUE, 'USER', 'b',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
INSERT INTO IWUser (id, enabled, roles, username, password)
VALUES (3, TRUE, 'MENTOR', 'c',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
INSERT INTO IWUser (id, enabled, roles, username, password)
VALUES (975, TRUE, 'USER','LM_DECO_11', '{bcrypt}$2a$10$cXIWsnqFZLsSnpRN917tBuEFCaYcocL/OVVsRNOHhr6XzzWllRVfu');

INSERT INTO "PUBLIC"."TAG" VALUES
(2575, 'asdasd', 'IW'),
(2576, 'Polgrim', 'POLG'),
(2577, 'Easy ANti cheat', 'EAC'),
(2578, 'youtube', 'YT');  

INSERT INTO "PUBLIC"."MENTORING" VALUES
(2625, 'Room 27', TIMESTAMP '2022-06-17 00:00:00', '19:00', '14:00', 'Refuerzo de IW', 975),
(2626, 'Room 82', TIMESTAMP '2022-06-24 00:00:00', '15:00', '12:00', 'Maestria de Login', 975);  

-- start id numbering from a value that is larger than any assigned above
ALTER SEQUENCE "PUBLIC"."GEN" RESTART WITH 1024;
