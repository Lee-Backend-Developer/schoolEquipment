create table category
(
    category_id bigint auto_increment,
    category_name         varchar(255) not null unique,

    primary key (category_id)
);

create table equipment
(
    equipment_id      bigint auto_increment,
    name              varchar(40)  not null unique,
    category_id bigint       not null,
    main_img          varchar(100) null,
    content           text null,
    count             integer      not null,

    foreign key (category_id) references category (category_id),
    primary key (equipment_id)
);

create table classes
(
    classtimelist_id bigint auto_increment,
    class_name         varchar(255)                                              not null,
    day_of_week        enum ('monday','tuesday','wednesday','thursday','friday') not null,
    one_time           boolean default false,
    two_time           boolean default false,
    three_time         boolean default false,
    four_time          boolean default false,
    five_time          boolean default false,
    six_time           boolean default false,
    seven_time         boolean default false,
    eight_time         boolean default false,
    nine_time          boolean default false,
    ten_time           boolean default false,

    primary key (classtimelist_id)
);


create table rental
(
    rental_id          bigint auto_increment,
    classtimelist_id bigint not null,
    equipment_id       bigint not null,
    rental_chk         boolean default true,
    rental_cnt         int     default 0,

    foreign key (classtimelist_id) references classes (classtimelist_id),
    foreign key (equipment_id) references equipment (equipment_id),
    primary key (rental_id)
);
