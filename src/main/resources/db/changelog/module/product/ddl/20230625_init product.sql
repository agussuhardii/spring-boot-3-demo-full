create schema if not exists product;

create table product.category
(
    id          char(36)     not null,
    name        varchar(100) not null,
    text        text,
    icon        varchar(255),

    created_by  varchar(50),
    modified_by varchar(50),
    created_at  numeric,
    updated_at  numeric,
    is_deleted  boolean      not null default false,
    primary key (id)
);

create table product.product
(
    id          char(36)     not null,
    name        varchar(100) not null,
    text        text,
    image       varchar(255),
    category_id char(36)     not null,
    qty         numeric      not null,

    created_by  varchar(50),
    modified_by varchar(50),
    created_at  numeric,
    updated_at  numeric,
    is_deleted  boolean      not null default false,
    primary key (id),
    foreign key (category_id) references product.category (id)
);
