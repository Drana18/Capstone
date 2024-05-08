DROP DATABASE IF EXISTS ExpenseDatabase;

CREATE DATABASE ExpenseDatabase;

USE ExpenseDatabase;

CREATE TABLE users (
  users_id int PRIMARY KEY auto_increment,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(100) NOT NULL
);

CREATE TABLE categories (
  categories_id int  PRIMARY KEY auto_increment,
  name VARCHAR(50) NOT NULL,
  description TEXT
);

CREATE TABLE expenses (
   expenses_id int PRIMARY KEY auto_increment,
   date DATE NOT NULL,
   description TEXT,
   amount DECIMAL(10, 2) NOT NULL,
   category_id INTEGER NOT NULL,
   user_id INTEGER NOT NULL,
   constraint fk_expenses_categories_id
	FOREIGN KEY (category_id) REFERENCES categories(categories_id),
   constraint fk_expenses_user_id
	FOREIGN KEY (user_id) REFERENCES users(users_id)
);



CREATE TABLE budgets (
  budgets_id  int PRIMARY KEY auto_increment,
  amount DECIMAL(10, 2) NOT NULL,
  category_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  
  constraint fk_budgets_categories_id
	FOREIGN KEY (category_id) REFERENCES categories(categories_id),
  
  constraint fk_budgets_user_id
	FOREIGN KEY (user_id) REFERENCES users(users_id)
);





-- -- Seed users
INSERT INTO users (username, password, email)
VALUES ('john_doe', 'password123', 'john@example.com'),
       ('jane_smith', 'password456', 'jane@example.com'),
       ('admin_user', 'adminpass', 'admin@example.com');

-- -- Seed categories
INSERT INTO categories (name, description)
VALUES ('Food', 'Expenses related to food purchases'),
       ('Utilities', 'Bills for utilities such as electricity, water, and gas'),
       ('Transportation', 'Expenses related to transportation, such as fuel and public transit'),
       ('Entertainment', 'Expenses for leisure activities and entertainment');

-- -- Seed expenses
INSERT INTO expenses (date, description, amount, category_id, user_id)
VALUES ('2024-04-01', 'Groceries', 50.00, 1, 1),
       ('2024-04-02', 'Electricity bill', 100.00, 2, 2),
       ('2024-04-03', 'Gasoline', 40.00, 3, 1),
       ('2024-04-04', 'Dinner with friends', 75.00, 4, 2);


-- -- Seed budgets
INSERT INTO budgets (amount, category_id, user_id, start_date, end_date)
VALUES (200.00, 1, 1, '2024-04-01', '2024-04-30'),
       (150.00, 2, 2, '2024-04-01', '2024-04-30'),
       (100.00, 3, 1, '2024-04-01', '2024-04-30'),
       (50.00, 4, 2, '2024-04-01', '2024-04-30');