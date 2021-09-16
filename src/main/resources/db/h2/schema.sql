DROP TABLE IF EXISTS purchases;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS developers;


CREATE TABLE developers (
    id         INT IDENTITY PRIMARY KEY,
    name       VARCHAR(50),
    about      VARCHAR(255),
    updated_at TIMESTAMP(3) AS CURRENT_TIMESTAMP
);

CREATE TABLE games (
    id           INT IDENTITY PRIMARY KEY,
    title        VARCHAR(100),
    price        DECIMAL(7, 2),
    release_date DATE,
    online       BOOLEAN,
    developer_id INT NOT NULL,
    updated_at   TIMESTAMP(3) AS CURRENT_TIMESTAMP
);
ALTER TABLE games
    ADD CONSTRAINT fk_games_developers FOREIGN KEY (developer_id) REFERENCES developers (id);

CREATE TABLE customers (
    id           INT IDENTITY PRIMARY KEY,
    username     VARCHAR(25),
    email        VARCHAR(255),
    country_code VARCHAR(2),
    city         VARCHAR(100),
    postcode     VARCHAR(10),
    updated_at   TIMESTAMP(3) AS CURRENT_TIMESTAMP
);

CREATE TABLE purchases (
    id          INT IDENTITY PRIMARY KEY,
    game_id     INT NOT NULL,
    customer_id INT NOT NULL,
    amount      INT,
    updated_at  TIMESTAMP(3) AS CURRENT_TIMESTAMP
);
ALTER TABLE purchases
    ADD CONSTRAINT fk_purchases_games FOREIGN KEY (game_id) REFERENCES games (id);
ALTER TABLE purchases
    ADD CONSTRAINT fk_purchases_customers FOREIGN KEY (customer_id) REFERENCES customers (id);
