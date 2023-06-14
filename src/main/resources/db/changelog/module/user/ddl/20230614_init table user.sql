create table users
(
    id           char(36)     not null
        primary key,
    full_name    varchar(100) not null,
    place_birth  varchar(100),
    date_birth   date,
    gender       varchar(10),
    phone_number varchar(20),
    email        varchar(20),
    full_address varchar(255),

    created_by   varchar(50),
    modified_by  varchar(50),
    created_at   numeric,
    updated_at   numeric,
    is_deleted   boolean      not null default false
);
