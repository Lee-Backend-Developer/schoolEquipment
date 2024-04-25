-- 장비 추가
insert into EQUIPMENT (name, count) values('pmw-200', 30);
insert into EQUIPMENT (name, count) values('ace tripod', 10);

-- 수업시간 추가
insert into classtimetable (class_name, day_of_week, TWO_TIME, THREE_TIME, FOUR_TIME)
values ('배우연기연출', 'Monday', true, true, true);

insert into classtimetable (class_name, day_of_week, TWO_TIME, THREE_TIME, FOUR_TIME)
values ('영상촬영실습', 'Monday', true, true, true);

insert into classtimetable (class_name, day_of_week, TWO_TIME, THREE_TIME, FOUR_TIME)
values ('영상제작개론및실습', 'Monday', true, true, true);

-- 장비대여
insert into rental(classtimetable_id, equipment_id) VALUES ( 1,1);
