create table primary_category
(
    id            bigint auto_increment,
    category_name varchar(255) not null unique,

    primary key (id)
) comment '물품 상위 카테고리 테이블';

create table secondary_category
(
    id                  bigint auto_increment,
    category_name       varchar(255) not null unique,
    primary_category_id bigint       not null,

    primary key (id),
    foreign key (primary_category_id) references primary_category (id)
) comment '물품 하위 카테고리 테이블';

create table equipment
(
    id                    bigint auto_increment,
    name                  varchar(40)  not null unique,
    secondary_category_id bigint       not null,
    main_img              varchar(100) null,
    content               text         null,
    count                 integer      not null,

    foreign key (secondary_category_id) references secondary_category (id),
    primary key (id)
) comment '물품 테이블';

create table class_period
(
    id          bigint auto_increment,
    class_name  varchar(255)                                              not null,
    day_of_week enum ('monday','tuesday','wednesday','thursday','friday') not null,
    one_time    boolean default false,
    two_time    boolean default false,
    three_time  boolean default false,
    four_time   boolean default false,
    five_time   boolean default false,
    six_time    boolean default false,
    seven_time  boolean default false,
    eight_time  boolean default false,
    nine_time   boolean default false,
    ten_time    boolean default false,

    primary key (id)
) comment '수업명, 1~10 교시 테이블';


create table rental
(
    id              bigint auto_increment comment '대여 id',
    class_period_id bigint not null comment '수업 시간 테이블의 외래키',
    equipment_id    bigint not null comment '물품 테이블의 외래키',
    rental_chk      boolean default true comment '대여 가능 여부',
    rental_cnt      int     default 0 comment '대여한 수량',

    foreign key (class_period_id) references class_period (id),
    foreign key (equipment_id) references equipment (id),
    primary key (id)
) comment '수업 시간에 대여한 물품';

# rental 테이블에서 오늘이 월요일이면 월요일에 대한 대여목록 가져오기
# 뷰로 만들어도 되지만 혹시 학생이 볼수있게 하게 되면 수량를 가릴수 있어야함
create table today_rental
(
    id                 bigint auto_increment comment 'today table에 id',
    rental_id          bigint not null comment 'rental table의 외래키',
    rental_minus_count int    not null comment '대여한 수량',

    foreign key (rental_id) references rental (id),
    primary key (id)
) COMMENT '오늘 대여 가능한 물품';

create table notification_product
(
    id      bigint auto_increment comment '공지 개인키 ID',
    img     varchar(40)  not null comment '이미지 경로',
    subject varchar(100) not null comment '제목',
    content varchar(100) comment '내용',

    primary key (id)
) comment '신상품_공지사항';

create table notification_content
(
    id      bigint auto_increment comment '유의사항 개인키',
    content text not null,

    primary key (id)
) comment '유의사항';

create table login_user
(
    id      bigint auto_increment comment '사용자 고유번호',
    userId  varchar(20) comment '사용자 ID',
    userPwd varchar(30) comment '사용자 PASSWD',
    role    enum ('loginUser','admin') default 'loginUser',

    primary key (id)
);

create table login_user_connect_log
(
    id           bigint auto_increment comment '접속자 고유번호',
    user_key     bigint comment '사용자 고유번호 참조키' not null,
    connect_date date comment '접속 날짜',

    primary key (id),
    foreign key (id) references login_user (id)
)

