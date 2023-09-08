import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class View_Leave extends JFrame implements ActionListener {
    JTable t;
    JButton bt1;
    JTextField tf1;
    JPanel p1, p2, p3;
    String x[] = {"Employee ID", "Name", "Email", "Start Date", "End Date", "Reason", "Apply Date"}; // Fixed the typo here
    String y[][] = new String[20][7];
    int i = 0, j = 0;
    Font f, f1;
    JLabel l1, l2;

    View_Leave() {
        super("All Employee Leave Records");
        setSize(1480, 500);
        setLocation(250, 150);
        f = new Font("MS UI Gothic", Font.BOLD, 17);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EMS";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String q = "select * from apply_leave";
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                y[i][j++] = rs.getString("eid");
                y[i][j++] = rs.getString("name");
                y[i][j++] = rs.getString("email");
                y[i][j++] = rs.getString("start_date");
                y[i][j++] = rs.getString("end_date");
                y[i][j++] = rs.getString("reason");
                y[i][j++] = rs.getString("apply_date");
                i++;
                j = 0;
            }
            stmt.close();
            con.close();
            t = new JTable(y, x);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JScrollPane js = new JScrollPane(t); // Corrected the JTable addition here
        t.setBackground(Color.WHITE);
        t.setForeground(Color.BLACK);
        t.setFont(f);

        f1=new Font("Lucids Fax",Font.BOLD,25);
        l1=new JLabel("Search any Employee");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(f1);
        l1.setForeground(Color.WHITE);

        l2=new JLabel("Employee ID");
        l2.setFont(f1);
        l2.setForeground(Color.GRAY);

        tf1=new JTextField();
        tf1.setFont(f);

        bt1=new JButton("Search Employee");
        bt1.setFont(f);

        bt1.setForeground(Color.WHITE);
        bt1.setBackground(Color.BLACK);
        bt1.addActionListener(this);

        p1=new JPanel();
        p1.setLayout(new GridLayout(1,1,10,10));
        p1.add(l1);

        p2=new JPanel();
        p2.setLayout(new GridLayout(1,3,10,10));
        p2.add(l2);
        p2.add(tf1);
        p2.add(bt1);

        p3=new JPanel();
        p3.setLayout(new GridLayout(2,1,10,10));
        p3.add(p1);
        p3.add(p2);

        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);

        add(p3,"South");
        add(js);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String eid=tf1.getText();
        if(e.getSource()==bt1){
            new View_Leave_Single(eid).setVisible(true);
        }
    }
    public static void main(String []args){
        new View_Leave().setVisible(true);
    }
}
