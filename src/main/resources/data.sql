-- 장비 추가
insert into EQUIPMENT (name, count) values('pmw-200', 30);
insert into EQUIPMENT (name, count) values('ace tripod', 10);

-- 수업시간 추가
insert into classtimetable (class_name, day_of_week)
values ('영화예술과', '월요일');

-- 장비대여
insert into rental(classtimetable_id, equipment_id) VALUES ( 1,1);
