import javax.swing.*;
import java.awt.event.*;
import java.util.Base64;

public class portal extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;

    public portal(){
        b1 = new JButton("New Soldier");
        b1.setBounds(10,30,150,30);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("New Weapon");
        b2.setBounds(10,70,150,30);
        b2.addActionListener(this);
        add(b2);

        b3 = new JButton("New Vehicle");
        b3.setBounds(10,110,150,30);
        b3.addActionListener(this);
        add(b3);

        b4 = new JButton("New Unit");
        b4.setBounds(10,150,150,30);
        b4.addActionListener(this);
        add(b4);

        b5 = new JButton("Remove Soldier");
        b5.setBounds(10,190,150,30);
        b5.addActionListener(this);
        add(b5);

        b6 = new JButton("Remove Weapon");
        b6.setBounds(10,230,150,30);
        b6.addActionListener(this);
        add(b6);

        b7 = new JButton("Remove Vehicle");
        b7.setBounds(10,270,150,30);
        b7.addActionListener(this);
        add(b7);

        b8 = new JButton("Remove Unit");
        b8.setBounds(10,310,150,30);
        b8.addActionListener(this);
        add(b8);

        b9 = new JButton("Soldier Info");
        b9.setBounds(10,350,150,30);
        b9.addActionListener(this);
        add(b9);

        b10 = new JButton("Weapon Info");
        b10.setBounds(10,390,150,30);
        b10.addActionListener(this);
        add(b10);

        b11 = new JButton("Vehicle Info");
        b11.setBounds(10,430,150,30);
        b11.addActionListener(this);
        add(b11);

        b12 = new JButton("Logout");
        b12.setBounds(10,470,150,30);
        b12.addActionListener(this);
        add(b12);


        setLayout(null);
        setBounds(500,200,600,550);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == b1){

        }
        else if (ae.getSource() == b2){

        }
        else if (ae.getSource() == b3){

        }
        else if (ae.getSource() == b4){

        }
        else if (ae.getSource() == b5){

        }
        else if (ae.getSource() == b6){

        }
        else if (ae.getSource() == b7){

        }
        else if (ae.getSource() == b8){

        }
        else if (ae.getSource() == b9){

        }
        else if (ae.getSource() == b10){

        }
        else if (ae.getSource() == b11){

        }
        else if (ae.getSource() == b12){
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new portal().setVisible(true);
    }

}
