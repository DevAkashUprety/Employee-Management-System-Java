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

public class Salary extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
    Font f,f1;
    Choice ch1,ch2,ch3;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton bt1,bt2;
    JPanel p1,p2,p3;
    Salary(){
        super("Salary");
        setLocation(100,100);
        setSize(950,700);
        setResizable(false);

        f=new Font("Arial", Font.BOLD,18);
        f1=new Font("Arial", Font.BOLD,25);

        l1=new JLabel("Select Employee ID");
        l2=new JLabel("Name");
        l3=new JLabel("E-mail");
        l4=new JLabel("HRA");
        l5=new JLabel("DA");
        l6=new JLabel("MID");
        l7=new JLabel("PF");
        l8=new JLabel("Basic Salary");
        l9=new JLabel("Select Month");
        l10=new JLabel("Select Year");
        l12=new JLabel("Employee Salary");

        l12.setHorizontalAlignment(JLabel.CENTER);

        ch1=new Choice();
        try{
            Class.forName("com.mysql.jdbc.Driver");
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
        ch2.add("January");
        ch2.add("February");
        ch2.add("March");
        ch2.add("April");
        ch2.add("May");
        ch2.add("June");
        ch2.add("July");
        ch2.add("August");
        ch2.add("September");
        ch2.add("October");
        ch2.add("November");
        ch2.add("December");

        ch3=new Choice();
        ch3.add("2013");
        ch3.add("2014");
        ch3.add("2015");
        ch3.add("2016");
        ch3.add("2017");
        ch3.add("2018");
        ch3.add("2019");
        ch3.add("2020");
        ch3.add("2021");
        ch3.add("2022");
        ch3.add("2023");
        ch3.add("2024");
        ch3.add("2025");
        ch3.add("2026");

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
        l6.setFont(f);
        l7.setFont(f);
        l8.setFont(f);
        l9.setFont(f);
        l10.setFont(f);
        l12.setFont(f);

        ch1.setFont(f);
        ch2.setFont(f);
        ch3.setFont(f);

        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t4=new JTextField();
        t5=new JTextField();
        t6=new JTextField();
        t7=new JTextField();

        t1.setFont(f);
        t2.setFont(f);
        t3.setFont(f);
        t4.setFont(f);
        t5.setFont(f);
        t6.setFont(f);
        t7.setFont(f);

        t1.setEditable(false);
        t2.setEditable(false);

        ImageIcon img = new ImageIcon("E:/Intellij_Work/EmployeeManagementSystem/Icon/OIP.jpg");
        l11 = new JLabel(img);

        bt1=new JButton("Submit");
        bt2=new JButton("Close");

        bt1.setFont(f);
        bt2.setFont(f);
        bt1.setBackground(Color.GREEN);
        bt1.setForeground(Color.BLACK);
        bt2.setBackground(Color.RED);
        bt2.setForeground(Color.BLACK);
        bt1.addActionListener(this);
        bt2.addActionListener(this);

        p1=new JPanel();
        p1.setLayout(new GridLayout(11,2,10,10));
        p1.add(l1);
        p1.add(ch1);
        p1.add(l2);
        p1.add(t1);
        p1.add(l3);
        p1.add(t2);
        p1.add(l4);
        p1.add(t3);
        p1.add(l5);
        p1.add(t4);
        p1.add(l6);
        p1.add(t5);
        p1.add(l7);
        p1.add(t6);
        p1.add(l8);
        p1.add(t7);
        p1.add(l9);
        p1.add(ch2);
        p1.add(l10);
        p1.add(ch3);
        p1.add(bt1);
        p1.add(bt2);

        p2=new JPanel();
        p2.setLayout(new GridLayout(1,1,10,10));
        p2.add(l11);

        p3=new JPanel();
        p3.setLayout(new GridLayout(1,1,10,10));
        p3.add(l12);

        setLayout(new BorderLayout(30,30));
        add(p1,"Center");
        add(p2,"West");
        add(p3,"North");


        ch1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/EMS";
                        String username = "root";
                        String password = "root";
                        Connection con = DriverManager.getConnection(url, username, password);
                        Statement stmt = con.createStatement();
                        String eid2 = ch1.getSelectedItem();
                        String q3 = "select * from employee where eid='" + eid2 + "'";
                        ResultSet rs = stmt.executeQuery(q3);
                        while (rs.next()) {
                            t1.setText(rs.getString("name"));
                            t2.setText(rs.getString("email"));
                        }
                        stmt.close();
                        con.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bt1){
            String Eid = ch1.getSelectedItem();
            String name = t1.getText();
            String email = t2.getText();
            Float hra = Float.parseFloat(t3.getText());
            Float da = Float.parseFloat(t4.getText());
            Float mid = Float.parseFloat(t5.getText());
            Float pf = Float.parseFloat(t6.getText());
            Float basic = Float.parseFloat(t7.getText());
            String month = ch2.getSelectedItem() + " " + ch3.getSelectedItem();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/EMS";
                String username = "root";
                String password = "root";
                Connection con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();
                String q1 = "INSERT INTO salary VALUES('" + 0 + "','" + Eid + "','" + name + "','" + email + "','" + hra + "','" + da + "','" + mid + "','" + pf + "','" + basic + "','" + month + "')";
                int aa = stmt.executeUpdate(q1);
                System.out.println(aa);
                if(aa == 1){
                    JOptionPane.showMessageDialog(null, "Your data successfully Inserted");
                    this.setVisible(false);
                } else{
                    JOptionPane.showMessageDialog(null, "Please, Fill all details Inserted");
                    this.setVisible(false);
                    this.setVisible(true);
                }
                stmt.close();
                con.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }    if(e.getSource()==bt2){
            JOptionPane.showMessageDialog(null,"Are you Sure.");
            setVisible(false);
        }
    }

    public static void main(String[] args){

        new Salary().setVisible(true);
    }

}
