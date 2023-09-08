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

public class Employee_Attendance extends JFrame implements ActionListener {
   JLabel l1,l2,l3,l4,l5;
   Choice c1,c2,c3,ch;
   JButton bt1,bt2,bt3;
   Font f;
   JPanel p;
   JTextField tf1,tf2;
    Employee_Attendance(){
        super("Employee Attendence");
        setSize(650,400);
        setLocation(300,200);
        setResizable(false);
        f=new Font("senserif",Font.BOLD,15);

        l1=new JLabel("Select Employee ID");
        l2=new JLabel("First Half");
        l3=new JLabel("Second Half");
        l4=new JLabel("Name");
        l5=new JLabel("E-mail");

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);

        tf1=new JTextField();
        tf2=new JTextField();
        tf1.setFont(f);
        tf2.setFont(f);

        tf1.setEditable(false);
        tf2.setEditable(false);

        c2=new Choice();
        c2.add("Present");
        c2.add("Absent");

        c3=new Choice();
        c3.add("Present");
        c3.add("Absent");

        c2.setFont(f);
        c3.setFont(f);

        bt1=new JButton("Submit");
        bt2=new JButton("Close");

        bt1.setBackground(Color.GREEN);
        bt1.setForeground(Color.white);
        bt2.setBackground(Color.red);
        bt2.setForeground(Color.white);
        bt1.setFont(f);
        bt2.setFont(f);
        bt1.addActionListener(this);
        bt2.addActionListener(this);

        c1=new Choice();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EMS";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String q = "select eid from employee";
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                c1.add(rs.getString("eid"));
            }
            stmt.close();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        p=new JPanel();
        p.setLayout(new GridLayout(6,2,10,10));
        p.add(l1);
        p.add(c1);
        p.add(l4);
        p.add(tf1);
        p.add(l5);
        p.add(tf2);
        p.add(l2);
        p.add(c2);
        p.add(l3);
        p.add(c3);
        p.add(bt1);
        p.add(bt2);

        add(p);
        c1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/EMS";
                    String username = "root";
                    String password = "root";
                    Connection con = DriverManager.getConnection(url, username, password);
                    Statement stmt = con.createStatement();
                    String eid=c1.getSelectedItem();
                    String q2 = "select * from employee where eid='"+eid+"'";
                    ResultSet rs = stmt.executeQuery(q2);
                    while (rs.next()) {
                        tf1.setText(rs.getString("name"));
                        tf2.setText(rs.getString("email"));
                    }
                    stmt.close();
                    con.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
            if(ev.getSource()==bt1){
                String ch_eid=c1.getSelectedItem();
                String ch_first_half=c2.getSelectedItem();
                String ch_second_half=c3.getSelectedItem();
                String name=tf1.getText();
                String email=tf2.getText();
                String dt=new java.util.Date().toString();
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/EMS";
                    String username = "root";
                    String password = "root";
                    Connection con = DriverManager.getConnection(url, username, password);
                    Statement stmt = con.createStatement();
                    String q2 = "insert into attendence value('"+ch_eid+"','"+name+"','"+email+"','"+ch_first_half+"','"+ch_second_half+"','"+dt+"')";
                    stmt.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null,"Data Inserted");
                    setVisible(false);
                    stmt.close();
                    con.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            if(ev.getSource()==bt2){
                JOptionPane.showMessageDialog(null,"Are you sure to close?");
                setVisible(false);
            }
    }
    public static void main(String args[]){
        new Employee_Attendance().setVisible(true);
    }
}
