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

create table subscriptions(
    id bigint auto_increment primary key,
    type varchar(255),
    startDate date,
    endDate date,
    user_id bigint,
    foreign key (user_id) references users(id)
);
