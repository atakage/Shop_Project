with RECURSIVE CTE AS(

		SELECT facility_code,id, p_id, val FROM tbl_test WHERE  id = '1'
		
        UNION all
        
        SELECT t.facility_code, t.id, t.p_id, t.val FROM tbl_test t
        INNER JOIN CTE c on c.id = t.p_id 
)
SELECT * FROM CTE;

-- 일단 P001이며 p_id null인 값의 List<id> 들 전부 불러온 뒤 그 List<id>들로 for문 돌려서 최종산출

SELECT facility_code,id, p_id, val FROM tbl_test WHERE  id = '1';

SELECT * FROM tbl_test;

use secur;





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
INSERT INTO tbl_test(facility_code, p_id,  val) VALUES('P001',4,"첫 번째 부모의 자식의 자식");
