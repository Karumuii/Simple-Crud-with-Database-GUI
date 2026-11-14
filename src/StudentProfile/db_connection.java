package StudentProfile;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

class db_connection {
   
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement stat;
    
    public void sysConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// The things below the Connection name in MySQL
                                                                                         // schema name
            con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            stat = (Statement) con.createStatement();
        }   catch (ClassNotFoundException ex) {
            System.getLogger(db_connection.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SQLException ex) {
            System.getLogger(db_connection.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
            
    }
    public db_connection (){
        sysConnection();
    }
}




