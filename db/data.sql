-- 수업시간 추가
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('배우연기연출', 'monday', 0, 1, 1, 1, 0, 0, 0, 0, 0, 0);
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('영상제작개론및실습', 'monday', 0, 1, 1, 1, 0, 0, 0, 0, 0, 0);
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('환경음향', 'monday', 0, 0, 1, 1, 0, 0, 0, 0, 0, 0);
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('스튜디오제작기초', 'tuesday', 1, 1, 1, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('다큐멘터리제작', 'tuesday', 1, 1, 1, 1, 1, 1, 0, 0, 0, 0);
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('촬영이론및실습', 'wednesday', 1, 1, 1, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('영상제작개론및실습', 'wednesday', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('숏폼콘텐츠제작/촬영', 'thursday', 1, 1, 1, 1, 1, 1, 0, 0, 0, 0);
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('카메라연기중급1', 'thursday', 1, 1, 1, 1, 1, 1, 0, 0, 0, 0);
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('방송음향', 'friday', 1, 1, 1, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO dima.class_period (class_name, day_of_week, one_time, two_time, three_time, four_time, five_time, six_time, seven_time, eight_time, nine_time, ten_time) VALUES ('사진예술', 'friday', 0, 1, 1, 1, 0, 0, 0, 0, 0, 0);

-- 장비대여
insert into rental(class_period_id, equipment_id, rental_cnt) VALUES (1,1,4);
insert into rental(class_period_id, equipment_id, rental_cnt) VALUES (1,1,4);

select * from equipment;

select * from class_period;
