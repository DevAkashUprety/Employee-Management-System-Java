import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class View_Employee implements ActionListener {
    JFrame f;
    JTextField t;
    JLabel l1,l2;
    JButton b1,b2;

    View_Employee(){
        f=new JFrame("View Employee");
        f.setBackground(Color.GREEN);
        f.setLayout(null);

        l1=new JLabel();
        l1.setBounds(50,50,900,570);
        l1.setLayout(null);
        //ImageIcon img = new ImageIcon("E:/Intellij_Work/EmployeeManagementSystem/Icon/OIP.jpg");
        //l1.setIcon(img);
        f.add(l1);


        l2=new JLabel("Employee Id");
        l2.setVisible(true);
        l2.setBounds(250,60,250,30);
        l2.setForeground(Color.BLACK);
        Font F1=new Font("Arial",Font.BOLD,30);
        l2.setFont(F1);
        l1.add(l2);
        f.add(l1);

        t=new JTextField();
        t.setBounds(500,60,220,30);
        l1.add(t);
        f.add(l1);

        b1= new JButton("Search");
        b1.setBackground(Color.GREEN);
        b1.setForeground(Color.WHITE);
        b1.setBounds(400,150,100,30);
        l1.add(b1);
        b1.setFont((new Font("Airal",Font.BOLD,18)));
        b1.addActionListener(this);

        b2= new JButton("Cancel");
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        b2.setBounds(550,150,100,30);
        l1.add(b2);
        b2.setFont((new Font("Airal",Font.BOLD,18)));
        b2.addActionListener(this);

        f.setVisible(true);
        f.setSize(1000,450);
        f.setLocation(500,200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            f.setVisible(false);
            new View_Employee_Data(t.getText());
        }
        if(e.getSource()==b2){
            f.setVisible(false);
            new HomePage();
        }
    }
    public static void main(String[] args){

        new View_Employee();
    }
}