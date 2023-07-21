package school.management.system;

import java.sql.*;

public class Conn {

    String url = "jdbc:mysql://192.168.0.20:3306/ums";
    String user = "client";
    String password = "Client#123";
    
    Connection c;
    Statement s;
    
    Conn() {
        try {
            c = DriverManager.getConnection(url, user, password);
            s = c.createStatement();
                       
            //connection.close(); // Tutup koneksi setelah selesai
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    PreparedStatement prepareStatement(String sql) throws SQLException {
        return c.prepareStatement(sql);
    }
}
