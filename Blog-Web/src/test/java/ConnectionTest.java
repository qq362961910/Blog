import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionTest {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog?useSSL=false&characterEncoding=UTF-8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "vr", "123456");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from blog_user");
        while (rs.next()) {
            int id = rs.getInt(1);
            System.out.println("id: " + id);
        }
        conn.close();
        stmt.close();
        rs.close();

        JsonFormat.Value value = new JsonFormat.Value();
    }
}
