
CREATE TABLE IF NOT EXISTS dishes (
    id SERIAL PRIMARY KEY,
    dish_name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS ingredients (
    id SERIAL PRIMARY KEY,
    ingredient_name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS recipes (
    id SERIAL PRIMARY KEY,
    dish_id INTEGER,
    ingredient_id INTEGER,
    weight INTEGER NOT NULL,

    FOREIGN KEY (dish_id) REFERENCES dishes(id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (id) ON DELETE CASCADE
);

INSERT INTO ingredients(ingredient_name) VALUES ('Chicken fillet');
INSERT INTO ingredients(ingredient_name) VALUES ('Tomato');
INSERT INTO ingredients(ingredient_name) VALUES ('Lettuce leaves');
INSERT INTO ingredients(ingredient_name) VALUES ('Parmesan');
INSERT INTO ingredients(ingredient_name) VALUES ('White bread');
INSERT INTO ingredients(ingredient_name) VALUES ('Garlic');
INSERT INTO ingredients(ingredient_name) VALUES ('Mayonnaise');

INSERT INTO dishes(dish_name) VALUES ('Caesar salad');

INSERT INTO recipes(dish_id, ingredient_id, weight) VALUES (1, 1, 150);
INSERT INTO recipes(dish_id, ingredient_id, weight) VALUES (1, 2, 80);
INSERT INTO recipes(dish_id, ingredient_id, weight) VALUES (1, 3, 64);
INSERT INTO recipes(dish_id, ingredient_id, weight) VALUES (1, 4, 50);
INSERT INTO recipes(dish_id, ingredient_id, weight) VALUES (1, 5, 50);
INSERT INTO recipes(dish_id, ingredient_id, weight) VALUES (1, 6, 4);
INSERT INTO recipes(dish_id, ingredient_id, weight) VALUES (1, 7, 25);


