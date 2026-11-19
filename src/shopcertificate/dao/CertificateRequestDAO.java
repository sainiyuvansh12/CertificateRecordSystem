package shopcertificate.dao;

import shopcertificate.db.DatabaseConnection;
import shopcertificate.model.CertificateRequest;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class CertificateRequestDAO {
    private final DatabaseConnection db;
    public CertificateRequestDAO(DatabaseConnection db){this.db=db;}
    public List<CertificateRequest> search(String query,String status,String type) throws SQLException {
        String sql="SELECT cr.*,s.student_name,s.registration_no FROM certificate_requests cr JOIN students s ON s.student_id=cr.student_id WHERE (s.student_name LIKE ? OR s.registration_no LIKE ? OR CAST(cr.request_id AS CHAR) LIKE ? OR cr.certificate_type LIKE ? OR CAST(cr.request_date AS CHAR) LIKE ?) AND (?='All statuses' OR cr.status=?) AND (?='All types' OR cr.certificate_type=?) ORDER BY cr.request_date DESC,cr.request_id DESC";
        try(Connection c=db.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            String q="%"+query+"%"; for(int i=1;i<=5;i++)p.setString(i,q); p.setString(6,status);p.setString(7,status);p.setString(8,type);p.setString(9,type);
            try(ResultSet r=p.executeQuery()){List<CertificateRequest> out=new ArrayList<>();while(r.next())out.add(map(r));return out;}
        }
    }
    public void save(CertificateRequest r) throws SQLException {
        String sql=r.id()==0?"INSERT INTO certificate_requests(student_id,certificate_type,request_date,remarks,status,issue_date) VALUES(?,?,?,?,?,?)":"UPDATE certificate_requests SET student_id=?,certificate_type=?,request_date=?,remarks=?,status=?,issue_date=? WHERE request_id=?";
        try(Connection c=db.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,r.studentId());p.setString(2,r.certificateType());p.setDate(3,java.sql.Date.valueOf(r.requestDate()));p.setString(4,r.remarks());p.setString(5,r.status());
            if(r.issueDate()==null)p.setNull(6,Types.DATE);else p.setDate(6,java.sql.Date.valueOf(r.issueDate()));if(r.id()!=0)p.setInt(7,r.id());p.executeUpdate();
        }
    }
    public void delete(int id)throws SQLException{try(Connection c=db.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM certificate_requests WHERE request_id=?")){p.setInt(1,id);p.executeUpdate();}}
    public Map<String,Integer> counts() throws SQLException {
        Map<String,Integer> m=new LinkedHashMap<>();for(String s:List.of("Requested","Under Review","Processing","Issued"))m.put(s,0);
        try(Connection c=db.getConnection();PreparedStatement p=c.prepareStatement("SELECT status,COUNT(*) total FROM certificate_requests GROUP BY status");ResultSet r=p.executeQuery()){while(r.next())m.put(r.getString(1),r.getInt(2));}return m;
    }
    private CertificateRequest map(ResultSet r)throws SQLException{java.sql.Date d=r.getDate("issue_date");return new CertificateRequest(r.getInt("request_id"),r.getInt("student_id"),r.getString("student_name"),r.getString("registration_no"),r.getString("certificate_type"),r.getDate("request_date").toLocalDate(),r.getString("remarks"),r.getString("status"),d==null?null:d.toLocalDate());}
}
