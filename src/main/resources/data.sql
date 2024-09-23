-- Roles
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_ADMIN');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_KITCHEN');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_MOTORIST');


-- Users
INSERT INTO users (user_id, username, password) VALUES (default, 'user', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'admin', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

-- Roles_Users (Ensure role_id and user_id are correctly mapped)
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);  -- Assign 'ROLE_USER' to user 'pepe'
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);  -- Assign 'ROLE_ADMIN' to user 'pepa'

-- Profiles
INSERT INTO profiles (email, user_id) VALUES
('user@mail.com', 1),
('admin@mail.com', 2);