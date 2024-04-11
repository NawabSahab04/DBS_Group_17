import java.sql.*;

public class conn {
    Connection c;
    Statement s;

    public conn(){
        try{
            Class.forName("ojdbc11.oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectigi","root","root");
            s = c.createStatement();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
