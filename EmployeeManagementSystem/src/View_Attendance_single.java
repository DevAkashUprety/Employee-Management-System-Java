import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class View_Attendance_single extends JFrame {
    JTable t;
    String x[] = {"Employee ID", "Name", "Email", "First Half", "Second Half", "Date"};
    String y[][] = new String[26][6];
    int i = 0, j = 0;
    Font f, f1;

    View_Attendance_single(String Eid) {
        super("Particular Employee Attendance Records");
        setSize(1480, 500);
        setLocation(250, 250);
        f = new Font("MS UI Gothic", Font.BOLD, 17);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EMS";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String q = "SELECT * FROM attendence WHERE eid='"+ Eid + "'";
            ResultSet rs = stmt.executeQuery(q);
            int count = 0;
            while (rs.next()) {
                count++;
                y[i][j++] = rs.getString("eid");
                y[i][j++] = rs.getString("name");
                y[i][j++] = rs.getString("email");
                y[i][j++] = rs.getString("first_half");
                y[i][j++] = rs.getString("second_half");
                y[i][j++] = rs.getString("day_date");
                i++;
                j = 0;
            }
            if (count == 0) {
                JOptionPane.showMessageDialog(null, "No Record Found");
            } else {
                t = new JTable(y, x);
                t.setBackground(Color.LIGHT_GRAY);
                t.setForeground(Color.BLACK);
                t.setFont(f);
                JScrollPane js = new JScrollPane(t); // Add the JTable to JScrollPane
                add(js); // Add the JScrollPane to JFrame
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}