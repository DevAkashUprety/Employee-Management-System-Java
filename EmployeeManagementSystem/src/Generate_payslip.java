import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Generate_payslip extends JFrame implements ActionListener {
    JButton bt1, bt2;
    JLabel l1, l2, l3;
    JTextArea ta;
    Choice ch1, ch2, ch3;
    JPanel p1;
    Font f;

    Generate_payslip() {
        super("Generate Pay Slip");
        setSize(900, 600);
        setLocation(200, 150);
        setResizable(false);

        f = new Font("arial", Font.BOLD, 16);
        l1 = new JLabel("Employee Id");
        l2 = new JLabel("Month");
        l3 = new JLabel("Year");

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);

        ch1 = new Choice();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EMS";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String q = "select eid from employee";
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                ch1.add(rs.getString("eid"));
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ch2 = new Choice();
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

        ch3 = new Choice();
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

        ch1.setFont(f);
        ch2.setFont(f);
        ch3.setFont(f);

        bt1 = new JButton("Print");
        bt1.setForeground(Color.WHITE);
        bt1.setBackground(Color.BLACK);
        bt1.addActionListener(this);
        bt2 = new JButton("Close");
        bt2.setForeground(Color.WHITE);
        bt2.setBackground(Color.BLACK);
        bt2.addActionListener(this);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2, 10, 10));
        p1.add(l1);
        p1.add(ch1);
        p1.add(l2);
        p1.add(ch2);
        p1.add(l3);
        p1.add(ch3);
        p1.add(bt1);
        p1.add(bt2);

        ta = new JTextArea();
        JScrollPane sp = new JScrollPane(ta);
        ta.setEditable(false);
        ta.setFont(f);

        setLayout(new BorderLayout());
        add(sp, BorderLayout.CENTER);
        add(p1, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            ta.setText("------------------Pay Slip---------------");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/EMS";
                String username = "root";
                String password = "root";
                Connection con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();
                String eid = ch1.getSelectedItem();
                String month_year = ch2.getSelectedItem() + " " + ch3.getSelectedItem();
                String q1 = "select * from employee where eid='" + eid + "'";
                ResultSet rest1 = stmt.executeQuery(q1);
                while (rest1.next()) {
                    ta.append("\n\nEmployee Id : " + Integer.parseInt(rest1.getString("eid")));
                    ta.append("\nEmployee Name : " + rest1.getString("name"));
                    ta.append("\nEmployee Email : " + rest1.getString("email"));
                    ta.append("\n....................................................................");
                }
                String q2= "select * from salary where month_year='" + month_year + "' and eid='" + eid + "'";
                ResultSet rest2 = stmt.executeQuery(q2);
                System.out.println("Outside if condition " + rest2);
                if (!rest2.next()) {
                    System.out.println("Inside if condition"+rest2);
                    ta.append("\n....................................................................");
                    ta.append("\nRecord not found of this month and year.\n");
                    ta.append("\n....................................................................");
                    ta.append("\nPlease add the salary of this month and year for this record.\n");
                } else {
                    float hra = Float.parseFloat(rest2.getString("hra"));
                    float da = Float.parseFloat(rest2.getString("da"));
                    float med = Float.parseFloat(rest2.getString("mid"));
                    float pf = Float.parseFloat(rest2.getString("pf"));
                    float basicSalary = Float.parseFloat(rest2.getString("basic"));

                    ta.append("\n\nHRA : " + hra);
                    ta.append("\n\nDA : " + da);
                    ta.append("\n\nMED : " + med);
                    ta.append("\n\nPF : " + pf);
                    ta.append("\n\nBasic Salary : " + basicSalary);

                    ta.append("\n--------------------------------------------------------------------");
                    float grossSalary = hra + da + med + pf + basicSalary;
                    double tax = (grossSalary * 2.1) / 100;
                    ta.append("\nGross Salary : " + grossSalary);
                    ta.append("\nTotal : " + grossSalary);
                    ta.append("\nTax 2.1% of salary : " + tax);
                }

                    System.out.println("starting catch"+rest2);
                stmt.close();
                con.close();
            } catch (Exception ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        if (e.getSource() == bt2) {
            JOptionPane.showMessageDialog(null, "Are you sure.");
            setVisible(false);
        }
    }

    public static void main(String args[]) {
        new Generate_payslip().setVisible(true);
    }
}
