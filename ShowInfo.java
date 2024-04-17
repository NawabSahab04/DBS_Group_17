import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ShowInfo extends JFrame implements ActionListener {
    JTextField soldierIdField;
    JButton showInfoButton;
    JTable infoTable;
    JScrollPane scrollPane;
    conn connection;

    public ShowInfo() {
        JLabel idLabel = new JLabel("Soldier ID:");
        soldierIdField = new JTextField();
        showInfoButton = new JButton("Show Info");

        idLabel.setBounds(60, 30, 120, 30);
        soldierIdField.setBounds(200, 30, 150, 30);
        showInfoButton.setBounds(200, 80, 150, 30);

        showInfoButton.addActionListener(this);

        add(idLabel);
        add(soldierIdField);
        add(showInfoButton);

        setLayout(null);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        connection = new conn(); // Initialize the connection object
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showInfoButton) {
            String soldierId = soldierIdField.getText();

            try {
                CallableStatement stmt = connection.c.prepareCall("{call SHOWINFOF(?)}");
                stmt.setString(1, soldierId);
                ResultSet rs = stmt.executeQuery();

                // Create a table model to hold the data
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Soldier ID");
                model.addColumn("Name"); // Here, "Name" represents the concatenation of FIRST_NAME and LAST_NAME
                model.addColumn("Retired Status");
                model.addColumn("Date of Birth");
                model.addColumn("Unit Name");
                model.addColumn("Location");
                model.addColumn("Unit Size");
                model.addColumn("Weapon");
                model.addColumn("Salary");

                // Add rows to the model
                while (rs.next()) {
                    Object[] row = {
                            rs.getString("SOLDIER_ID"),
                            rs.getString("NAME"), // Use "NAME" instead of "FIRST_NAME" or "LAST_NAME"
                            rs.getString("RETIRED_STATUS"),
                            rs.getDate("DATE_OF_BIRTH"),
                            rs.getString("UNIT_NAME"),
                            rs.getString("LOCATION"),
                            rs.getInt("SIZE"),
                            rs.getString("WEAPON"),
                            rs.getDouble("SALARY")
                    };
                    model.addRow(row);
                }

                // Create the table and scroll pane
                infoTable = new JTable(model);
                scrollPane = new JScrollPane(infoTable);

                // Add the scroll pane to the frame
                scrollPane.setBounds(20, 130, 760, 200);
                add(scrollPane);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ShowInfo();
    }
}
