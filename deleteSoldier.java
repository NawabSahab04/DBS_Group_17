import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class deleteSoldier extends JFrame implements ActionListener {
    JTextField t1;
    JButton b1;
    deleteSoldier(){
        JLabel id = new JLabel("Soldier ID");
        id.setFont(new Font("Serif", Font.BOLD, 10));
        id.setBounds(60,30,120,30);
        add(id);

        t1 = new JTextField();
        t1.setBounds(200,30,150,30);
        add(t1);

        b1 =  new JButton("SUBMIT");
        b1.setBounds(200,330,150,30);
        b1.addActionListener(this);
        add(b1);

        setLayout(null);
        setBounds(400,200,500,450);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String id = t1.getText();
        conn c = new conn();

        String str = "delete from soldier where soldier_id = '"+id+"'";
        //changes in mysql command line also have to be made
        try{
            c.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null,"SOLDIER REMOVED");
            this.setVisible(false);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void main(String[] args) {
        new deleteSoldier().setVisible(true);
    }
}
