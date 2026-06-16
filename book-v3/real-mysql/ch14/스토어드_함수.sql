DELIMITER $$

CREATE FUNCTION sf_sum(param1 INT, param2 INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE param3 INT DEFAULT 0;
    SET param3 = param1 + param2;
    RETURN param3;
END$$

DELIMITER ;

select sf_sum(1, 2)
