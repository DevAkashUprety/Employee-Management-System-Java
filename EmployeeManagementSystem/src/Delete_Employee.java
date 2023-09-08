import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Delete_Employee extends JFrame implements ActionListener {
    JTable t;
    JTextField tf1;
    JButton bt1;
    JPanel p1,p2,p3;
    String x[]={"Employee ID","Name","Email","Age","Date of Birth","Post"};
    String y[][]=new String[20][6];
    int i=0,j=0;
    Font f, f1;
    JLabel l1,l2;
    Delete_Employee(){
        super("All Employee Records for Delete");
        setSize(1480,500);
        setLocation(250,250);
        f=new Font("MS UI Gothic",Font.BOLD,17);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EMS";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String q = "SELECT * FROM employee";
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()){
                y[i][j++]=rs.getString("eid");
                y[i][j++]=rs.getString("name");
                y[i][j++]=rs.getString("email");
                y[i][j++]=rs.getString("age");
                y[i][j++]=rs.getString("dob");
                y[i][j++]=rs.getString("post");
                i++;
                j=0;
            }
            t=new JTable(y,x);
            stmt.close();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        t.setBackground(Color.white);
        t.setForeground(Color.BLACK);
        t.setFont(f);
        JScrollPane js=new JScrollPane(t);
        f1=new Font("Lucida Fax",Font.BOLD,25);
        l1=new JLabel("Search any Employee");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(f1);
        l1.setForeground(Color.WHITE);

        l2=new JLabel("Employee Id");
        l2.setFont(f1);
        l2.setForeground(Color.GRAY);

        tf1=new JTextField();
        bt1=new JButton("Delete Employee");
        tf1.setFont(f);
        bt1.setFont(f);
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.white);
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

        p1.setBackground(Color.PINK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);

        add(p3,"South");
        add(js);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String eid=tf1.getText();
        if(e.getSource()==bt1){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/EMS";
                String username = "root";
                String password = "root";
                Connection con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();
                String q = "delete from employee where eid='"+eid+"'";
                String q1 = "delete from attendence where eid='"+eid+"'";
                String q2 = "delete from apply_leave where eid='"+eid+"'";
                String q3 = "delete from salary where eid='"+eid+"'";

                int  rs = stmt.executeUpdate(q);
                if(rs==1){
                    JOptionPane.showMessageDialog(null,"Your data successfully deleted.");
                    stmt.executeUpdate(q1);
                    stmt.executeUpdate(q2);
                    stmt.executeUpdate(q3);
                    this.setVisible(false);
                    new Delete_Employee().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Didn't delete sorry !!!!");
                    this.setVisible(false);
                    new Delete_Employee().setVisible(true);
                }
                stmt.close();
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }
    public static void main(String args[])
    {
        new Delete_Employee().setVisible(true);
    }
}
