import java.sql.*;

public class conn {
    Connection c;
    Statement s;

    public conn(){
        try{
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to your MySQL database
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/PROJECTIGIFinal","root","root");
            // Create a statement object
            s = c.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
