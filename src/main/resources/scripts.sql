CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_role VARCHAR(50) NOT NULL
);

CREATE TABLE memberships (
    membership_id SERIAL PRIMARY KEY,
    membership_type VARCHAR(100) NOT NULL,
    membership_price DECIMAL(10, 2) NOT NULL,
    duration_months INTEGER NOT NULL
);

CREATE TABLE workout_classes (
    class_id SERIAL PRIMARY KEY,
    class_name VARCHAR(100) NOT NULL,
    trainer_name VARCHAR(100) NOT NULL,
    duration_minutes INTEGER NOT NULL,
    schedule VARCHAR(100) NOT NULL
);

ALTER TABLE memberships ADD COLUMN user_id INT REFERENCES users(user_id);

ALTER TABLE memberships
  ADD CONSTRAINT fk_memberships_user
  FOREIGN KEY (user_id)
  REFERENCES users(user_id);


