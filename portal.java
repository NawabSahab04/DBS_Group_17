import javax.swing.*;
import java.awt.event.*;
import java.util.Base64;

public class portal extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b12;

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


        b6 = new JButton("Show Soldier Information");
        b6.setBounds(10,230,150,30);
        b6.addActionListener(this);
        add(b6);

        b12 = new JButton("Back");
        b12.setBounds(10,300,150,30);
        b12.addActionListener(this);
        add(b12);



        setLayout(null);
        setBounds(500,200,300,400);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == b1){
            new addSoldier().setVisible(true);
        }
        else if (ae.getSource() == b2){
            new addWeapon().setVisible(true);
        }
        else if (ae.getSource() == b3){
            new addVehicle().setVisible(true);
        }
        else if (ae.getSource() == b4){
            new addUnit().setVisible(true);
        }
        else if (ae.getSource() == b5){
            new deleteSoldier().setVisible(true);
        }
        else if (ae.getSource() == b12){
            setVisible(false);
        }
        else if (ae.getSource() == b6){
            new ShowInfo().setVisible(true);
        }
    }
    public static void main(String[] args){
        new portal().setVisible(true);
    }

}
