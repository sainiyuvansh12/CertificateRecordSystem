# Student Certificate Record System

**Student:** Yuvansh Saini  
**Registration number:** PIET24CR033  
**Course project:** Advanced Java Programming Lab  
**SDG alignment:** SDG 4 – Quality Education

## Objective

A Java desktop prototype for an institutional records office to maintain student records and track certificate requests from submission through review, processing, and issue.

## Features

- Administrative sign-in backed by MySQL.
- Student create, read, update, delete, and search, with input validation and unique registration numbers.
- Certificate request create, read, update, delete, and search by student name, registration number, request ID, and certificate type.
- Status and certificate-type filters, request and issue dates, remarks, and an overview grouped by actual database status.
- Foreign-key protection against deleting students who have requests.
- Fictional Jaipur-oriented demonstration data (20 students and 24 requests).
- Academic-office visual design with a warm neutral canvas, muted teal identity, top navigation, and shared credit footer.

## Technology

Java 17, Swing/AWT, JDBC, MySQL 8+, and MySQL Connector/J 9.7. No additional framework or build system is required.

## Database setup

1. Start MySQL and execute `database/schema.sql` using a MySQL account permitted to create the database and tables.
2. Execute `database/student_certificate_seed.sql` to add the demo login and fictional student/request data. Seed inserts can be rerun safely.
3. Configure the connection before launch. Defaults are URL `jdbc:mysql://localhost:3306/student_certificate_db?serverTimezone=UTC`, user `root`, and an empty password. Set `CERTIFICATE_DB_URL`, `CERTIFICATE_DB_USER`, and `CERTIFICATE_DB_PASSWORD` to override them.

The schema has `users`, `students`, and `certificate_requests` tables. Requests reference students; registration numbers and usernames are unique. Request status and certificate type are constrained to supported values. Issued records require an issue date.

## Run

From the project folder, compile and launch:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName })
java -cp "out;lib/mysql-connector-j-9.7.0.jar" shopcertificate.Main
```

**Demo login:** username `admin`, password `PIET@2026` (created by the seed SQL). This is a classroom demonstration credential; replace it for any non-demo deployment.

## Project structure

```text
database/
  schema.sql
  student_certificate_seed.sql
lib/
  mysql-connector-j-9.7.0.jar
src/shopcertificate/
  Main.java
  dao/  CertificateRequestDAO.java, StudentDAO.java, UserDAO.java
  db/   DatabaseConnection.java
  model/ CertificateRequest.java, Student.java
  ui/   LoginFrame.java, MainFrame.java
```

## Scope notes

This prototype tracks certificate records and issue status; it does not generate certificate PDFs, send email, or claim integration with live college records. Seed identities and contact details are fictional.
