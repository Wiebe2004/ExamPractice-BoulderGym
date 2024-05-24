drop table if exists users;
drop table if exists subscriptions;

create table users(
    id bigint auto_increment primary key,
    first_name varchar(255) not null,
    name varchar(255) not null,
    birth_date date not null,
    age int,
    email varchar(255) not null,
    is_student boolean not null
);

create table SUBSCRIPTION(
    id bigint auto_increment primary key,
    start_date date not null,
    end_date date,
    type varchar(255) not null,
    is_active boolean,
    user_id bigint,
    foreign key (user_id) references users(id) on delete cascade
);
