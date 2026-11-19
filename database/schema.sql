CREATE DATABASE IF NOT EXISTS student_certificate_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE student_certificate_db;

CREATE TABLE IF NOT EXISTS users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(40) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS students (
  student_id INT AUTO_INCREMENT PRIMARY KEY,
  registration_no VARCHAR(24) NOT NULL UNIQUE,
  student_name VARCHAR(100) NOT NULL,
  course VARCHAR(100) NOT NULL,
  department VARCHAR(120) NOT NULL,
  semester TINYINT NOT NULL,
  email VARCHAR(120) NOT NULL,
  phone VARCHAR(16) NOT NULL,
  CONSTRAINT chk_semester CHECK (semester BETWEEN 1 AND 8)
);
CREATE TABLE IF NOT EXISTS certificate_requests (
  request_id INT AUTO_INCREMENT PRIMARY KEY,
  student_id INT NOT NULL,
  certificate_type ENUM('Bonafide Certificate','Character Certificate','Transfer Certificate','Migration Certificate','Enrollment Certificate','Fee Certificate','Provisional Certificate') NOT NULL,
  request_date DATE NOT NULL,
  remarks VARCHAR(500) NOT NULL DEFAULT '',
  status ENUM('Requested','Under Review','Processing','Issued') NOT NULL DEFAULT 'Requested',
  issue_date DATE NULL,
  CONSTRAINT fk_request_student FOREIGN KEY(student_id) REFERENCES students(student_id) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT chk_issue_date CHECK ((status='Issued' AND issue_date IS NOT NULL) OR (status<>'Issued' AND issue_date IS NULL))
);
