# Gym Management System

A console‑based Java application for managing gym users, memberships, and workout classes. Supports three roles (Admin, Trainer, Member) with PostgreSQL persistence and BCrypt password hashing.

## Features

- **Admin**: View/delete users, view total membership revenue  
- **Trainer**: Create/update/delete workout classes  
- **Member**: Browse classes, purchase memberships  

## Quick Start

1. **Clone**  
   ```bash
   git clone https://github.com/your‑username/Java‑Final‑Winter‑2025‑Base‑Repo.git
   cd Java‑Final‑Winter‑2025‑Base‑Repo
Setup Database

bash
Copy
Edit
psql -h localhost -U postgres -d s3javafinal -f src/main/resources/scripts.sql
Configure Connection
Edit src/main/java/org/keyin/database/DatabaseConnection.java with your DB URL, user, and password.

Build & Run

bash
Copy
Edit
mvn clean compile exec:java -Dexec.mainClass="org.keyin.GymApp"