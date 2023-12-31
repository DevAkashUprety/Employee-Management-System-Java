import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;

public class Update_Details_Data extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13;
    JPanel p1,p2,p3;
    JButton bt1,bt2;
    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10;
    Font f, f1;
    Choice ch;

    Update_Details_Data() {
        super("Update Employee");
        setLocation(450, 120);
        setSize(1200, 750);

        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 18);

        ch = new Choice();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EMS";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            String q = "SELECT eid FROM employee";
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                ch.add(rs.getString("eid"));
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        l1=new JLabel("Update Employee");
        l2=new JLabel("Name");
        l3=new JLabel("Father's Name");
        l4=new JLabel("Age");
        l5=new JLabel("Date of Birth");
        l6=new JLabel("Address");
        l7=new JLabel("Phone");
        l8=new JLabel("E-mail");
        l9=new JLabel("Education");
        l10=new JLabel("Job Post");
        l11=new JLabel("Aadhar");
        l12=new JLabel("Employee ID");

        tf1=new JTextField();
        tf2=new JTextField();
        tf3=new JTextField();
        tf4=new JTextField();
        tf5=new JTextField();
        tf6=new JTextField();
        tf7=new JTextField();
        tf8=new JTextField();
        tf9=new JTextField();
        tf10=new JTextField();

        bt1=new JButton("Update Data");
        bt1.addActionListener(this);
        bt2=new JButton("Back");
        bt2.addActionListener(this);

        l1.setHorizontalAlignment(JLabel.CENTER);

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        l8.setFont(f1);
        l9.setFont(f1);
        l10.setFont(f1);
        l11.setFont(f1);
        l12.setFont(f1);

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);
        tf8.setFont(f1);
        tf9.setFont(f1);
        tf10.setFont(f1);

        bt1.setBackground(Color.BLUE);
        bt2.setBackground(Color.RED);
        bt1.setForeground(Color.white);
        bt2.setForeground(Color.white);

        p1=new JPanel(); //Header(Title)
        p1.setLayout(new GridLayout(1,1,10,10));
        p1.add(l1);

        //right Panel
        p2=new JPanel();
        p2.setLayout(new GridLayout(12,2,10,10));
        p2.add(l12);
        p2.add(ch);
        p2.add(l2);
        p2.add(tf1);
        p2.add(l3);
        p2.add(tf2);
        p2.add(l4);
        p2.add(tf3);
        p2.add(l5);
        p2.add(tf4);
        p2.add(l6);
        p2.add(tf5);
        p2.add(l7);
        p2.add(tf6);
        p2.add(l8);
        p2.add(tf7);
        p2.add(l9);
        p2.add(tf8);
        p2.add(l10);
        p2.add(tf9);
        p2.add(l11);
        p2.add(tf10);
        p2.add(bt1);
        p2.add(bt2);

        p3=new JPanel();
        p3.setLayout(new GridLayout(1,1,10,10));

        ImageIcon img = new ImageIcon("E:/Intellij_Work/EmployeeManagementSystem/Icon/OIP.jpg");
        l13 = new JLabel(img);
        l13.setBounds(0, 0,400,600);
        p3.add(l13);

        setLayout(new BorderLayout(10,10));
        add(p1,"North");
        add(p2,"Center");
        add(p3,"West");

        ch.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/EMS";
                    String username = "root";
                    String password = "root";
                    Connection con = DriverManager.getConnection(url, username, password);
                    Statement stmt = con.createStatement();
                    String eid=ch.getSelectedItem();
                    String q = "SELECT * FROM employee WHERE eid='" +eid+ "'";
                    ResultSet rs = stmt.executeQuery(q);
                    while (rs.next()) {
                        tf1.setText(rs.getString("name"));
                        tf2.setText(rs.getString("fname"));
                        tf3.setText(rs.getString("age"));
                        tf4.setText(rs.getString("dob"));
                        tf5.setText(rs.getString("address"));
                        tf6.setText(rs.getString("phone"));
                        tf7.setText(rs.getString("email"));
                        tf8.setText(rs.getString("education"));
                        tf9.setText(rs.getString("post"));
                        tf10.setText(rs.getString("aadhar"));
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
    public void actionPerformed(ActionEvent e) {
        String eid=ch.getSelectedItem();
        String name=tf1.getText();
        String fname=tf2.getText();
        String age=tf3.getText();
        String dob=tf4.getText();
        String address=tf5.getText();
        String phone=tf6.getText();
        String email=tf7.getText();
        String education=tf8.getText();
        String post=tf9.getText();
        String aadhar=tf10.getText();

        if(e.getSource()==bt1){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/EMS";
                String username = "root";
                String password = "root";
                Connection con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();
                String q = "UPDATE  employee set name='"+name+ "',fname='"+fname+"',age='"+age+"',dob='"+dob+"',address='"+address+"',phone='"+phone+"',email='"+email+"',education='"+education+"',post='"+post+"',aadhar='"+aadhar+"' WHERE eid='"+eid+"'";
                int aa= stmt.executeUpdate(q);
                if(aa==1){
                    JOptionPane.showMessageDialog(null,"Your data successfully updated");
                    this.setVisible(false);
                    new Update_Details_Data();
                }
                else{
                    JOptionPane.showMessageDialog(null, " Please Fill all details carefully");
                }
                stmt.close();
                con.close();
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
        if (e.getSource()==bt2){
            this.setVisible(false);
        }
    }
    public static void main(String[]args){
        new Update_Details_Data().setVisible(true);
    }
}
