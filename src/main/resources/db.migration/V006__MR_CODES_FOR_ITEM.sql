create table mr_codes_for_item (
    uuid uuid not null,
    promocodes varchar(255),
    shop_uuid uuid,
    primary key (uuid)
);
