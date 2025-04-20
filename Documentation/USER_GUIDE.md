# Gym Management System — User Guide

## 1. Introduction

Welcome to the **Gym Management System**, a simple console‑based Java application that lets three types of users manage gym data:

- **Admin**: View all users, delete users, and see total membership revenue.  
- **Trainer**: Add new workout classes.  
- **Member**: Browse available classes and purchase memberships.

The system uses **PostgreSQL** to store data, **Maven** for build automation, and **jBCrypt** for secure password hashing.

---

## 2. Prerequisites

Make sure you have the following installed and running:

1. **Java Development Kit (JDK) 23**  
   ```bash
   java -version
   # should report something like: openjdk version "23.x.x"
Maven

bash
Copy
Edit
mvn -version
# should show your Maven version and Java home
PostgreSQL

Default port: 5432

Note your DB name, username, and password.

A terminal or command‑prompt interface.

3. Setup & Installation
Clone the repository

bash
Copy
Edit
git clone https://github.com/your‑username/Java‑Final‑Winter‑2025‑Base‑Repo.git
cd Java‑Final‑Winter‑2025‑Base‑Repo
Create the database tables
In psql, run:

sql
Copy
Edit
-- users table
CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  user_name VARCHAR(100) NOT NULL,
  user_password VARCHAR(255) NOT NULL,
  user_role VARCHAR(50) NOT NULL
);

-- workout_classes table
CREATE TABLE workout_classes (
  class_id SERIAL PRIMARY KEY,
  class_name VARCHAR(100) NOT NULL,
  trainer_name VARCHAR(100) NOT NULL,
  duration_minutes INTEGER NOT NULL,
  schedule VARCHAR(100) NOT NULL
);

-- memberships table
CREATE TABLE memberships (
  membership_id SERIAL PRIMARY KEY,
  membership_type VARCHAR(100) NOT NULL,
  membership_price DECIMAL(10,2) NOT NULL,
  duration_months INTEGER NOT NULL,
  user_id INT REFERENCES users(user_id)
);
Or run the provided script:

bash
Copy
Edit
psql -h localhost -U postgres -d s3javafinal -f src/main/resources/scripts.sql
Configure database connection
Edit src/main/java/org/keyin/database/DatabaseConnection.java:

java
Copy
Edit
private static final String URL      = "jdbc:postgresql://localhost:5432/s3javafinal";
private static final String USER     = "postgres";
private static final String PASSWORD = "password";
Build the project

bash
Copy
Edit
mvn clean install
4. Running the Application
From the project root, run:

bash
Copy
Edit
mvn clean compile exec:java -Dexec.mainClass="org.keyin.GymApp"
You will see the main menu:

sql
Copy
Edit
=== Gym Management System ===
1. Add a new user
2. Login as a user
3. Exit
Enter your choice:
Type the option number and press Enter to proceed.

5. Role Walkthroughs
5.1 Registering & Logging In
Add a new user

At main menu: type 1 → Enter.

Follow prompts:

mathematica
Copy
Edit
Enter username: alice
Enter password: pass123
Enter role (Admin/Trainer/Member): Member
Success message: User added successfully!

Log in

At main menu: type 2 → Enter.

Enter your credentials:

yaml
Copy
Edit
Enter username: alice
Enter password: pass123
Success: Login Successful! Welcome alice

5.2 Admin Menu
After logging in as Admin (role = Admin), you see:

sql
Copy
Edit
=== Admin Menu ===
1. View all users
2. View total revenue from memberships
3. Delete a user
4. Back to main menu
Enter your choice:
1: Lists usernames & roles.

2: Shows total membership revenue.

3: Prompts Enter username of user you wish to delete: → deletes that user.

4: Returns to the main menu.

5.3 Trainer Menu
After logging in as Trainer (role = Trainer), you see:

mathematica
Copy
Edit
=== Trainer Menu ===
1. Add a workout class
2. Back to main menu
Enter your choice:
1: Prompts for class details:

yaml
Copy
Edit
Enter name of class: Yoga
Enter name of trainer: John Doe
Enter duration of workout in minutes: 60
Enter schedule: Mon/Wed/Fri 9AM
Success: Your workout class has been successfully added!

2: Returns to main menu.

5.4 Member Menu
After logging in as Member (role = Member), you see:

css
Copy
Edit
 Member Menu 
1. View all workout classes
2. Purchase a membership
3. Back to main menu
Please enter your choice:
1: Lists all workout classes with name, trainer, duration, schedule.

2: Prompts:

mathematica
Copy
Edit
=== Purchase a Membership ===
Enter membership type (e.g., Basic, Premium): Premium
Enter membership price: 49.99
Enter membership duration (in months): 3
Success: Membership purchased successfully!

3: Returns to main menu.

6. Error Handling & Validation
Non‑numeric input where numbers are expected (e.g., price/duration) prompts you to retry.

Empty or invalid role during registration shows an error and aborts.

Invalid menu choice (not in allowed range) displays “Invalid choice.” and re‑prompts.

Database errors (e.g., missing tables) surface as clear SQL error messages—see Troubleshooting.

7. Example Session
text
Copy
Edit
$ mvn clean compile exec:java -Dexec.mainClass="org.keyin.GymApp"

=== Gym Management System ===
1. Add a new user
2. Login as a user
3. Exit
Enter your choice: 1
Enter username: admin
Enter password: adminpass
Enter role (Admin/Trainer/Member): Admin
User added successfully!

=== Gym Management System ===
Enter your choice: 2
Enter username: admin
Enter password: adminpass
Login Successful! Welcome admin

=== Admin Menu ===
1. View all users
2. View total revenue from memberships
3. Delete a user
4. Back to main menu
Enter your choice: 1
Username: admin
Role: Admin
---------------------------
Enter your choice: 4

=== Gym Management System ===
Enter your choice: 3
Exiting the program...
8. Troubleshooting
“relation ‘memberships’ does not exist”
→ Run the CREATE TABLE SQL for memberships (see Section 3).

Cannot connect to database
→ Verify URL, USER, PASSWORD in DatabaseConnection.java.

Login failures
→ Ensure you entered the correct role (Admin, Trainer, or Member) when registering.

Compilation errors
→ Run mvn clean compile and check for missing imports (e.g., import org.keyin.memberships.Membership;).

End of User Guide

