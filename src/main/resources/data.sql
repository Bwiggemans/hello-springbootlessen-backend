INSERT INTO users (username, password, enabled, email)
VALUES
('user', '$2y$10$WNKmdP9CfbjrheVhvx3ON.ktoFYcYfsqg6OLUFiQfxzeDHaqrb8E6', true, 'dummy@novi.nl'),
('admin', '$2y$10$WNKmdP9CfbjrheVhvx3ON.ktoFYcYfsqg6OLUFiQfxzeDHaqrb8E6', true, 'dummy@novi.nl');

INSERT INTO authorities (username, authority)
VALUES
('user', 'ROLE_USER'),
('admin', 'ROLE_USER'),
('admin', 'ROLE_ADMIN');

INSERT INTO persons (name)
VALUES
('Hans'),
('Jan'),
('Bas');

INSERT INTO books (title, author, isbn, person_id)
VALUES
('Harry Potter en de Steen der Wijzen', 'J.K. Rowling', '9076174083', 1),
('Harry Potter en de Geheime Kamer', 'J.K. Rowling', '9076174121', 2),
('Harry Potter en de Gevangene van Azkaban', 'J.K. Rowling', '9076174148', 3),
('Harry Potter en de Vuurbeker', 'J.K. Rowling', '9076174199', 1),
('Harry Potter en de Orde van de Feniks', 'J.K. Rowling', '906169700X', 2),
('Harry Potter en de Halfbloed Prins', 'J.K. Rowling', '9061697662', 3),
('Harry Potter en de Relieken van de Dood', 'J.K. Rowling', '9789061698326', 1);


