import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class View_Employee_Data implements ActionListener {
    JFrame f;
    JLabel id,aid,id1,aid1,id2,aid2,id3,aid3,id4,aid4,id5,aid5,id6,aid6,id7,aid7,id8,aid8,id9,aid9;
    String emp_id,name,father,address,phone,email,education,post,age,dob,aadhar;
    JButton b1,b2;
    ImageIcon icon;
    View_Employee_Data(String Eid){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EMS";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            String q = "SELECT * FROM employee WHERE eid='" + Eid + "'";
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                name = rs.getString("name");
                father = rs.getString("fname");
                age = rs.getString("age");
                dob = rs.getString("dob");
                address = rs.getString("address");
                phone = rs.getString("phone");
                email = rs.getString("email");
                education = rs.getString("education");
                post = rs.getString("post");
                emp_id = rs.getString("eid");

                // Add this line to initialize 'aadhar' variable
                aadhar = rs.getString("aadhar");
            }
            JOptionPane.showMessageDialog(null, "Successfull");
            f.setVisible(false);
            new HomePage();
            stmt.close();
            con.close();

        }catch(Exception ex){
                ex.printStackTrace();
        }
        f=new JFrame("Display Employee Details");
        f.setVisible(true);
        f.setSize(795,980);
        f.setLocation(450,50);
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        id9=new JLabel();
        id9.setBounds(0,0,795,980);
        id9.setLayout(null);
        // ImageIcon img = new ImageIcon("E:/Intellij_Work/EmployeeManagementSystem/Icon/OIP.jpg");
        //id9.setIcon(img);


        id8=new JLabel("Employee Details");
        id8.setBounds(170,10,250,40);
        id8.setFont(new Font("Arial",Font.BOLD,28));
        id9.add(id8);
        f.add(id9);

        id=new JLabel("Employee Id");
        id.setBounds(150,70,170,30);
        id.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(id);
        aid=new JLabel(Eid);
        aid.setBounds(330,70,200,30);
        aid.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(aid);


        id1=new JLabel("Name");
        id1.setBounds(150,100,170,30);
        id1.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(id1);
        aid1=new JLabel(name);
        aid1.setBounds(330,100,200,30);
        aid1.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(aid1);

        id2=new JLabel("Father's Name");
        id2.setBounds(150,200,170,30);
        id2.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(id2);
        aid2=new JLabel(father);
        aid2.setBounds(330,200,200,30);
        aid2.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(aid2);

        id3=new JLabel("Address");
        id3.setBounds(150,300,170,30);
        id3.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(id3);
        aid3=new JLabel(address);
        aid3.setBounds(330,300,200,30);
        aid3.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(aid3);

        id4=new JLabel("Mobile No");
        id4.setBounds(150,400,170,30);
        id4.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(id4);
        aid4=new JLabel(phone);
        aid4.setBounds(330,400,200,30);
        aid4.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(aid4);

        id5=new JLabel("Email Id");
        id5.setBounds(150,500,170,30);
        id5.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(id5);
        aid5=new JLabel(email);
        aid5.setBounds(330,500,200,30);
        aid5.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(aid5);

        id6=new JLabel("Education");
        id6.setBounds(150,600,170,30);
        id6.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(id6);
        aid6=new JLabel(education);
        aid6.setBounds(330,600,200,30);
        aid6.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(aid6);

        id7=new JLabel("Job Post");
        id7.setBounds(150,700,170,30);
        id7.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(id7);
        aid7=new JLabel(post);
        aid7.setBounds(330,700,200,30);
        aid7.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(aid7);

        id8=new JLabel("Aadhar No");
        id8.setBounds(150,800,170,30);
        id8.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(id8);
        aid8=new JLabel(aadhar);
        aid8.setBounds(330,800,200,30);
        aid8.setFont(new Font("Arial",Font.BOLD,20));
        id9.add(aid8);

        b1= new JButton("Print");
        b1.setBackground(Color.GREEN);
        b1.setForeground(Color.WHITE);
        b1.setBounds(160,850,100,30);
        id9.add(b1);
        b1.setFont((new Font("Airal",Font.BOLD,18)));
        b1.addActionListener(this);

        b2= new JButton("Cancel");
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        b2.setBounds(350,850,100,30);
        id9.add(b2);
        b2.setFont((new Font("Airal",Font.BOLD,18)));
        b2.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            JOptionPane.showMessageDialog(null,"Printed successfully");
            f.setVisible(false);
            new HomePage();
        }
        if(e.getSource()==b2){
            f.setVisible(false);
            new View_Employee();
        }
    }
}

