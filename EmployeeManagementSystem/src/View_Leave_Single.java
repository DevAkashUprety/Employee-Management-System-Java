import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class View_Leave_Single extends JFrame {
    JTable t;
    String x[] = {"Employee ID", "Name", "Email", "Start Date", "End Date", "Reason", "Apply Date"};
    String y[][] = new String[26][7]; // Corrected the array size here
    int i = 0, j = 0;
    Font f, f1;

    View_Leave_Single(String Eid) {
        super("Particular Employee Leave Records");
        setSize(1480, 500);
        setLocation(250, 150);
        f = new Font("MS UI Gothic", Font.BOLD, 17);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EMS";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String q = "SELECT * FROM apply_leave WHERE eid='" + Eid + "'";
            ResultSet rs = stmt.executeQuery(q);
            int count = 0;
            while (rs.next()) {
                count++;
                y[i][j++] = rs.getString("eid");
                y[i][j++] = rs.getString("name");
                y[i][j++] = rs.getString("email");
                y[i][j++] = rs.getString("start_date");
                y[i][j++] = rs.getString("end_date"); // Added the missing column
                y[i][j++] = rs.getString("reason");
                y[i][j++] = rs.getString("apply_date"); // Fixed the column name here
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
                JScrollPane js = new JScrollPane(t);
                add(js);
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
