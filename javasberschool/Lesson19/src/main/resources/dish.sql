
INSERT INTO units(unit_name) VALUES ('gram');

INSERT INTO products(product_name) VALUES ('Chicken fillet');
INSERT INTO products(product_name) VALUES ('Tomato');
INSERT INTO products(product_name) VALUES ('Lettuce leaves');
INSERT INTO products(product_name) VALUES ('Parmesan');
INSERT INTO products(product_name) VALUES ('White bread');
INSERT INTO products(product_name) VALUES ('Garlic');
INSERT INTO products(product_name) VALUES ('Mayonnaise');

INSERT INTO dishes(dish_name) VALUES ('Caesar salad');

INSERT INTO ingredients(dish_id, product_id, value, unit_id) VALUES (1, 1, 150, 1);
INSERT INTO ingredients(dish_id, product_id, value, unit_id) VALUES (1, 2, 80, 1);
INSERT INTO ingredients(dish_id, product_id, value, unit_id) VALUES (1, 3, 64, 1);
INSERT INTO ingredients(dish_id, product_id, value, unit_id) VALUES (1, 4, 50, 1);
INSERT INTO ingredients(dish_id, product_id, value, unit_id) VALUES (1, 5, 50, 1);
INSERT INTO ingredients(dish_id, product_id, value, unit_id) VALUES (1, 6, 4, 1);
INSERT INTO ingredients(dish_id, product_id, value, unit_id) VALUES (1, 7, 25, 1);