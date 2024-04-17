# DBS_Group_17
Military System Application
This Java application demonstrates a user interface to retrieve soldier information based on their ID and display associated weapon details from a MySQL database.This project provides a user interface for managing military resources efficiently.

Features
Soldier Weapon Lookup
Allows retrieval of weapon details associated with a soldier ID.
Usage
Soldier ID Input
Enter a valid soldier ID into the provided text field.
Submit
Click "Submit" to retrieve and display weapon details for the specified soldier ID.
Cancel
Click "Cancel" to return to the main dashboard.
How It Works
Database Query
The application connects to a MySQL database using the conn class.
Executes a SQL query to retrieve weapon details (weapon_name, first_name, last_name) associated with the provided soldier ID.
Requirements
Java Development Kit (JDK)
MySQL Database Server
NetBeans IDE (or similar)
Setup
Database Configuration

Ensure MySQL server is running.
Update database connection details in conn.java for proper database connectivity.
Import Project

Open NetBeans IDE.
Import the "militarySystem" project.
Run Application

Locate and run soldierWeapon.java as the main class.
Dependencies
Swing (javax.swing)

Used for GUI components (JFrame, JTable, JButton, JTextField).
AWT (java.awt)

Utilized for font and layout adjustments.
SQL (java.sql)

Manages database connections and executes SQL queries.
Protean (net.proteanit.sql)

Enables easy population of JTable with ResultSet data (DbUtils.resultSetToTableModel(rs)).
Troubleshooting
Ensure proper database connectivity.
Verify required libraries are included in the project build path.
Contributors
Developed by:
Vishnu Chebolu- 2022A7PS0124P
Virendrasinh mane-2022A7PS1175P
Samrath Singh Khanuja- 2022A7PS1171P
Sohan Reddy Julakanti-2022A7PS1177P
Harsh raj- 2022A7PS1179P
