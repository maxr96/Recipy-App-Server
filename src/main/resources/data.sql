DROP TABLE IF EXISTS recipe;

CREATE TABLE recipe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(250) NOT NULL,
    title VARCHAR(250) NOT NULL,
    time INT NOT NULL,
    author VARCHAR(50) NOT NULL
);

INSERT INTO recipe (description, title, time, author) VALUES
    ('Desc', 'Title', 1234, 'Me'),
    ('Desc2', 'Awesome Title', 2347, 'You');