-- Roles
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_ADMIN');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_KITCHEN');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_MOTORIST');

-- Users
INSERT INTO users (user_id, username, password) VALUES (default, 'user', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'admin', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'kitchen', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, password) VALUES (default, 'motorist', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

-- Roles_Users 
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);  -- Assign 'ROLE_USER' to user 'user'
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);  -- Assign 'ROLE_ADMIN' to user 'admin'
INSERT INTO roles_users (role_id, user_id) VALUES (3, 3);  -- Assign 'ROLE_KITCHEN' to user 'kitchen'
INSERT INTO roles_users (role_id, user_id) VALUES (4, 4);  -- Assign 'ROLE_MOTORIST' to user 'motorist'

-- Profiles
INSERT INTO profiles (email, user_id) VALUES ('user@mail.com', 1);
INSERT INTO profiles (email, user_id) VALUES ('admin@mail.com', 2);
INSERT INTO profiles (email, user_id) VALUES ('kitchen@mail.com', 3);
INSERT INTO profiles (email, user_id) VALUES ('motorist@mail.com', 4);
