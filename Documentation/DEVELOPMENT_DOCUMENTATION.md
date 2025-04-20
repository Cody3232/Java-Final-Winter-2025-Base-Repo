# Gym Management System — Development Documentation

## 1. Project Directory Structure

Java-Final-Winter-2025-Base-Repo/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── keyin/
│   │   │           ├── GymApp.java
│   │   │           ├── database/
│   │   │           │   └── DatabaseConnection.java
│   │   │           ├── memberships/
│   │   │           │   ├── Membership.java
│   │   │           │   ├── MembershipDAO.java
│   │   │           │   └── MembershipService.java
│   │   │           ├── user/
│   │   │           │   ├── User.java
│   │   │           │   ├── UserDao.java
│   │   │           │   ├── UserService.java
│   │   │           │   └── childclasses/
│   │   │           │       ├── Admin.java
│   │   │           │       ├── Member.java
│   │   │           │       └── Trainer.java
│   │   │           └── workoutclasses/
│   │   │               ├── WorkoutClass.java
│   │   │               ├── WorkoutClassDAO.java
│   │   │               └── WorkoutClassService.java
│   │   └── resources/
│   │       └── scripts.sql
└── target/

markdown
Copy
Edit

## 2. Build Process & Dependencies

This is a **Maven** project. All dependencies and build settings live in `pom.xml`.

- **Dependencies**:
  - **PostgreSQL JDBC driver** (`org.postgresql:postgresql:42.7.5`)
  - **jBCrypt** (`org.mindrot:jbcrypt:0.4`)
- **Build**:
  ```bash
  mvn clean install
This command:

Cleans previous build artifacts (target/ folder).

Downloads dependencies.

Compiles source code.

Runs any unit tests (none currently).

Run:

bash
Copy
Edit
mvn compile exec:java -Dexec.mainClass="org.keyin.GymApp"
3. Database Setup for Development
Create the database (if not already):

sql
Copy
Edit
CREATE DATABASE s3javafinal;
Run table‐creation script:

bash
Copy
Edit
psql -h localhost -U postgres -d s3javafinal -f src/main/resources/scripts.sql
This script creates the users, workout_classes, and memberships tables with the necessary columns and constraints.

Verify tables in psql:

sql
Copy
Edit
\d users
\d workout_classes
\d memberships
4. How to Clone & Run the Project
Clone:

bash
Copy
Edit
git clone https://github.com/your‑username/Java‑Final‑Winter‑2025‑Base‑Repo.git
cd Java‑Final‑Winter‑2025‑Base‑Repo
Build & run:

bash
Copy
Edit
mvn clean install
mvn compile exec:java -Dexec.mainClass="org.keyin.GymApp"
Database credentials are in DatabaseConnection.java. Adjust URL, user, and password as needed.

5. Javadoc Documentation
Key classes and methods are documented with Javadoc comments. To generate HTML docs:

bash
Copy
Edit
mvn javadoc:javadoc
After running, open:

bash
Copy
Edit
target/site/apidocs/index.html
Here’s a summary of some key Javadoc entries:

GymApp:

main(String[]) — application entry point.

showAdminMenu(...), showTrainerMenu(...), showMemberMenu(...) — role‐specific menus.

UserService:

addUser(User) — hashes password and inserts user.

loginForUser(String, String) — verifies credentials.

getAllUsers(), deleteUser(String) — user retrieval and deletion.

MembershipService:

addMembership(Membership, int) — links membership to user.

getTotalRevenue() — sums all membership prices.

WorkoutClassService:

addWorkoutClass(WorkoutClass)

getAllWorkoutClasses()

6. Project Structure Explanation
org.keyin.GymApp
Central class for user interaction and menu navigation.

org.keyin.database
DatabaseConnection.java manages the JDBC URL and credentials.

org.keyin.user

User models the user entity.

UserDao handles CRUD operations on the users table.

UserService contains business logic and uses jBCrypt.

org.keyin.memberships
Mirrors user structure:

Membership, MembershipDAO, MembershipService.

org.keyin.workoutclasses
Similar DAO/Service pattern for classes.

7. Additional Notes
Error handling: All database calls are wrapped in try‐catch to print errors without crashing.

Validation: Console inputs are checked (hasNextInt(), empty strings) to avoid invalid entries.

End of Development Documentation