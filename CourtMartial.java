import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CourtMartial extends JFrame implements ActionListener {
    JTextField t1;
    JButton b1;

    public CourtMartial() {
        JLabel l1 = new JLabel("COURT MARTIAL");
        l1.setFont(new Font("Serif", Font.BOLD, 15));
        l1.setBounds(200, 20, 150, 20);
        add(l1);

        JLabel soldier_id = new JLabel("SOLDIER ID");
        soldier_id.setFont(new Font("Serif", Font.PLAIN, 16));
        soldier_id.setBounds(60, 80, 120, 30);
        add(soldier_id);

        t1 = new JTextField();
        t1.setBounds(200, 80, 150, 30);
        add(t1);

        b1 = new JButton("Court Martial");
        b1.setBounds(60, 180, 130, 30);
        b1.addActionListener(this);
        add(b1);

        setBounds(530, 200, 450, 300);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String soldier_id = t1.getText();
            conn c = new conn();
            try {
                String str = "CALL COURT_MARTIAL4('" + soldier_id + "')";
                c.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "Court Martial Successful");
                this.setVisible(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new CourtMartial().setVisible(true);
    }
}
