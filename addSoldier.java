import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addSoldier extends JFrame implements ActionListener {
    JTextField t1,t2,t3,t4,t5;
    JComboBox c1;
    JButton b1;
    JRadioButton yes;
    conn c;
    addSoldier(){
        c = new conn();
        JLabel id = new JLabel("Soldier ID");
        id.setFont(new Font("Serif", Font.BOLD, 10));
        id.setBounds(60,30,120,30);
        add(id);

        t1 = new JTextField();
        t1.setBounds(200,30,150,30);
        add(t1);

        JLabel rank = new JLabel("Soldier Rank");
        rank.setFont(new Font("Serif", Font.BOLD, 10));
        rank.setBounds(60,80,120,30);
        add(rank);

        String ranks[] = {"Lieutenant","Captain","Major","Colonel","Commander"};
        c1 = new JComboBox(ranks);
        c1.setBounds(200,80,150,30);
        add(c1);

        JLabel fname = new JLabel("FIRST NAME");
        fname.setFont(new Font("Serif", Font.BOLD, 10));
        fname.setBounds(60,130,120,30);
        add(fname);

        t2 = new JTextField();
        t2.setBounds(200,130,150,30);
        add(t2);

        JLabel lname = new JLabel("LAST NAME");
        lname.setFont(new Font("Serif", Font.BOLD, 10));
        lname.setBounds(60,180,120,30);
        add(lname);

        t3 = new JTextField();
        t3.setBounds(200,180,150,30);
        add(t3);

        JLabel retired_status = new JLabel("RETIREMENT STATUS");
        retired_status.setFont(new Font("Serif", Font.BOLD, 10));
        retired_status.setBounds(60,230,120,30);
        add(retired_status);

        yes = new JRadioButton("RETIRED");
        yes.setBackground(Color.WHITE);
        yes.setBounds(200, 230, 70, 27);
        add(yes);


        JLabel dob = new JLabel("DATE OF BIRTH");
        dob.setFont(new Font("Serif", Font.BOLD, 10));
        dob.setBounds(60,280,120,30);
        add(dob);

        t5 = new JTextField();
        t5.setBounds(200,280,150,30);
        add(t5);

        b1 =  new JButton("SUBMIT");
        b1.setBounds(200,330,150,30);
        b1.addActionListener(this);
        add(b1);

        setLayout(null);
        setBounds(400,200,500,450);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String id = t1.getText();
        String fname = t2.getText();
        String lname = t3.getText();
        String dob = t5.getText();
        String retired_status = yes.isSelected() ? "Retired" : "Active";
        String rank = (String) c1.getSelectedItem();

        String str = "INSERT INTO soldier VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = c.c.prepareStatement(str);
            pstmt.setString(1, id);
            pstmt.setString(2, rank);
            pstmt.setString(3, fname);
            pstmt.setString(4, lname);
            pstmt.setString(5, retired_status);
            pstmt.setString(6, dob);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Soldier Added");
            this.setVisible(false);
        } catch (SQLException ex) {
            String errorMessage;
            if (ex.getMessage().toLowerCase().contains("date format")) {
                errorMessage = "Please enter the date in yyyy-MM-dd format.";
            } else {
                errorMessage = "An error occurred while adding the soldier to the database.";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    public static void main(String[] args) {
        new addSoldier().setVisible(true);
    }
}
