create sequence hibernate_sequence start 001 increment 1;

create table users (
    id uuid not null,
    avatar varchar(255),
    email varchar(255),
    name1 varchar(255),
    password varchar(255),
    role varchar(255),
    primary key (id)
    );