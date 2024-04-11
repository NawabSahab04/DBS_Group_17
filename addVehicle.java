import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class addVehicle extends JFrame implements ActionListener {
    JTextField t1,t2,t3;
    JButton b1,b2;
    public addVehicle(){

        JLabel l1 = new JLabel("ADD VEHICLE");
        l1.setFont(new Font("Serif", Font.BOLD,15));
        l1.setBounds(200,20,150,20);
        add(l1);

        JLabel vehicle_id = new JLabel("VEHICLE ID");
        vehicle_id.setFont(new Font("Serif", Font.PLAIN,16));
        vehicle_id.setBounds(60,80,120,30);
        add(vehicle_id);

        t1 = new JTextField();
        t1.setBounds(200,80,150,30);
        add(t1);

        JLabel vehicle_name = new JLabel("VEHICLE NAME");
        vehicle_name.setFont(new Font("Serif", Font.PLAIN,16));
        vehicle_name.setBounds(60,130,120,30);
        add(vehicle_name);

        t2 = new JTextField();
        t2.setBounds(200,130,150,30);
        add(t2);

        JLabel vehicle_price = new JLabel("VEHICLE PRICE");
        vehicle_price.setFont(new Font("Serif", Font.PLAIN,16));
        vehicle_price.setBounds(60,180,120,30);
        add(vehicle_price);

        t3 = new JTextField();
        t3.setBounds(200,180,150,30);
        add(t3);

        b1 = new JButton("Add Vehicle");
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
            String vehicle_id = t1.getText();
            String vehicle_name = t2.getText();
            String vehicle_price = t3.getText();

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
            new addVehicle().setVisible(true);
        }
    }
    public static void main(String[] args){
        new addVehicle().setVisible(true);
    }
}


