create schema if not exists order_;

create table order_.order_
(
    id           char(36)    not null,
    user_        char(36)    not null,
    items        jsonb       not null,
    total_price  numeric     not null,
    payment_type varchar(20) not null,
    payment      jsonb       not null,

    created_by   varchar(50),
    modified_by  varchar(50),
    created_at   numeric,
    updated_at   numeric,
    is_deleted   boolean     not null default false,
    primary key (id)
);