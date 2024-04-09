//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class militarySystem extends JFrame implements ActionListener{
    militarySystem(){
        setSize(800,450);
        setLocation(300,200);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        JLabel l1 = new JLabel(i1);
        l1.setBounds(0,0,800,450);
        add(l1);

        JButton b1 = new JButton("Proceed");
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
        b1.setBounds(600,50,100,35);
        b1.addActionListener(this);
        l1.add(b1);

        JLabel lid=new JLabel("Military Resource Allocation");
        lid.setBounds(150,330,500,100);
        lid.setFont(new Font("serif",Font.PLAIN,40));
        lid.setForeground(Color.black);
        l1.add(lid);
        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        new Login().setVisible(true);
        this.setVisible(false);
    }
    public static void main(String[] args) {
        new militarySystem();
    }
}