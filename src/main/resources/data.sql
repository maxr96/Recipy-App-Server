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
--
--     CREATE OR REPLACE TABLE recipe (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     description VARCHAR(250) NOT NULL,
--     title VARCHAR(250) NOT NULL,
--     time INT NOT NULL,
--     author VARCHAR(50) NOT NULL,
--     ingredient_amount_id INT NOT NULL,
--     foreign key (ingredient_amount_id) references ingredient_amount
-- ) ENGINE=MyISAM;
--
--  CREATE OR REPLACE TABLE ingredient_amount (
--         id INT AUTO_INCREMENT PRIMARY KEY,
--         unit_id INT NOT NULL,
--         ingredient_id INT NOT NULL,
--         foreign key (unit_id) references unit.id,
--         foreign key (ingredient_id) references ingredient.id,
--         UNIQUE(unit_id),
--         UNIQUE(ingredient_id)
--    ) ENGINE=MyISAM;
--
--    CREATE OR REPLACE TABLE ingredient (
--        id INT AUTO_INCREMENT PRIMARY KEY,
--        name VARCHAR(50) NOT NULL
--    ) ENGINE=MyISAM;
--
--      CREATE OR REPLACE TABLE unit (
--    		id INT AUTO_INCREMENT PRIMARY KEY,
--        name VARCHAR(50) NOT NULL
--    ) ENGINE=MyISAM;
--
-- INSERT INTO recipe (description, title, time, author, ingredient_amount_id) VALUES
--     ('Desc3', 'Awesome Title2', 23475, 'You', 1);
--
--
--   INSERT INTO ingredient_amount (unit_id, ingredient_id) VALUES (1, 1)
--
--   INSERT INTO ingredient (name) VALUES ('buckwheat')
--
--   INSERT INTO unit (name) VALUES ('gram')
