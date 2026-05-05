CREATE TABLE IF NOT EXISTS customer (
    phone_number bigint primary key,
    username varchar(50) not null,
    password varchar(50) not null,
    email varchar(100) unique not null,
    plan_id varchar(50) not null
);