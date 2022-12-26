create table mr_shop (
    uuid uuid not null,
    image varchar(255),
    item_name varchar(255),
    description varchar(255),
    used_count int,
    total_count int,
    page_url varchar(255),
    price int,
    primary key (uuid)
);
