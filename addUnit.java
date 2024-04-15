import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class addUnit extends JFrame implements ActionListener {
    JTextField t1,t2,t3,t4,t5,t6;
    JButton b1,b2;
    public addUnit(){

        JLabel l1 = new JLabel("ADD UNIT");
        l1.setFont(new Font("Serif", Font.BOLD,15));
        l1.setBounds(200,20,150,20);
        add(l1);

        JLabel unit_id = new JLabel("UNIT ID");
        unit_id.setFont(new Font("Serif", Font.PLAIN,16));
        unit_id.setBounds(60,80,120,30);
        add(unit_id);

        t1 = new JTextField();
        t1.setBounds(200,80,150,30);
        add(t1);

        JLabel unit_location = new JLabel("UNIT LOCATION");
        unit_location.setFont(new Font("Serif", Font.PLAIN,16));
        unit_location.setBounds(60,130,120,30);
        add(unit_location);

        t2 = new JTextField();
        t2.setBounds(200,130,150,30);
        add(t2);

        JLabel unit_size = new JLabel("UNIT SIZE");
        unit_size.setFont(new Font("Serif", Font.PLAIN,16));
        unit_size.setBounds(60,180,120,30);
        add(unit_size);

        t3 = new JTextField();
        t3.setBounds(200,180,150,30);
        add(t3);

        JLabel commander_id = new JLabel("COMMANDER ID");
        commander_id.setFont(new Font("Serif", Font.PLAIN,16));
        commander_id.setBounds(60,230,120,30);
        add(commander_id);

        t4 = new JTextField();
        t4.setBounds(200,230,150,30);
        add(t4);

        JLabel unit_name = new JLabel("UNIT NAME");
        unit_name.setFont(new Font("Serif", Font.PLAIN,16));
        unit_name.setBounds(60,280,120,30);
        add(unit_name);

        t5 = new JTextField();
        t5.setBounds(200,280,150,30);
        add(t5);

        b1 = new JButton("Add Unit");
        b1.setBounds(60,380,130,30);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBounds(200,380,130,30);
        b2.addActionListener(this);
        add(b2);

        setBounds(530,200,850,570);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource()==b1){
            String unit_id = t1.getText();
            String unit_location = t2.getText();
            String unit_size = t3.getText();
            String commander_id = t4.getText();
            String unit_name = t5.getText();

            conn c = new conn();
            try{
                String str = "INSERT INTO unit values( '"+unit_id+"', '"+unit_location+"', '"+unit_size+"','"+commander_id+"', '"+unit_name+"')";
                c.s.executeUpdate(str);

                JOptionPane.showMessageDialog(null,"Unit Added");
                this.setVisible(false);
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
        else if (e.getSource()==b2){
            new addUnit().setVisible(true);
        }
    }
    public static void main(String[] args){
        new addUnit().setVisible(true);
    }
}
