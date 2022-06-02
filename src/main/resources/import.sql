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
VALUES (975, TRUE, 'USER', 'LM_DECO_11', '{bcrypt}$2a$10$7l0SRmwJha80a5TZFpu/4OBLOKeadXn4aOGa6GcScdo4rVvPBtRwe');
INSERT INTO IWUser (id, enabled, roles, username, password)
VALUES (10625, TRUE, '{bcrypt}$2a$10$9ezvmr7EEIyLrxbnzlsUCevyCvewLGkVDpA7QbJ7u64zD6.AFYa4m', 'ADMIN,MENTOR,USER', 'Admin'); 
INSERT INTO "PUBLIC"."ASESOR" VALUES
(975, TRUE, 'Oscar'),
(976, TRUE, 'Vero'),
(977, TRUE, 'Marta'),
(978, TRUE, 'Julia'),
(979, TRUE, 'Raquel'),
(980, TRUE, 'Montse'),
(981, TRUE, 'Sara'),
(982, TRUE, 'Ana'),
(983, TRUE, 'Mamen'),
(984, TRUE, 'David');  

-- start id numbering from a value that is larger than any assigned above
ALTER SEQUENCE "PUBLIC"."GEN" RESTART WITH 1024;
