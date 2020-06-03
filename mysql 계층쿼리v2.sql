


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





-----------------------------------------------------------------------------


$.ajax({
       type: "POST"
       , contentType: "application/x-www-form-urlencoded"
       , url: "http://bemeal2.tistory.com/media/data"
       , data: "onLoad=%5Btype%20Function%5D&count=15&page=1"
       , async: true
       , error: function (res) {
              console.log("ajax error - " + res);
       }
       , success: function (res) {
              console.log("ajax success - " + res);
       }
       , beforeSend: function () {
              var width = 0;
              var height = 0;
              var left = 0;
              var top = 0;

              width = 50;
              height = 50;


              top = ( $(window).height() - height ) / 2 + $(window).scrollTop();
              left = ( $(window).width() - width ) / 2 + $(window).scrollLeft();

 

              if($("#div_ajax_load_image").length != 0) {
                     $("#div_ajax_load_image").css({
                            "top": top+"px",
                            "left": left+"px"
                     });
                     $("#div_ajax_load_image").show();
              }
              else {
                     $('body').append('<div id="div_ajax_load_image" style="position:absolute; top:' + top + 'px; left:' + left + 'px; width:' + width + 'px; height:' + height + 'px; z-index:9999; background:#f0f0f0; filter:alpha(opacity=50); opacity:alpha*0.5; margin:auto; padding:0; "><img src="file://D:\\temp\\ajax_loader.gif" style="width:50px; height:50px;"></div>');
              }

       }
       , complete: function () {
                     $("#div_ajax_load_image").hide();
       }
});


beforesend: ajax 호출 전 실행
complete: 성공여부 관계없이 끝나고 무조건 실행됨







------------------------------------------------------------------------------------------------------------------

삭제 문제 때문에 select로 추가 갱신 힘듦
list 다 불러오고 view에서 display block 추가 처리
view페이지에 미답변/답변 탭 분리시켜놓고 
ajax로 붙이기 
<c:foreach>로 일단 다 불러오되 6번째 부터는 display none으로 해놓기