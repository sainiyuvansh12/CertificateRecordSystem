package shopcertificate.model;

import java.time.LocalDate;

public record CertificateRequest(int id, int studentId, String studentName, String registrationNo,
        String certificateType, LocalDate requestDate, String remarks, String status, LocalDate issueDate) { }
