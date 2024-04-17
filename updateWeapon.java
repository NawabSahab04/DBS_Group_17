import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class updateWeapon extends JFrame implements ActionListener {
    JTextField t1,t2;
    JButton b1,b2;
    public updateWeapon(){

        JLabel l1 = new JLabel("UPDATE WEAPON PRICE");
        l1.setFont(new Font("Serif", Font.BOLD,15));
        l1.setBounds(200,20,150,20);
        add(l1);

        JLabel weapon_id = new JLabel("WEAPON ID");
        weapon_id.setFont(new Font("Serif", Font.PLAIN,16));
        weapon_id.setBounds(60,80,120,30);
        add(weapon_id);

        t1 = new JTextField();
        t1.setBounds(200,80,150,30);
        add(t1);


        JLabel weapon_price = new JLabel("NEW PRICE");
        weapon_price.setFont(new Font("Serif", Font.PLAIN,16));
        weapon_price.setBounds(60,180,120,30);
        add(weapon_price);

        t2 = new JTextField();
        t2.setBounds(200,180,150,30);
        add(t2);

        b1 = new JButton("Submit");
        b1.setBounds(60,330,130,30);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBounds(200,330,130,30);
        b2.addActionListener(this);
        add(b2);

        setBounds(450,200,600,500);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource()==b1){
            String weapon_id = t1.getText();
            String new_price = t2.getText();

            conn c = new conn();
            try{
                String str = "update weapon set weapon_price = '"+new_price+"'where weapon_id = '"+weapon_id+"'";
                c.s.executeUpdate(str);

                JOptionPane.showMessageDialog(null,"Weapon Price Updated");
                this.setVisible(false);
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
        else if (e.getSource()==b2){
            new updateWeapon().setVisible(true);
        }
    }
    public static void main(String[] args){
        new updateWeapon().setVisible(true);
    }
}
