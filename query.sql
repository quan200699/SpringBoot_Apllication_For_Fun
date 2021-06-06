DELIMITER //
CREATE TRIGGER add_loyalty BEFORE INSERT
    on debtor
    FOR EACH ROW
BEGIN
    IF NEW.loyalty_id is null then set NEW.loyalty_id = 1;
    END IF;
END //
