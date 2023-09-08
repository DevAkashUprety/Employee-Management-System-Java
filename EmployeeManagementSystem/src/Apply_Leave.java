import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Apply_Leave  extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JButton bt1,bt2;
    JPanel p1,p2,p3;
    JTextField tf1,tf2,tf3,tf4;
    Font f, f1;
    Choice ch1,ch2;
    Apply_Leave(){
        super("Apply Employee Leave");
        setLocation(450,50);
        setSize(1000,520);

        f=new Font("Arial",Font .BOLD,25);
        f1=new Font ("Arial",Font.BOLD,16);

        l1=new JLabel("Apply Employee Leave");
        l2=new JLabel("Select Employee ID");
        l3=new JLabel("Name");
        l4=new JLabel("Email");
        l5=new JLabel("Start Date");
        l6=new JLabel("End Date");
        l7=new JLabel("Leave Reason");

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();

        bt1=new JButton("Submit");
        bt2=new JButton("Close");

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt1.setForeground(Color.WHITE);
        bt1.setBackground(Color.GREEN);
        bt2.setForeground(Color.WHITE);
        bt2.setBackground(Color.RED);

        ch1=new Choice();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EMS";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String q = "select eid from employee";
            ResultSet rs = stmt.executeQuery(q);
            while(rs.next()){
                ch1.add(rs.getString("eid"));
            }
            stmt.close();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        ch2=new Choice();
        ch2.add("Employee Health Issue");
        ch2.add("Family Member Health Issue");
        ch2.add("Function/ Celebration");
        ch2.add("Party");
        ch2.add("Personal Issue");
        ch2.add("Dating");
        ch2.add("Other's");

        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        ch1.setFont(f1);
        ch2.setFont(f1);


        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);

        bt1.setFont(f1);
        bt2.setFont(f1);

        tf1.setEditable(false);
        tf2.setEditable(false);

        p1=new JPanel();
        p1.setLayout(new GridLayout(1,1,10,10));
        p1.add(l1);

        p2=new JPanel();
        p2.setLayout(new GridLayout(7,2,10,10));
        p2.add(l2);
        p2.add(ch1);
        p2.add(l3);
        p2.add(tf1);
        p2.add(l4);
        p2.add(tf2);
        p2.add(l5);
        p2.add(tf3);
        p2.add(l6);
        p2.add(tf4);
        p2.add(l7);
        p2.add(ch2);
        p2.add(bt1);
        p2.add(bt2);


        p3=new JPanel();
        p3.setLayout(new GridLayout(1,1,10,10));

        ImageIcon img = new ImageIcon("E:/Intellij_Work/EmployeeManagementSystem/Icon/OIP.jpg");
        l8 = new JLabel(img);
        l8.setBounds(250,400,100,100);
        p3.add(l8);
        setLayout(new BorderLayout(10,10));
        add(p1,"North");
        add(p3,"Center");
        add(p2,"West");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ch1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent args) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/EMS";
                    String username = "root";
                    String password = "root";
                    Connection con = DriverManager.getConnection(url, username, password);
                    Statement stmt = con.createStatement();
                    String eid=ch1.getSelectedItem();
                    String q2 = "select * from employee where eid='"+eid+"'";
                    ResultSet rs = stmt.executeQuery(q2);
                    while(rs.next()){
                        tf1.setText(rs.getString("name"));
                        tf2.setText(rs.getString("email"));
                    }
                    stmt.close();
                    con.close();
                }catch (Exception exa){
                    exa.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String eid = ch1.getSelectedItem();
            String name = tf1.getText();
            String email = tf2.getText();
            String startdt = tf3.getText();
            String enddt = tf4.getText();
            String reason = ch2.getSelectedItem();
            String apply_dt = new java.util.Date().toString();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/EMS";
                String username = "root";
                String password = "root";
                Connection con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();
                String q = "INSERT INTO apply_leave VALUES('" + eid + "','" + name + "','" + email + "','" + startdt + "','" + enddt + "','" + reason + "','" + apply_dt + "')";
                int aa = stmt.executeUpdate(q);
                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Your data successfully updated");
                    this.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please, Fill all the details carefully");
                }
                stmt.close();
                con.close();
            } catch (Exception exa) {
                exa.printStackTrace();
            }
        }
        if(e.getSource()==bt2){
            JOptionPane.showMessageDialog(null,"are you sure to close");
            this.setVisible(false);
        }
    }
    public static void main(String[]args) {
        new Apply_Leave().setVisible(true);
    }
}
