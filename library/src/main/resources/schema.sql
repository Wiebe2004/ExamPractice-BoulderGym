-- Drop tables in reverse order of creation to avoid foreign key constraints
drop table if exists TENTIMESPASS;
drop table if exists SUBSCRIPTION;
drop table if exists users;

-- Create tables
create table users (
    id bigint auto_increment primary key,
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
    user_id bigint,
    foreign key (user_id) references users(id) on delete cascade
);

create table TENTIMESPASS (
    id bigint auto_increment primary key,
    start_date date not null,
    end_date date,
    is_active varchar(255),
    ENTRIES int,
    user_id bigint,
    foreign key (user_id) references users(id) on delete cascade
);
