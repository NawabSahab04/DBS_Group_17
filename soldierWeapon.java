import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import net.proteanit.sql.*;

public class soldierWeapon extends JFrame implements ActionListener {

    JTable t1;
    JButton b1,b2;
    JTextField t3;

    soldierWeapon(){
        t1 = new JTable();
        t1.setBounds(0,40,1000,250);
        add(t1);

        JLabel vehicle_type = new JLabel("Soldier ID");
        vehicle_type.setFont(new Font("Serif", Font.PLAIN,16));
        vehicle_type.setBounds(60,300,120,30);
        add(vehicle_type);

        t3 = new JTextField();
        t3.setBounds(200,300,150,30);
        add(t3);

        b1 = new JButton("Submit");
        b1.setBounds(350,560,120,30);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(530,560,120,30);
        b2.addActionListener(this);
        add(b2);

        setLayout(null);
        setBounds(450,200,1000,650);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == b1){
            String id = t3.getText();
            try{
                conn c = new conn();
                String str = "select weapon_name, first_name, last_name from soldier s join soldier_weapon sw on sw.soldier_id = s.soldier_id join weapon w on w.weapon_id = sw.weapon_id where s.soldier_id = '"+id+"'";
                ResultSet rs = c.s.executeQuery(str);

                t1.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
        else if (e.getSource() == b2){
            new Dashboard().setVisible(true);
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new soldierWeapon().setVisible(true);
    }
}
