import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class addWeapon extends JFrame implements ActionListener {
    JTextField t1,t2,t3;
    JButton b1,b2;
    JComboBox c1;
    public addWeapon(){

        JLabel l1 = new JLabel("ADD WEAPON");
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

        JLabel weapon_name = new JLabel("WEAPON NAME");
        weapon_name.setFont(new Font("Serif", Font.PLAIN,16));
        weapon_name.setBounds(60,130,120,30);
        add(weapon_name);

        t2 = new JTextField();
        t2.setBounds(200,130,150,30);
        add(t2);

        JLabel weapon_price = new JLabel("WEAPON PRICE");
        weapon_price.setFont(new Font("Serif", Font.PLAIN,16));
        weapon_price.setBounds(60,180,120,30);
        add(weapon_price);

        t3 = new JTextField();
        t3.setBounds(200,180,150,30);
        add(t3);

        JLabel rank = new JLabel("Soldier Rank");
        rank.setFont(new Font("Serif", Font.PLAIN, 16));
        rank.setBounds(60,230,120,30);
        add(rank);

        String ranks[] = {"Lieutenant","Captain","Major","Colonel","Commander"};
        c1 = new JComboBox(ranks);
        c1.setBounds(200,230,120,30);
        add(c1);

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
            String weapon_name = t2.getText();
            String weapon_price = t3.getText();

            String rank = (String)c1.getSelectedItem();

            /*conn c = new conn();
            try{
                String str = "insert into vehicles values('"; //complete
                c.s.executeUpdate(str);

                JOptionPane.showMessageDialog(null,"Vehicle Added");
                this.setVisible(false);
            }
            catch(Exception ex){
                System.out.println(ex);
            }*/
        }
        else if (e.getSource()==b2){
            new addWeapon().setVisible(true);
        }
    }
    public static void main(String[] args){
        new addWeapon().setVisible(true);
    }
}
