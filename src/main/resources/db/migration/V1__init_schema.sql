INSERT INTO users (uuid, firstname, lastname, username, password)
VALUES ('24f1d94e-c8b6-4ab4-8563-407cf725f37a', 'sidney', 'miranda', 'admin', '{bcrypt}df790ef4-f636-4adb-98e6-a94ebe7e538b');

INSERT INTO role (uuid, name, description)
VALUES ('359d4350-e9ca-4863-a1ed-7e33da97a1a4', 'ROLE_ADMIN', 'Administrador do Sistema');

INSERT INTO users_role (user_id, role_id)
VALUES ('24f1d94e-c8b6-4ab4-8563-407cf725f37a','359d4350-e9ca-4863-a1ed-7e33da97a1a4');