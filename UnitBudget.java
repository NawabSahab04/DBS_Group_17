import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitBudget extends JFrame implements ActionListener {
    JTextField unitIdField;
    JButton calculateButton;
    conn connection = new conn();

    UnitBudget() {
        JLabel unitIdLabel = new JLabel("Unit ID:");
        unitIdLabel.setFont(new Font("Serif", Font.BOLD, 10));
        unitIdLabel.setBounds(60, 30, 120, 30);
        add(unitIdLabel);

        unitIdField = new JTextField();
        unitIdField.setBounds(200, 30, 150, 30);
        add(unitIdField);

        calculateButton = new JButton("Calculate Budget");
        calculateButton.setBounds(150, 80, 150, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);

        setLayout(null);
        setBounds(400, 200, 500, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String unitId = unitIdField.getText();
        if (e.getSource() == calculateButton) {
            if (isValidUnitId(unitId)) {
                String[] choices = {"Weapon Budget", "Vehicle Budget", "Soldier Budget"};
                String selectedOption = (String) JOptionPane.showInputDialog(null, "What do you want to calculate?",
                        "Select Budget Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

                if (selectedOption != null) {
                    switch (selectedOption) {
                        case "Weapon Budget":
                            // Calculate weapon budget logic using unitId
                            // Placeholder code
                            //JOptionPane.showMessageDialog(null, "Calculate Weapon Budget for Unit " + unitId);
                            calculateWeaponBudget(unitId);
                            calculateWeaponBudgetI(unitId);
                            break;
                        case "Vehicle Budget":
                            // Calculate vehicle budget logic using unitId
                            // Placeholder code
                            //JOptionPane.showMessageDialog(null, "Calculate Vehicle Budget for Unit " + unitId);
                            showTotalVehiclePrice(unitId);
                            showVehicleCounts(unitId);
                            break;
                        case "Soldier Budget":
                            // Calculate soldier budget logic using unitId
                            // Placeholder code
                            showTotalSalaries(unitId);
                            //JOptionPane.showMessageDialog(null, "Calculate Soldier Budget for Unit " + unitId);
                            break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Unit ID!");
            }
            dispose();
        }
    }

    private boolean isValidUnitId(String unitId) {
        try {
            String query = "SELECT COUNT(*) FROM unit WHERE unit_id = '" + unitId + "'";
            ResultSet resultSet = connection.s.executeQuery(query);
            resultSet.next(); // Move cursor to the first row
            int count = resultSet.getInt(1); // Get the value of the first column
            return count > 0; // If count > 0, unit ID exists; otherwise, it's invalid
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any potential SQL exceptions
            return false; // Return false in case of any exceptions
        }

    }

    private void calculateWeaponBudget(String unitId) {
        // Calculate weapon budget for the specified unit
        try {
            String query = "SELECT SUM(W.weapon_price) AS weapon_budget " +
                    "FROM UNIT UN "+
                    "JOIN ALLOTED A ON UN.UNIT_ID = A.UNIT_ID " +
                    "JOIN SOLDIER_WEAPON SW ON SW.SOLDIER_ID = A.SOLDIER_ID " +
                    "JOIN WEAPON W ON W.WEAPON_ID = SW.WEAPON_ID " +
                    "WHERE UN.UNIT_ID = '" + unitId + "'";



            ResultSet resultSet = connection.s.executeQuery(query);
            if (resultSet.next()) {
                double weaponBudget = resultSet.getDouble("weapon_budget");
                String formattedBudget = String.format("%.12f", weaponBudget); // Format the budget to display 2 decimal places
                JOptionPane.showMessageDialog(null, "Weapon Budget for Unit " + unitId + ": $" + weaponBudget);
            } else {
                JOptionPane.showMessageDialog(null, "No data found for Unit " + unitId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while calculating weapon budget");
        }
        dispose();
    }

    private void calculateWeaponBudgetI(String unitId) {
        // Calculate weapon budget for the specified unit
        try {
            String query = "SELECT W.WEAPON_NAME, COUNT(W.WEAPON_ID) AS weapon_count " +
                    "FROM UNIT UN " +
                    "JOIN ALLOTED A ON UN.UNIT_ID = A.UNIT_ID " +
                    "JOIN SOLDIER_WEAPON SW ON SW.SOLDIER_ID = A.SOLDIER_ID " +
                    "JOIN WEAPON W ON W.WEAPON_ID = SW.WEAPON_ID " +
                    "WHERE UN.UNIT_ID = '" + unitId + "'" +
                    "GROUP BY W.WEAPON_NAME"; // Group by weapon name to count the number of each weapon

            // Execute the query
            ResultSet resultSet = connection.s.executeQuery(query);

            // Create a new JWindow
            JWindow resultWindow = new JWindow();
            JPanel panel = new JPanel(new GridLayout(0, 1));

            // Add a label for each result row
            while (resultSet.next()) {
                String weaponName = resultSet.getString("WEAPON_NAME");
                int weaponCount = resultSet.getInt("weapon_count");
                JLabel label = new JLabel("Weapon Name: " + weaponName + ", Count: " + weaponCount);
                panel.add(label);
            }

            // Add the panel to the window and set its size
            resultWindow.add(new JScrollPane(panel)); // Add a scroll pane to handle large content
            resultWindow.setSize(400, 300); // Adjusted size for better visibility
            resultWindow.setLocationRelativeTo(null); // Center the window on the screen
            resultWindow.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while calculating weapon budget");
        }
        dispose();
    }

    private double calculateTotalVehiclePrice(String unitId) {
        double totalVehiclePrice = 0.0; // Initialize total vehicle price

        try {
            // Construct the SQL query
            String query = "SELECT SUM(VP.PRICE) AS total_price " +
                    "FROM UNIT U " +
                    "JOIN OWNS OW ON OW.UNIT_ID = U.UNIT_ID " +
                    "JOIN VEHICLE V ON V.VEHICLE_ID = OW.VEHICLE_ID " +
                    "JOIN VEHICLE_PRICE VP ON V.VTYPE = VP.VTYPE " +
                    "WHERE U.UNIT_ID = '" + unitId + "'";

            // Execute the query
            ResultSet resultSet = connection.s.executeQuery(query);

            // Retrieve the total vehicle price from the result set
            if (resultSet.next()) {
                totalVehiclePrice = resultSet.getDouble("total_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while calculating total vehicle price");
        }

        return totalVehiclePrice;
    }

    private void showTotalVehiclePrice(String unitId) {
        double totalVehiclePrice = calculateTotalVehiclePrice(unitId);
        JOptionPane.showMessageDialog(null, "Total Vehicle Price for Unit " + unitId + ": $" + totalVehiclePrice);
        dispose();
    }

    private void showVehicleCounts(String unitId) {
        try {
            // Construct the SQL query
            String query = "SELECT V.VEHICLE_NAME, COUNT(V.VEHICLE_ID) AS vehicle_count " +
                    "FROM UNIT U " +
                    "JOIN OWNS OW ON OW.UNIT_ID = U.UNIT_ID " +
                    "JOIN VEHICLE V ON V.VEHICLE_ID = OW.VEHICLE_ID " +
                    "WHERE U.UNIT_ID = '" + unitId + "'" +
                    "GROUP BY V.VEHICLE_NAME";

            // Execute the query
            ResultSet resultSet = connection.s.executeQuery(query);

            // Create a new JWindow
            JWindow resultWindow = new JWindow();
            JPanel panel = new JPanel(new GridLayout(0, 1));

            // Add a label for each result row
            while (resultSet.next()) {
                String vehicleName = resultSet.getString("VEHICLE_NAME");
                int vehicleCount = resultSet.getInt("vehicle_count");
                JLabel label = new JLabel("Vehicle Name: " + vehicleName + ", Count: " + vehicleCount);
                panel.add(label);
            }

            // Add the panel to the window and set its size
            resultWindow.add(new JScrollPane(panel)); // Add a scroll pane to handle large content
            resultWindow.setSize(400, 300); // Adjusted size for better visibility
            resultWindow.setLocationRelativeTo(null); // Center the window on the screen
            resultWindow.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching vehicle counts");
        }
        dispose();
    }


    private void showTotalSalaries(String unitId) {
        try {
            // Construct the SQL query
            String query = "SELECT SUM(SF.SALARY) AS total_salary " +
                    "FROM UNIT U " +
                    "JOIN ALLOTED A ON A.UNIT_ID = U.UNIT_ID " +
                    "JOIN SOLDIER_FINANCE SF ON SF.SOLDIER_ID = A.SOLDIER_ID " +
                    "WHERE A.UNIT_ID = '" + unitId + "'";

            // Execute the query
            ResultSet resultSet = connection.s.executeQuery(query);

            // Retrieve the total salary from the result set
            double totalSalary = 0.0;
            if (resultSet.next()) {
                totalSalary = resultSet.getDouble("total_salary");
            }

            // Create a new JWindow to display the result
            JWindow resultWindow = new JWindow();
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Total Salaries for Unit " + unitId + ": $" + totalSalary);
            panel.add(label);
            resultWindow.add(panel);

            // Set the size and visibility of the window
            resultWindow.setSize(300, 100);
            resultWindow.setLocationRelativeTo(null); // Center the window on the screen
            resultWindow.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while fetching total salaries");
        }
        dispose();
    }



    public static void main(String[] args) {
        new UnitBudget().setVisible(true);
    }
}
