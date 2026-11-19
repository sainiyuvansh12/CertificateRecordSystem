USE student_certificate_db;
INSERT INTO users(username,password,active) VALUES('admin','PIET@2026',TRUE)
ON DUPLICATE KEY UPDATE password=VALUES(password), active=VALUES(active);

INSERT INTO students(registration_no,student_name,course,department,semester,email,phone) VALUES
('PIET24CS101','Aarav Sharma','B.Tech.','Computer Science & Engineering',5,'aarav.sharma@example.edu','+919876540101'),
('PIET24CS102','Ananya Verma','B.Tech.','Information Technology',5,'ananya.verma@example.edu','+919876540102'),
('PIET24CS103','Rohan Meena','B.Tech.','Computer Engineering',5,'rohan.meena@example.edu','+919876540103'),
('PIET24CS104','Priya Singh','B.Tech.','Computer Science & Engineering',5,'priya.singh@example.edu','+919876540104'),
('PIET24CS105','Kunal Saini','B.Tech.','Information Technology',5,'kunal.saini@example.edu','+919876540105'),
('PIET24CS106','Neha Gupta','B.Tech.','Computer Engineering',5,'neha.gupta@example.edu','+919876540106'),
('PIET24CS107','Mohit Agarwal','B.Tech.','Computer Science & Engineering',5,'mohit.agarwal@example.edu','+919876540107'),
('PIET24CS108','Kavya Joshi','B.Tech.','Information Technology',5,'kavya.joshi@example.edu','+919876540108'),
('PIET24CS109','Arjun Rathore','B.Tech.','Computer Engineering',5,'arjun.rathore@example.edu','+919876540109'),
('PIET24CS110','Sneha Sharma','B.Tech.','Computer Science & Engineering',5,'sneha.sharma@example.edu','+919876540110'),
('PIET24CS111','Manish Choudhary','B.Tech.','Information Technology',5,'manish.choudhary@example.edu','+919876540111'),
('PIET24CS112','Nisha Jain','B.Tech.','Computer Engineering',5,'nisha.jain@example.edu','+919876540112'),
('PIET24CS113','Rahul Yadav','B.Tech.','Computer Science & Engineering',5,'rahul.yadav@example.edu','+919876540113'),
('PIET24CS114','Pooja Verma','B.Tech.','Information Technology',5,'pooja.verma@example.edu','+919876540114'),
('PIET24CS115','Aditya Singh','B.Tech.','Computer Engineering',5,'aditya.singh@example.edu','+919876540115'),
('PIET24CS116','Ishita Mehta','B.Tech.','Computer Science & Engineering',5,'ishita.mehta@example.edu','+919876540116'),
('PIET24CS117','Devendra Pareek','B.Tech.','Information Technology',5,'devendra.pareek@example.edu','+919876540117'),
('PIET24CS118','Simran Kaur','B.Tech.','Computer Engineering',5,'simran.kaur@example.edu','+919876540118'),
('PIET24CS119','Harsh Vardhan','B.Tech.','Computer Science & Engineering',5,'harsh.vardhan@example.edu','+919876540119'),
('PIET24CS120','Meera Rathore','B.Tech.','Information Technology',5,'meera.rathore@example.edu','+919876540120')
ON DUPLICATE KEY UPDATE student_name=VALUES(student_name),course=VALUES(course),department=VALUES(department),semester=VALUES(semester),email=VALUES(email),phone=VALUES(phone);

INSERT INTO certificate_requests(student_id,certificate_type,request_date,remarks,status,issue_date)
SELECT s.student_id,d.certificate_type,d.request_date,d.remarks,d.status,d.issue_date FROM (
 SELECT 'PIET24CS101' reg,'Bonafide Certificate' certificate_type,'2026-09-02' request_date,'Hostel documentation' remarks,'Issued' status,'2026-09-05' issue_date UNION ALL
 SELECT 'PIET24CS102','Character Certificate','2026-09-04','Internship verification','Processing',NULL UNION ALL
 SELECT 'PIET24CS103','Fee Certificate','2026-09-08','Education loan file','Under Review',NULL UNION ALL
 SELECT 'PIET24CS104','Bonafide Certificate','2026-09-10','Scholarship application','Requested',NULL UNION ALL
 SELECT 'PIET24CS105','Enrollment Certificate','2026-09-12','Competitive examination','Issued','2026-09-16' UNION ALL
 SELECT 'PIET24CS106','Provisional Certificate','2026-09-15','Academic record copy','Under Review',NULL UNION ALL
 SELECT 'PIET24CS107','Migration Certificate','2026-09-17','Transfer application','Processing',NULL UNION ALL
 SELECT 'PIET24CS108','Fee Certificate','2026-09-20','Fee reimbursement','Requested',NULL UNION ALL
 SELECT 'PIET24CS109','Bonafide Certificate','2026-09-22','Travel concession','Issued','2026-09-24' UNION ALL
 SELECT 'PIET24CS110','Character Certificate','2026-09-24','Placement documentation','Processing',NULL UNION ALL
 SELECT 'PIET24CS111','Enrollment Certificate','2026-09-25','Bank documentation','Under Review',NULL UNION ALL
 SELECT 'PIET24CS112','Transfer Certificate','2026-09-26','Academic transfer','Requested',NULL UNION ALL
 SELECT 'PIET24CS113','Fee Certificate','2026-09-27','Scholarship application','Issued','2026-09-29' UNION ALL
 SELECT 'PIET24CS114','Bonafide Certificate','2026-09-28','Internship verification','Processing',NULL UNION ALL
 SELECT 'PIET24CS115','Provisional Certificate','2026-09-29','Further studies','Under Review',NULL UNION ALL
 SELECT 'PIET24CS116','Migration Certificate','2026-09-30','University application','Requested',NULL UNION ALL
 SELECT 'PIET24CS117','Enrollment Certificate','2026-10-01','Examination form','Issued','2026-10-03' UNION ALL
 SELECT 'PIET24CS118','Character Certificate','2026-10-02','Placement documentation','Processing',NULL UNION ALL
 SELECT 'PIET24CS119','Bonafide Certificate','2026-10-03','Hostel documentation','Requested',NULL UNION ALL
 SELECT 'PIET24CS120','Fee Certificate','2026-10-04','Education loan file','Under Review',NULL UNION ALL
 SELECT 'PIET24CS103','Character Certificate','2026-10-05','Scholarship application','Requested',NULL UNION ALL
 SELECT 'PIET24CS107','Bonafide Certificate','2026-10-05','Travel concession','Under Review',NULL UNION ALL
 SELECT 'PIET24CS115','Enrollment Certificate','2026-10-06','Academic record copy','Processing',NULL UNION ALL
 SELECT 'PIET24CS102','Provisional Certificate','2026-10-06','Further studies','Requested',NULL
) d JOIN students s ON s.registration_no=d.reg
WHERE NOT EXISTS (SELECT 1 FROM certificate_requests c WHERE c.student_id=s.student_id AND c.certificate_type=d.certificate_type AND c.request_date=d.request_date);
