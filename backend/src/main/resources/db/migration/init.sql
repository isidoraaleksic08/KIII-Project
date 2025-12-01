
CREATE TABLE countries (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           continent VARCHAR(255) NOT NULL
);

CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         first_name VARCHAR(255) NOT NULL,
                         last_name VARCHAR(255) NOT NULL,
                         country_id INT NOT NULL,
                         FOREIGN KEY (country_id) REFERENCES countries(id)
);

CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       category_id INT NOT NULL,
                       author_id INT NOT NULL,
                       copies INT NOT NULL,
                       publish_date TIMESTAMP NOT NULL,
                       FOREIGN KEY (category_id) REFERENCES categories(id),
                       FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL
);

INSERT INTO countries (name, continent) VALUES
                                            ('Country1', 'Continent1'),
                                            ('Country2', 'Continent2'),
                                            ('Country3', 'Continent3');

INSERT INTO categories (name) VALUES
                                  ('Fantasy'),
                                  ('Biography'),
                                  ('Drama'),
                                  ('Novel');

INSERT INTO authors (first_name, last_name, country_id) VALUES
                                                            ('Name1', 'Surname1', 2),
                                                            ('Name2', 'Surname2', 3),
                                                            ('Name3', 'Surname3', 1);

INSERT INTO books (title, category_id, author_id, copies, publish_date) VALUES
                                                                            ('Book1', 3, 2, 3, '2020-05-08 05:15:00'),
                                                                            ('Book2', 4, 3, 6, '2024-03-02 04:12:00'),
                                                                            ('Book3', 2, 1, 2, '2025-03-04 01:23:00'),
                                                                            ('Book4', 2, 1, 2, '2025-03-04 01:23:00'),
                                                                            ('Book5', 2, 1, 2, '2025-03-04 01:23:00'),
                                                                            ('Book6', 2, 1, 2, '2025-03-04 01:23:00'),
                                                                            ('Book7', 2, 1, 2, '2025-03-04 01:23:00'),
                                                                            ('Book8', 2, 1, 2, '2025-03-04 01:23:00'),
                                                                            ('Book9', 2, 1, 2, '2025-03-04 01:23:00'),
                                                                            ('Book10', 2, 1, 2, '2025-03-04 01:23:00'),
                                                                            ('Book11', 2, 1, 2, '2025-03-04 01:23:00');
INSERT INTO users (username, password, first_name, last_name, role) VALUES

                                                                        ('at', 'passwordHashForAt', 'Ana', 'Todorovska', 'ROLE_Librarian'),
                                                                        ('user', 'passwordHashForUser', 'User', 'User', 'ROLE_USER');
