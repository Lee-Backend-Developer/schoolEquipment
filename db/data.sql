-- 카테고리 추가
insert into equipment_category(category_name)
values('카메라');

-- 장비 추가
insert into equipment (name, count, equipment_category)
values ('pmw-200', 30, 1);
insert into equipment (name, count, equipment_category)
values ('ace tripod', 10, 1);

-- 수업시간 추가
--      월요일
insert into class_time_list (class_name, day_of_week, TWO_TIME, THREE_TIME, FOUR_TIME)
values ('배우연기연출', 'Monday', true, true, true);

insert into class_time_list (class_name, day_of_week, TWO_TIME, THREE_TIME, FOUR_TIME)
values ('영상촬영실습', 'Monday', true, true, true);

insert into class_time_list (class_name, day_of_week, TWO_TIME, THREE_TIME, FOUR_TIME)
values ('영상제작개론및실습', 'Monday', true, true, true);

--     화요일
insert into class_time_list (class_name, day_of_week, ONE_TIME, TWO_TIME, THREE_TIME)
values ('스튜디오제작기초', 'Tuesday', true, true, true);

insert into class_time_list (class_name, day_of_week, ONE_TIME, TWO_TIME, THREE_TIME, FOUR_TIME, FIVE_TIME)
values ('다큐멘터리제작', 'Tuesday', true, true, true, true, true);

--     수요일
insert into class_time_list (class_name, day_of_week, ONE_TIME, TWO_TIME, THREE_TIME)
values ('촬영이론및실습', 'Wednesday', true, true, true);

--     목요일
insert into class_time_list (class_name, day_of_week, ONE_TIME, TWO_TIME, THREE_TIME, FOUR_TIME, FIVE_TIME, SIX_TIME)
values ('영화예술과', 'Thursday', true, true, true, true, true, true);

--     금요일
insert into class_time_list (class_name, day_of_week, ONE_TIME, TWO_TIME, THREE_TIME)
values ('방송음향', 'Friday', true, true, true);


-- 장비대여
select * from rental;
insert into rental(class_time_list_id, equipment_id, rental_cnt)
VALUES (1, 1, 3);
insert into rental(class_time_list_id, equipment_id, rental_cnt)
VALUES (1, 1, 2);
