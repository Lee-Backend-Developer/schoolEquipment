-- 장비 추가
insert into EQUIPMENT (name, count) values('pmw-200', 30);
insert into EQUIPMENT (name, count) values('ace tripod', 10);

-- 수업시간 추가
--      월요일
insert into classTimeList (class_name, day_of_week, TWO_TIME, THREE_TIME, FOUR_TIME)
values ('배우연기연출', 'Monday', true, true, true);

insert into classTimeList (class_name, day_of_week, TWO_TIME, THREE_TIME, FOUR_TIME)
values ('영상촬영실습', 'Monday', true, true, true);

insert into classTimeList (class_name, day_of_week, TWO_TIME, THREE_TIME, FOUR_TIME)
values ('영상제작개론및실습', 'Monday', true, true, true);

--     화요일
insert into classTimeList (class_name, day_of_week, ONE_TIME, TWO_TIME, THREE_TIME)
values ('스튜디오제작기초', 'Tuesday', true, true, true);

insert into classTimeList (class_name, day_of_week, ONE_TIME, TWO_TIME, THREE_TIME, FOUR_TIME, FIVE_TIME)
values ('다큐멘터리제작', 'Tuesday', true, true, true, true, true);

--     수요일
insert into classTimeList (class_name, day_of_week, ONE_TIME, TWO_TIME, THREE_TIME)
values ('촬영이론및실습', 'Wednesday', true, true, true);

--     목요일
insert into classTimeList (class_name, day_of_week, ONE_TIME, TWO_TIME, THREE_TIME, FOUR_TIME, FIVE_TIME, SIX_TIME)
values ('영화예술과', 'Thursday', true, true, true, true, true, true);

--     금요일
insert into classTimeList (class_name, day_of_week, ONE_TIME, TWO_TIME, THREE_TIME)
values ('방송음향', 'Friday', true, true, true);


-- 장비대여
insert into rental(CLASSTIMETABLE_ID, EQUIPMENT_ID) VALUES (1,1);
insert into rental(CLASSTIMETABLE_ID, EQUIPMENT_ID) VALUES (1,1);
