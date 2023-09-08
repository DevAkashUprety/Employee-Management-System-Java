import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Add_Emplpyee implements ActionListener {
    JLabel id,id1,id2,id3,id4,id5,id6,id7,id8,id9,id10,id11,id12,id13;
    JFrame f;
    JTextField t,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13;
    JButton b,b1;
    Add_Emplpyee(){
        f=new JFrame("Add Employee");
        f.setBackground(Color.WHITE);
        f.setLayout(null);
        id=new JLabel();
        id.setBounds(800,300,900,600);
        id.setLayout(null);
       // ImageIcon i1 = new ImageIcon("E:/Intellij_Work/EmployeeManagementSystem/Icon/OIP.jpg");
        f.add(id);

        id1=new JLabel("New Employee Details");
        id1.setBounds(410,30,500,50);
        id1.setFont((new Font("Airal",Font.BOLD,30)));
        id1.setForeground(Color.black);
        f.add(id1);


        id2=new JLabel("Name");
        id2.setBounds(120,150,200,30);
        id2.setFont((new Font("Airal",Font.BOLD,20)));
        id2.setForeground(Color.black);
        f.add(id2);
        t1=new JTextField();
        t1.setBounds(250,150,200,30);
        f.add(t1);

        id3=new JLabel("Father Name");
        id3.setBounds(700,150,200,30);
        id3.setFont((new Font("Airal",Font.BOLD,20)));
        id3.setForeground(Color.black);
        f.add(id3);
        t2=new JTextField();
        t2.setBounds(880,150,200,30);
        f.add(t2);

        id4=new JLabel("Age");
        id4.setBounds(120,200,200,30);
        id4.setFont((new Font("Airal",Font.BOLD,20)));
        id4.setForeground(Color.black);
        f.add(id4);
        t3=new JTextField();
        t3.setBounds(250,200,200,30);
        f.add(t3);


        id5=new JLabel("Date of Birth");
        id5.setBounds(700,200,200,30);
        id5.setFont((new Font("Airal",Font.BOLD,20)));
        id5.setForeground(Color.black);
        f.add(id5);
        t4=new JTextField();
        t4.setBounds(880,200,200,30);
        f.add(t4);

        id6=new JLabel("Address");
        id6.setBounds(120,250,200,30);
        id6.setFont((new Font("Airal",Font.BOLD,20)));
        id6.setForeground(Color.black);
        f.add(id6);
        t5=new JTextField();
        t5.setBounds(250,250,200,30);
        f.add(t5);

        id7=new JLabel("Phone");
        id7.setBounds(700,250,200,30);
        id7.setFont((new Font("Airal",Font.BOLD,20)));
        id7.setForeground(Color.black);
        f.add(id7);
        t6=new JTextField();
        t6.setBounds(880,250,200,30);
        f.add(t6);

        id8=new JLabel("Email Id");
        id8.setBounds(120,300,200,30);
        id8.setFont((new Font("Airal",Font.BOLD,20)));
        id8.setForeground(Color.black);
        f.add(id8);
        t7=new JTextField();
        t7.setBounds(250,300,200,30);
        f.add(t7);

        id9=new JLabel("Education");
        id9.setBounds(700,300,200,30);
        id9.setFont((new Font("Airal",Font.BOLD,20)));
        id9.setForeground(Color.black);
        f.add(id9);
        t8=new JTextField();
        t8.setBounds(880,300,200,30);
        f.add(t8);

        id10=new JLabel("Job Post");
        id10.setBounds(120,350,200,30);
        id10.setFont((new Font("Airal",Font.BOLD,20)));
        id10.setForeground(Color.black);
        f.add(id10);
        t9=new JTextField();
        t9.setBounds(250,350,200,30);
        f.add(t9);

        id11=new JLabel("Aadhar No");
        id11.setBounds(700,350,200,30);
        id11.setFont((new Font("Airal",Font.BOLD,20)));
        id11.setForeground(Color.black);
        f.add(id11);
        t10=new JTextField();
        t10.setBounds(880,350,200,30);
        f.add(t10);

        id12=new JLabel("Employee ID");
        id12.setBounds(120,400,200,30);
        id12.setFont((new Font("Airal",Font.BOLD,20)));
        id12.setForeground(Color.black);
        f.add(id12);
        t11=new JTextField();
        t11.setBounds(250,400,200,30);
        f.add(t11);

        b= new JButton("Submit");
        b.setBackground(Color.GREEN);
        b.setForeground(Color.WHITE);
        b.setBounds(400,500,150,40);
        f.add(b);
        b.setFont((new Font("Airal",Font.BOLD,18)));
        b.addActionListener(this);


        b1= new JButton("Cancel");
        b1.setBackground(Color.RED);
        b1.setForeground(Color.WHITE);
        b1.setBounds(600,500,150,40);
        f.add(b1);
        b1.setFont((new Font("Airal",Font.BOLD,18)));
        b1.addActionListener(this);



        f.setVisible(true);
        f.setSize(1200,700);
        f.setLocation(400,100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = t1.getText();
        String fname = t2.getText();
        String age = t3.getText();
        String dob = t4.getText();
        String address = t5.getText();
        String phone = t6.getText();
        String email = t7.getText();
        String education = t8.getText();
        String post = t9.getText();
        String aadhar = t10.getText();
        String eid = t11.getText();

        if (e.getSource() == b) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/EMS";
                String username = "root";
                String password = "root";
                Connection con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();

                String q = "INSERT INTO employee (name, fname, age, dob, address, phone, email, education, post, aadhar, eid) " +
                        "VALUES ('" + name + "','" + fname + "','" + age + "','" + dob + "','" + address + "','" + phone + "','" + email + "','" + education + "','" + post + "','" + aadhar + "','" + eid + "')";

                stmt.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Details Successfully Inserted");
                f.setVisible(false);
                new HomePage();
                stmt.close();
                con.close();

            } catch (ClassNotFoundException ex) {
                System.out.println("JDBC Driver not found");
            } catch (SQLException ex) {
                System.out.println("Error executing SQL query: " + ex);
            } catch (Exception ex){
                System.out.println("Error: "+ex);
            }
        }
        if (e.getSource() == b1) {
            f.setVisible(false);
            new HomePage();
        }
    }

    public static void main(String[] args){

        new Add_Emplpyee();
    }
}
