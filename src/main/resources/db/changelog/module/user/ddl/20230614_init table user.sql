create schema if not exists user_;

create table user_.user_
(
    id           char(36)     not null
        primary key,
    username     varchar(100) not null,
    password     varchar(255) not null,
    full_name    varchar(100) not null,
    place_birth  varchar(100),
    date_birth   date,
    gender       varchar(10),
    phone_number varchar(20),
    email        varchar(200) not null,
    full_address varchar(255),

    created_by   varchar(50),
    modified_by  varchar(50),
    created_at   numeric,
    updated_at   numeric,
    is_deleted   boolean      not null default false
);

create table user_.user_role
(
    user_id char(36)    not null,
    roles   varchar(20) not null,
    foreign key (user_id) references user_.user_ (id),
    primary key (user_id, roles)
);

