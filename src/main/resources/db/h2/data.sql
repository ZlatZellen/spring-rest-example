INSERT INTO developers (id, name, about)
VALUES (1, 'Capcom', 'Japanese video game developer and publisher'),
       (2, 'Crytek', 'German video game developer and software developer'),
       (3, 'Electronic Arts', 'American video game company headquartered in Redwood City');

INSERT INTO games (id, title, price, release_date, online, developer_id)
VALUES (1, 'Far Cry', 419, '2004-03-04', FALSE, 2),
       (2, 'Dead Space', 999, '2008-10-08', FALSE, 3),
       (3, 'Resident Evil 4', 699, '2014-02-28', FALSE, 1);

INSERT INTO customers (id, username, email, country_code, city, postcode)
VALUES (1, 'Charlie', 'charlie@protonmail.com', 'US', 'New York City', '10003'),
       (2, 'Jack', 'jack@gmail.com', 'UK', 'City of London', 'E1 7AF'),
       (3, 'Mia', 'mia@protonmail.ch', 'CH', 'Geneva', '1203');

INSERT INTO purchases (id, game_id, customer_id, amount)
VALUES (1, 1, 2, 3),
       (2, 3, 3, 1),
       (3, 2, 3, 2),
       (4, 3, 2, 1);
