# Simple CRUD with Database

A simple Java desktop application that performs **CRUD** (Create, Read, Update, Delete) operations on student records stored in a **MySQL** database. Built as a NetBeans (Ant) project and connected to MySQL through JDBC.

## Features

- Login using accounts stored in the database
- Add new student records
- View existing student records
- Update student information
- Delete student records

## Tech Stack

| Component | Details |
|-----------|---------|
| Language | Java |
| IDE / Build | Apache NetBeans (Ant, `build.xml`) |
| Database | MySQL 8.x |
| DB Tool | MySQL Workbench |
| Driver | MySQL Connector/J 9.6.0 (`mysql-connector-j-9.6.0.jar`, included) |

## Project Structure

```
Simple-Crud-with-Database/
├── nbproject/                     # NetBeans project settings
├── src/                           # Java source files
├── build.xml                      # Ant build script
├── database.sql                   # Database schema + sample data
├── manifest.mf
└── mysql-connector-j-9.6.0.jar    # MySQL JDBC driver
```

## Database Overview

The script creates a database named **`student`** with two tables:

| Table | Columns |
|-------|---------|
| `login` | `UserId` (PK, auto-increment), `username`, `password` |
| `student_information` | `UserId` (PK, auto-increment), `firstname`, `middlename`, `lastname`, `age` |

---

## Prerequisites

Install the following before you start:

1. **JDK 8 or newer**
2. **Apache NetBeans** (any recent version)
3. **MySQL Server 8.x** (running on your machine)
4. **MySQL Workbench**

---

## Setup Guide

### 1. Clone the repository

```bash
git clone https://github.com/Karumuii/Simple-Crud-with-Database.git
```

Or click **Code → Download ZIP** on GitHub and extract it.

### 2. Import `database.sql` into MySQL Workbench

Make sure your MySQL server is running first.

**Option A: Data Import Wizard (recommended)**

1. Open **MySQL Workbench** and connect to your local instance (usually `Local instance MySQL80`). Enter your root password when asked.
2. In the top menu, go to **Server → Data Import**.
3. Select **Import from Self-Contained File** and click **...** to browse to the `database.sql` file from this repository.
4. For **Default Target Schema**, you can leave it empty. The script already contains `CREATE DATABASE IF NOT EXISTS student` and `USE student`.
5. Click **Start Import** at the bottom right.
6. When it says *Import completed*, go to the **Navigator** panel on the left, open the **Schemas** tab, and click the refresh icon. You should now see the `student` database with the `login` and `student_information` tables.

**Option B: Run the script directly**

1. In MySQL Workbench, go to **File → Open SQL Script...** and choose `database.sql`.
2. Click the **lightning bolt** icon (Execute) to run the whole script.
3. Refresh the **Schemas** panel to see the new database.

**Verify the import** by running this in a new query tab:

```sql
USE student;
SELECT * FROM login;
SELECT * FROM student_information;
```

### 3. Open the project in NetBeans

1. Open **NetBeans**.
2. Go to **File → Open Project...** and select the project folder (the one containing `nbproject` and `src`).
3. Make sure the MySQL driver is attached. In the **Projects** panel, expand your project, right-click **Libraries → Add JAR/Folder...**, and select `mysql-connector-j-9.6.0.jar` from the project folder. (Skip this if it already appears under Libraries.)

### 4. Configure the database connection (change the password)

The app connects to MySQL using JDBC, so you must set it to match **your own** MySQL setup.

1. In NetBeans, press **Ctrl + Shift + F** (Find in Projects) and search for `getConnection` to find the file that connects to the database. It is inside the `src` folder.
2. You will find something similar to this:

```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/student",  // URL / host / port / database name
    "root",                                  // your MySQL username
    "your_password_here"                     // your MySQL password
);
```

3. Update the values:

| Setting | What to put | Notes |
|---------|-------------|-------|
| URL | `jdbc:mysql://localhost:3306/student` | Change `3306` if your MySQL uses another port. Keep `student` as the database name. |
| Username | `root` | Or any MySQL user that has access to the `student` database. |
| Password | Your MySQL password | This is the password you set when installing MySQL or the one you use to log in to Workbench. |

> **Tip:** The password in the code must be the same one you use to open your connection in MySQL Workbench. If Workbench connects, the same credentials will work in the app.

4. Save the file.

### 5. Run the project

1. Right-click the project in the **Projects** panel and choose **Clean and Build**.
2. Click **Run Project** (green play button) or press **F6**.
3. Log in using an account from the `login` table (check it in Workbench with `SELECT * FROM login;`) and start managing student records.

---

## Troubleshooting

| Error | Likely cause and fix |
|-------|----------------------|
| `Access denied for user 'root'@'localhost'` | Wrong username or password in the code. Use the same credentials as your Workbench connection. |
| `Communications link failure` | MySQL server is not running, or the port is wrong. Start the server (Windows: **Services → MySQL80 → Start**) and check the port in the URL. |
| `Unknown database 'student'` | The SQL file was not imported. Repeat Step 2 and refresh the Schemas panel. |
| `No suitable driver found` / `ClassNotFoundException` | The Connector/J jar is not attached. Redo Step 3 (Libraries → Add JAR/Folder). |
| SSL or `Public Key Retrieval is not allowed` | Change the URL to `jdbc:mysql://localhost:3306/student?useSSL=false&allowPublicKeyRetrieval=true` |
| Forgot your MySQL root password | Reset it through the MySQL installer (**Reconfigure → Accounts and Roles**) or the official MySQL password reset guide. |

---

## Notes

- `database.sql` uses `DROP TABLE IF EXISTS`, so **re-importing will reset both tables** to the sample data.
- Passwords in the `login` table are stored as **plain text** for learning purposes only. Do not use this approach in a real application. Hash passwords (for example with BCrypt) instead.
- Never commit your real database password to a public repository.

---

## Credits

Created by **Karumuii** as a school project for BSIT.

Built with:
- Java and Apache NetBeans (Ant)
- MySQL 8.x and MySQL Workbench
- MySQL Connector/J 9.6.0

If you reuse or modify this project, please keep this credit.

## Note for Users: Rename Before Use

This project was made as a personal school activity, so the source code
(class names, package names, window titles, labels, and comments) may contain
the original author's name and section.

Before using it as your own, refactor these:

1. In NetBeans, right-click the class or package and choose **Refactor → Rename** so references update automatically.
2. Use **Edit → Find in Projects** (Ctrl+Shift+F) to search for the author's name and section, then replace them.
3. Check UI text such as form titles, labels, and "About" or header text.
4. Check comments and file headers.
5. Rename the project itself (right-click the project → **Rename**) and update `build.xml` and `manifest.mf` if the project or main class name changes.

Please don't submit this project under the original author's name or section.
