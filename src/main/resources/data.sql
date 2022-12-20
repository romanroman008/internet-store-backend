--INSERT INTO
--    user_role (name, description)
--VALUES
--    ('ADMIN', 'Ma dostęp do wszystkiego'),
--    ('USER', 'Dostęp tylko do odczytu');

CREATE TABLE IF NOT EXISTS product (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    description VARCHAR(1000),
    price FLOAT,
    amount FLOAT,
    PRIMARY KEY (id)
);
--    INSERT INTO
--    product (id,name,description,price,amount)
--    VALUES
--    (1,'Beef jerky - papryka','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',29.99,10),
--     (2,'Beef jerky - czosnek','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',29.99,10),
--      (3,'Beef jerky - sweet-chilli','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',29.99,10),
--       (4,'Beef jerky - honey','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',29.99,10),
--        (5,'Beef jerky - orginal','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',29.99,10),
--         (6,'Beef jerky - sweet-spicy','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',29.99,10),
--          (7,'Beef jerky - spicy','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',29.99,10);

