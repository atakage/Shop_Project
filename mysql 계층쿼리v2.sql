use secur;

drop table tbl_test;

CREATE TABLE tbl_test(

id bigint auto_increment primary key,
facility_code varchar(5),
p_id int,
val varchar(100),
groupId int

);

INSERT INTO tbl_test(facility_code, p_id, val, groupId) VALUES('P001',1,"첫 번째 부모",0);
INSERT INTO tbl_test(facility_code, p_id, val, groupId) VALUES('P001',2,"두 번째 부모",0);
INSERT INTO tbl_test(facility_code, p_id, val, groupId) VALUES('P002',3,"세 번째 부모",0);
INSERT INTO tbl_test(facility_code, p_id,  val, groupId) VALUES('P001',1,"첫 번째 부모의 자식",1);
INSERT INTO tbl_test(facility_code, p_id,  val, groupId) VALUES('P001',3,"세 번째 부모의 자식2",1);


SELECT * FROM tbl_test;

SELECT * FROM tbl_test ORDER BY p_id ASC, groupId ASC;





----------------------


QaVO

groupId 컬럼 추가

q는 input될 때 p_id는 id값으로 설정, groupId는 0으로
a는 input될 때 groupId를 1로 설정

auto increment는 2개 이상 불가능하니 q input 시 input 후 update 방식으로 p_id값을 넣어야...........