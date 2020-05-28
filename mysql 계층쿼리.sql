use secur;

drop table tbl_test;

CREATE TABLE tbl_test(

id bigint auto_increment primary key,
facility_code varchar(5),
p_id int,
val varchar(100)

);

INSERT INTO tbl_test(facility_code,  val) VALUES('P001',"첫 번째 부모");
INSERT INTO tbl_test(facility_code,  val) VALUES('P001',"두 번째 부모");
INSERT INTO tbl_test(facility_code,  val) VALUES('P002',"세 번째 부모");
INSERT INTO tbl_test(facility_code, p_id,  val) VALUES('P001',1,"첫 번째 부모의 자식");
INSERT INTO tbl_test(facility_code, p_id,  val) VALUES('P001',1,"첫 번째 부모의 자식2");


SELECT * FROM tbl_test;



DROP FUNCTION IF EXISTS fnc_hierarchi;

DELIMITER $$

CREATE FUNCTION fnc_hierarchi() RETURNS INT NOT deterministic reads sql data
begin
	DECLARE v_id INT;
    DECLARE v_parent INT;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET @id = NULL;
    
    SET v_parent = @id;
    SET v_id = -1;
    
    IF @id IS NULL THEN RETURN NULL;
    END IF;
    
    LOOP
    
    SELECT MIN(id) INTO @id FROM tbl_test WHERE p_id = v_parent AND id > v_id;
    
    IF (@id IS NOT NULL) OR (v_parent = @start_with) THEN SET @level = @level +1;
    RETURN @id;
    END IF;


	SET @level := @level -1;
    
    SELECT id, p_id INTO v_id, v_parent FROM ANIMAL WHERE id = v_parent;
    
    END LOOP;
    
END

$$

DELIMITER ;











SELECT CASE WHEN LEVEL-1 > 0 then CONCAT(CONCAT(REPEAT('  ', level-1),'ㄴ'), test.val)
		else test.val END AS val, test.id, test.p_id, fnc.level
        FROM (SELECT fnc_hierarchi() AS id, @level AS level FROM
			(SELECT @start_with:=0, @id:=@start_with, @level:=0) vars JOIN tbl_test WHERE @id IS NOT NULL) fnc JOIN tbl_test test ON fnc.id = test.id;
            
            
            
            SELECT id, p_id, val FROM tbl_test WHERE p_id = 1
    UNION ALL
    SELECT r.id, r.p_id, r.val FROM tbl_test r;
            
            

            
SELECT id, p_id, val FROM(
	SELECT * FROM tbl_test ORDER BY p_id, id) tbl_test,
    (SELECT @pv := '3') initialisation WHERE find_in_set(p_id, @pv) AND length(@pv := concat(@pv,',',id));
    
    
with RECURSIVE CTE AS(

		SELECT id, p_id, val FROM tbl_test WHERE  id = '1'
		
        UNION all
        
        SELECT t.id, t.p_id, t.val FROM tbl_test t
        INNER JOIN CTE c on c.id = t.p_id
)
SELECT * FROM CTE;

