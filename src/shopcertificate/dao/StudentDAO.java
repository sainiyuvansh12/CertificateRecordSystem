package shopcertificate.dao;

import shopcertificate.db.DatabaseConnection;
import shopcertificate.model.Student;
import java.sql.*;
import java.util.*;

public class StudentDAO {
    private final DatabaseConnection db;
    public StudentDAO(DatabaseConnection db) { this.db=db; }
    public List<Student> search(String query) throws SQLException {
        String sql="SELECT * FROM students WHERE registration_no LIKE ? OR student_name LIKE ? OR course LIKE ? ORDER BY student_name";
        try(Connection c=db.getConnection(); PreparedStatement p=c.prepareStatement(sql)) {
            String q="%"+query+"%"; p.setString(1,q); p.setString(2,q); p.setString(3,q);
            try(ResultSet r=p.executeQuery()) { List<Student> out=new ArrayList<>(); while(r.next()) out.add(map(r)); return out; }
        }
    }
    public void save(Student s) throws SQLException {
        String sql=s.id()==0 ? "INSERT INTO students(registration_no,student_name,course,department,semester,email,phone) VALUES(?,?,?,?,?,?,?)" :
                "UPDATE students SET registration_no=?,student_name=?,course=?,department=?,semester=?,email=?,phone=? WHERE student_id=?";
        try(Connection c=db.getConnection(); PreparedStatement p=c.prepareStatement(sql)) {
            bind(p,s); if(s.id()!=0)p.setInt(8,s.id()); p.executeUpdate();
        }
    }
    private void bind(PreparedStatement p, Student s) throws SQLException {
        p.setString(1,s.registrationNo()); p.setString(2,s.name()); p.setString(3,s.course()); p.setString(4,s.department());
        p.setInt(5,s.semester()); p.setString(6,s.email()); p.setString(7,s.phone());
    }
    public void delete(int id) throws SQLException { try(Connection c=db.getConnection(); PreparedStatement p=c.prepareStatement("DELETE FROM students WHERE student_id=?")){p.setInt(1,id);p.executeUpdate();} }
    private Student map(ResultSet r) throws SQLException { return new Student(r.getInt("student_id"),r.getString("registration_no"),r.getString("student_name"),r.getString("course"),r.getString("department"),r.getInt("semester"),r.getString("email"),r.getString("phone")); }
}
