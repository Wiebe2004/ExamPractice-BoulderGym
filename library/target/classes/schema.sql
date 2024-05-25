drop table if exists users;
drop table if exists subscriptions;
drop table if exists TENTIMESPASS;

create table users (
    id varchar(8) primary key,
    first_name varchar(255) not null,
    name varchar(255) not null,
    birth_date date not null,
    age int,
    email varchar(255) not null,
    is_student boolean not null
);

create table SUBSCRIPTION (
    id bigint auto_increment primary key,
    start_date date not null,
    end_date date,
    type varchar(255) not null,
    is_active varchar(255),
    user_id varchar(8),
    foreign key (user_id) references users(id) on delete cascade
);

create table TENTIMESPASS (
    id bigint auto_increment primary key,
    start_date date not null,
    end_date date,
    is_active varchar(255),
    ENTRIES int,
    user_id varchar(8),
    foreign key (user_id) references users(id) on delete cascade
);
