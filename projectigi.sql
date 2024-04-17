CREATE DATABASE PROJECTIGIDO2;

USE PROJECTIGIDO2;

CREATE TABLE Soldier (
    Soldier_ID VARCHAR(50) PRIMARY KEY,
    Soldier_Rank VARCHAR(50),
    First_Name VARCHAR(50),
    Last_Name VARCHAR(50),
    Retired_Status VARCHAR(10),
    Date_Of_Birth DATE
);

CREATE TABLE Soldier_Code_Name (
    Soldier_Count INT,
    -- Soldier count will check the number of soldiers who have the same first and last name
    First_Name VARCHAR(50),
    Last_Name VARCHAR(50),
    Code_Name VARCHAR(50),
    PRIMARY KEY (First_Name, Last_Name)
    
    -- Delete cascade ensures that the code names are also deleted along with the sol
);

-- Create a trigger where soldier_code_name is inserted automatically
DELIMITER //

CREATE TRIGGER insert_soldier_code_name
AFTER INSERT ON Soldier
FOR EACH ROW
BEGIN
    DECLARE TEMP_COUNT INT;

    -- Check if the soldier already exists
    SELECT Soldier_Count
    INTO TEMP_COUNT 
    FROM Soldier_Code_Name 
    WHERE First_Name = NEW.First_Name
    AND Last_Name = NEW.Last_Name
    FOR UPDATE;

    -- If the soldier exists, increment the count and exit
    IF TEMP_COUNT IS NOT NULL THEN
        SET TEMP_COUNT  = TEMP_COUNT + 1;
        
        UPDATE SOLDIER_CODE_NAME
        SET SOLDIER_COUNT = TEMP_COUNT
        WHERE First_Name = NEW.First_Name 
        AND Last_Name = NEW.Last_Name;
    ELSE
        -- Otherwise, insert the soldier and code name into Soldier_Code_Name table
        INSERT INTO Soldier_Code_Name (Soldier_Count, First_Name, Last_Name, Code_Name) 
        VALUES (1, NEW.First_Name, NEW.Last_Name, CONCAT(REVERSE(NEW.First_Name), REVERSE(NEW.Last_Name)));
    END IF;

END;
//

DELIMITER ;

CREATE TABLE Weapon (
    Weapon_ID INT PRIMARY KEY,
    Soldier_Rank VARCHAR(50),
    Weapon_Name VARCHAR(100),
    Weapon_Price INT
);

-- Insert weapons for the five different ranks
INSERT INTO Weapon (Weapon_ID, Soldier_Rank, Weapon_Name, Weapon_PRICE)
VALUES
    (1, 'Major', 'M16A4', 100000),
    (2, 'Colonel', 'M4A1', 500000),
    (3, 'Lieutenant', 'M9 Beretta', 10),
    (4, 'Captain', 'M249 SAW', 1000000),
    (5, 'Commander', 'M1911', 500);

-- Insert weapons for soldiers of rank Major

-- TO RESOLVE THE CIRCULAR DEPENDENCY HAD TO DELETE THE RATION_ID ATTRIBUTE FROM THE UNIT 
-- TABLE ONLY RATION REFERENCES THE UNIT NOW
CREATE TABLE Unit (
    Unit_ID INT PRIMARY KEY,
    Location VARCHAR(100),
    Size INT,
    Commander_ID VARCHAR(100),
    Unit_Name VARCHAR(100)
);

CREATE TABLE Ration (
    Ration_ID INT PRIMARY KEY,
    Unit_ID INT,
    Meal VARCHAR(100),
    Cost DECIMAL(10, 2)
);

ALTER TABLE Ration
ADD FOREIGN KEY (Unit_ID) REFERENCES Unit (Unit_ID) ON DELETE CASCADE;

-- CICULAR DEPENDENCY AMONG UNIT ID AND RATION IDS REFERENCE EACH OTHER
-- NEED TO BE REMOVED
-- Insert 5 units into the Unit table
INSERT INTO Unit (Unit_ID, Location, Size, Commander_ID, Unit_Name)
VALUES
    (1, 'Mount Olympus', 0,100000,'Zeus'),
    (2, 'Valhalla', 0, 200000,'Odin'),
    (3, 'Asgard', 0,300000, 'Thor'),
    (4, 'Elysium', 0, 400000,'Hades'),
    (5, 'Mount Parnassus',0, 500000, 'Apollo');
    

-- Insert rations for the 5 units into the Ration table
INSERT INTO Ration (Ration_ID, Unit_ID, Meal, Cost)
VALUES
    (101, 1, 'Ambrosia', 10.50),
    (102, 2, 'Mead and Meat', 8.75),
    (103, 3, 'Golden Apples', 12.25),
    (104, 4, 'Pomegranates', 9.85),
    (105, 5, 'Nectar and Honey', 11.20);

-- DONE WITH CREATING THE VEHICLES
CREATE TABLE Vehicle (
    Vehicle_ID INT PRIMARY KEY,
    Vehicle_Name VARCHAR(100),
    VType VARCHAR(100)
);

-- DONE WITH THE VEHICLE PRICE TABLE
CREATE TABLE Vehicle_Price (
	Vehicle_Count INT,
    VType VARCHAR(100) PRIMARY KEY,
    Price DECIMAL(65, 2)
);

-- TRIGGER INVOKED ON VEHICLE PRICE WHEN VEHICLE IS INSERTED
-- IF THE VEHICLE_TYPE IS NOT ONE OF THE 5 TYPES ALLOWED THE VEHICLE IS OF NO
-- USE TO THE MILTARY AND HENCE WILL NOT BE ALLOWED TO BE INSERTED
-- DEPENDING ON THE VEHICLE TYPE THE VEHICLE COUNT WILL BE INCREMENTED

INSERT INTO Vehicle_Price (Vehicle_Count, VType, Price)
VALUES 
(0, 'Main Battle Tank', 5000000.00),
(0, 'Infantry Fighting Vehicle', 2000000.00),
(0, 'Armored Personnel Carrier', 1500000.00),
(0, 'Utility Vehicle', 500000.00),
(0, 'Attack Helicopter', 10000000.00),
(0, 'Multirole Fighter', 30000000.00),
(0, 'Aircraft Carrier', 1000000000.00),
(0, 'Submarine', 500000000.00),
(0, 'Surface-to-Air Missile System', 20000000.00),
(0, 'Multiple Launch Rocket System', 10000000.00);

-- DONE WITH FINANCE
CREATE TABLE Soldier_Finance (
    Soldier_ID VARCHAR(50) PRIMARY KEY,
    Salary DECIMAL(10, 2),
    Account_Balance DECIMAL(10, 2),
    FOREIGN KEY (Soldier_ID) REFERENCES Soldier (Soldier_ID) ON DELETE CASCADE
);

-- NOT  DONE WITH SUPPLIES
CREATE TABLE Supplies (
    Unit_ID INT,
    Ration_ID INT,
    PRIMARY KEY (Unit_ID, Ration_ID),
    FOREIGN KEY (Unit_ID) REFERENCES Unit (Unit_ID) ON DELETE CASCADE,
    FOREIGN KEY (Ration_ID) REFERENCES Ration (Ration_ID) ON DELETE CASCADE
);


-- DONE WITH ALLOTED
CREATE TABLE Alloted (
    Unit_ID INT,
    Soldier_ID VARCHAR(50),
    Unit_Name VARCHAR(100),
    PRIMARY KEY (Unit_ID, Soldier_ID),
    FOREIGN KEY (Unit_ID) REFERENCES Unit (Unit_ID) ON DELETE CASCADE,
    FOREIGN KEY (Soldier_ID) REFERENCES Soldier (Soldier_ID) ON DELETE CASCADE
);

-- THINK IM DONE WITH OWNS (THINK)
CREATE TABLE Owns (
    Unit_ID INT,
    Vehicle_ID INT,
    PRIMARY KEY (Unit_ID, Vehicle_ID),
    FOREIGN KEY (Unit_ID) REFERENCES Unit (Unit_ID) ON DELETE CASCADE,
    FOREIGN KEY (Vehicle_ID) REFERENCES Vehicle (Vehicle_ID) ON DELETE CASCADE
);

-- Just assign the weapon according to the rank
CREATE TABLE Soldier_Weapon (
    SOLDIER_ID VARCHAR(50),
    Weapon_ID INT,
    PRIMARY KEY (Soldier_ID, Weapon_ID),
    FOREIGN KEY (Soldier_ID) REFERENCES Soldier (Soldier_ID) ON DELETE CASCADE,
    FOREIGN KEY (Weapon_ID) REFERENCES Weapon (Weapon_ID) ON DELETE CASCADE
);

DELIMITER //
CREATE TRIGGER ASSIGN_WEAPON
AFTER INSERT ON SOLDIER
FOR EACH ROW
BEGIN
    DECLARE TEMP_RANK VARCHAR(100);
    DECLARE TEMP_ID INT;

    -- Get the newly inserted soldier's rank
    SELECT NEW.SOLDIER_RANK INTO TEMP_RANK;

    -- Assign Weapon_ID randomly based on the soldier's rank
    CASE TEMP_RANK
        WHEN 'MAJOR' THEN SELECT Weapon_ID INTO TEMP_ID FROM Weapon WHERE Soldier_Rank = 'Major' ORDER BY RAND() LIMIT 1;
        WHEN 'COLONEL' THEN SELECT Weapon_ID INTO TEMP_ID FROM Weapon WHERE Soldier_Rank = 'Colonel' ORDER BY RAND() LIMIT 1;
        WHEN 'LIEUTENANT' THEN SELECT Weapon_ID INTO TEMP_ID FROM Weapon WHERE Soldier_Rank = 'Lieutenant' ORDER BY RAND() LIMIT 1;
        WHEN 'CAPTAIN' THEN SELECT Weapon_ID INTO TEMP_ID FROM Weapon WHERE Soldier_Rank = 'Captain' ORDER BY RAND() LIMIT 1;
        WHEN 'COMMANDER' THEN SELECT Weapon_ID INTO TEMP_ID FROM Weapon WHERE Soldier_Rank = 'Commander' ORDER BY RAND() LIMIT 1;
        ELSE SET TEMP_ID = NULL; -- Handle unrecognized ranks
    END CASE;

    -- Insert the assigned weapon into Soldier_Weapon table
    IF TEMP_ID IS NOT NULL THEN
        INSERT INTO SOLDIER_WEAPON (WEAPON_ID, SOLDIER_ID) VALUES (TEMP_ID, NEW.SOLDIER_ID);
    END IF;
END;
//

DELIMITER ;


DELIMITER //

CREATE TRIGGER CHECK_VEHICLE
BEFORE INSERT ON Vehicle
FOR EACH ROW
BEGIN
    DECLARE TEMP_VEHICLE_COUNT INT;
    DECLARE TEMP_VEHICLE_TYPE VARCHAR(100);

    SET TEMP_VEHICLE_TYPE = NEW.VType;

    -- Check if the vehicle type exists in the Vehicle_Price table
    SELECT Vehicle_Count
    INTO TEMP_VEHICLE_COUNT
    FROM Vehicle_Price
    WHERE VType = TEMP_VEHICLE_TYPE;

    IF TEMP_VEHICLE_COUNT IS NOT NULL THEN
        -- Increment the vehicle count if the vehicle type exists
        SET TEMP_VEHICLE_COUNT = TEMP_VEHICLE_COUNT + 1;
        UPDATE Vehicle_Price
        SET Vehicle_Count = TEMP_VEHICLE_COUNT
        WHERE VType = TEMP_VEHICLE_TYPE;
    ELSE
        -- If the vehicle type doesn't exist, reject the insertion
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid vehicle type. Cannot add the vehicle.';
    END IF;

END;
//

DELIMITER ;


DELIMITER //
DELIMITER $$
CREATE TRIGGER insert_soldier_assign_unit
AFTER INSERT ON Soldier
FOR EACH ROW
BEGIN
    DECLARE unit_id_value INT;
    DECLARE temp_rank VARCHAR(50);

    SET temp_rank = NEW.SOLDIER_RANK;

    IF temp_rank = 'COMMANDER' THEN
        SET unit_id_value = NEW.SOLDIER_ID / 100000; -- Example calculation, adjust as needed
    ELSE
        -- Select a random unit_id from the Unit table
        SELECT Unit_ID INTO unit_id_value FROM Unit ORDER BY RAND() LIMIT 1;
    END IF;

    -- Insert the soldier-unit relationship into Alloted table
    INSERT INTO Alloted (Unit_ID, Soldier_ID, Unit_Name)
    VALUES (unit_id_value, NEW.Soldier_ID, (SELECT Unit_Name FROM Unit WHERE Unit_ID = unit_id_value));

    -- Increase the variable count in the Unit table by 1
    UPDATE Unit SET SIZE = SIZE + 1 WHERE Unit_ID = unit_id_value;
END$$
DELIMITER ;

//

DELIMITER //
CREATE TRIGGER CHECK_SALARY
AFTER INSERT ON Soldier
FOR EACH ROW
BEGIN
    DECLARE TEMP_SAL DECIMAL(10, 2);
    DECLARE TEMP_RANK VARCHAR(50);
    -- Get the rank of the new soldier
    SET TEMP_RANK = NEW.Soldier_Rank;
    
    -- Calculate salary based on the rank
    CASE TEMP_RANK
        WHEN 'COMMANDER' THEN SET TEMP_SAL = 10000000;
        WHEN 'Major' THEN SET TEMP_SAL = 5000;
        WHEN 'Captain' THEN SET TEMP_SAL = 4000;
        WHEN 'Colonel' THEN SET TEMP_SAL = 6000;
        WHEN 'LIEUTENANT' THEN SET TEMP_SAL = 7000;
        ELSE SET TEMP_SAL = 3000; -- Default salary for other ranks
    END CASE;
    
    -- Update the SALARY and ACCOUNT_BALANCE in SOLDIER_FINANCE table
    INSERT INTO Soldier_Finance (Soldier_ID, Salary, Account_Balance)
    VALUES (NEW.Soldier_ID, TEMP_SAL, 0);
END;


//

DELIMITER ;

DELIMITER //
-- ASSIGN VECHICLES RANDOMLY

CREATE TRIGGER UN_VE
AFTER INSERT ON Vehicle
FOR EACH ROW
BEGIN 
	DECLARE TO_ASSIGN INT;
    
    SET TO_ASSIGN = NEW.Vehicle_ID % 5 + 1;
    
    INSERT INTO Owns (Unit_ID, Vehicle_ID)
    VALUES (TO_ASSIGN, NEW.Vehicle_ID);
END;
//

DELIMITER ;


INSERT INTO SUPPLIES (UNIT_ID,RATION_ID)

VALUES
(1,101),
(2,102),
(3,103),
(4,104),
(5,105);


