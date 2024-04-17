import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
    JMenuBar mb;
    JMenu m1,m2;
    JMenuItem i1,i2,i3,i4,i5, i6, i7;

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
        i2 = new JMenuItem("SOLDIER WEAPON");
        i2.addActionListener(this);
        m2.add(i2);
        i6 = new JMenuItem("COURT MARTIAL");
        i6.addActionListener(this);
        m2.add(i6);

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
        else if (ae.getActionCommand().equals("SOLDIER WEAPON")){
            new soldierWeapon().setVisible(true);
        }
        else if (ae.getActionCommand().equals("COURT MARTIAL")){
            new deleteSoldier().setVisible(true);
        }
        else if (ae.getActionCommand().equals("UPDATE")){
            new addUnit().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }
}
