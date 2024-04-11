import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
    JMenuBar mb;
    JMenu m1,m2;
    JMenuItem i1,i2,i3,i4,i5;

    Dashboard(){
        mb = new JMenuBar();
        add(mb);

        m1 = new JMenu("Military Resource Allocation");
        mb.add(m1);

        m2 = new JMenu("Admin");
        mb.add(m2);

        i1 = new JMenuItem("PORTAL");
        i1.addActionListener(this);
        m1.add(i1);
        i2 = new JMenuItem("ADD SOLDIER");
        i2.addActionListener(this);
        m2.add(i2);
        i3 = new JMenuItem("ADD WEAPON");
        i3.addActionListener(this);
        m2.add(i3);
        i4 = new JMenuItem("ADD VEHICLE");
        i4.addActionListener(this);
        m2.add(i4);
        i5 = new JMenuItem("ADD UNIT");
        i5.addActionListener(this);
        m2.add(i5);

        mb.setBounds(0,0,1000,30);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 600,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel NewLabel = new JLabel(i3);
        NewLabel.setBounds(0, 0, 1000, 600);
        add(NewLabel);

        JLabel MilitarySystem = new JLabel("Indian Army Welcomes You");
        MilitarySystem.setForeground(Color.BLACK);
        MilitarySystem.setFont(new Font("Tahoma", Font.PLAIN, 46));
        MilitarySystem.setBounds(200, 450, 1000, 50);
        NewLabel.add(MilitarySystem);

        setLayout(null);
        setBounds(0,0,1000,600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getActionCommand().equals("PORTAL")){
            new portal().setVisible(true);
        }
        else if (ae.getActionCommand().equals("ADD SOLDIER")){
            new addSoldier().setVisible(true);
        }
        else if (ae.getActionCommand().equals("ADD WEAPON")){
            new addWeapon().setVisible(true);
        }
        else if (ae.getActionCommand().equals("ADD VEHICLE")){
            new addVehicle().setVisible(true);
        }
        else if (ae.getActionCommand().equals("ADD UNIT")){
            new addUnit().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }
}
