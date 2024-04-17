USE PROJECTIGIDO;
DELIMITER //

CREATE PROCEDURE RemoveSoldierF(IN s_id VARCHAR(50))
BEGIN
    DECLARE unit_id_value INT;

    -- Get the unit ID of the soldier
    SELECT Unit_ID INTO unit_id_value FROM Alloted WHERE Soldier_ID = s_id;

    -- Delete records from the Soldier_Weapon table
    -- DELETE FROM Soldier_Weapon WHERE SOLDIER_ID = s_id;

    -- Delete the soldier from the Alloted table
    DELETE FROM Alloted WHERE Soldier_ID = s_id;
    
    CALL RetireSoldierF(s_id); -- Changed S_ID to s_id

    -- Delete the soldier from the Soldier table
    UPDATE SOLDIER 
    SET RETIRED_STATUS = 'RETIRED'
    WHERE SOLDIER_ID = s_id; -- Changed S_ID to s_id

    -- Decrease the variable count in the Unit table by 1
    UPDATE Unit SET SIZE = SIZE - 1 WHERE Unit_ID = unit_id_value;
END //

CREATE PROCEDURE RetireSoldierF(IN s_id VARCHAR(50))
BEGIN
    DECLARE join_date DATE;
    DECLARE curret_date DATE;
    DECLARE service_duration INT;
    DECLARE FINAL_BALANCE DECIMAL(10, 2);

    -- Get the join date of the soldier
    SELECT Date_Of_Birth INTO join_date FROM Soldier WHERE Soldier_ID = s_id;

    -- Calculate the service duration
    SET curret_date = CURDATE();
    SET service_duration = DATEDIFF(curret_date, join_date);

    -- Update the soldier's salary in SOLDIER_FINANCE table
    SELECT ACCOUNT_BALANCE INTO FINAL_BALANCE FROM Soldier_Finance WHERE Soldier_ID = s_id;
    SET FINAL_BALANCE = FINAL_BALANCE + (service_duration * 0.2);

    UPDATE SOLDIER_FINANCE 
    SET ACCOUNT_BALANCE = FINAL_BALANCE
    WHERE Soldier_ID = s_id;

    -- Update the soldier's retired status in the Soldier table
    UPDATE SOLDIER 
    SET RETIRED_STATUS = 'RETIRED'
    WHERE SOLDIER_ID = s_id;
END; //



CREATE PROCEDURE COURT_MARTIAL4(IN SOL_ID VARCHAR(50))
BEGIN
    DECLARE UNIT_ID_VALUE INT ; -- Assuming UNIT_ID_VALUE is intended to be declared here
    
    SELECT UNIT_ID INTO UNIT_ID_VALUE FROM ALLOTED WHERE SOLDIER_ID = SOL_ID;
    
    DELETE FROM SOLDIER_WEAPON WHERE SOLDIER_ID = SOL_ID;
    
    UPDATE SOLDIER
    SET Retired_status = 'MARTIALED' -- Changed Retired_status to RETIRED_STATUS
    WHERE SOLDIER_ID = SOL_ID;
    
    UPDATE SOLDIER_FINANCE
    SET SALARY = 0
    WHERE SOLDIER_ID = SOL_ID;
    
    UPDATE UNIT 
    SET SIZE = SIZE - 1
    WHERE UNIT_ID = UNIT_ID_VALUE;
END //


CREATE PROCEDURE SHOWINFOF(IN SOL_ID VARCHAR(50))
BEGIN
	DECLARE TEMP_ID VARCHAR(50);
    
    SELECT SOLDIER_ID INTO TEMP_ID FROM SOLDIER WHERE SOLDIER_ID = SOL_ID;
    
    -- IF SOLDIER DOES NOT EXIST RAISE AN EXCEPTION
    IF TEMP_ID IS NOT NULL THEN
        SELECT DISTINCT 
			S.SOLDIER_ID,
			CONCAT(S.FIRST_NAME, ' ', S.LAST_NAME) AS NAME,
			S.RETIRED_STATUS,
			S.DATE_OF_BIRTH,
			A.UNIT_NAME,
			U.LOCATION,
			U.SIZE,
			W.WEAPON_NAME AS WEAPON,
			SF.SALARY
		FROM 
			SOLDIER S
			JOIN ALLOTED A ON A.SOLDIER_ID = S.SOLDIER_ID
			JOIN UNIT U ON U.UNIT_ID = A.UNIT_ID
			JOIN SOLDIER_WEAPON SW ON S.SOLDIER_ID = SW.SOLDIER_ID
			JOIN WEAPON W ON W.WEAPON_ID = SW.WEAPON_ID
			JOIN SOLDIER_FINANCE SF ON S.SOLDIER_ID = SF.SOLDIER_ID
		WHERE 
			S.SOLDIER_ID = TEMP_ID;

	ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Soldier does not exist';
    END IF;
END //

