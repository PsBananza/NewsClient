create table task (
    id int8 not null,
    description varchar(255),
    image varchar(255),
    title varchar(255),
    username varchar(255),
    user_id uuid not null,
    primary key (id)
    );