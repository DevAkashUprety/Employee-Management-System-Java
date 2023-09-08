import java.sql.*;

public class ConnectionClass {
    Connection con;
    Statement stm;
    public ConnectionClass() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/employee";
            String username = "root";
            String password = "root";
            con = DriverManager.getConnection(url, username, password);
            stm=con.createStatement();
//            if (con.isClosed()) {
//                System.out.println("Connection is Closed");
//            } else {
//                System.out.println("Connection Created..");
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ConnectionClass();
    }
}
