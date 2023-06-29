create schema if not exists cart;

create table cart.cart
(
    id          char(36) not null,
    user_id     char(36) not null,

    created_by  varchar(50),
    modified_by varchar(50),
    created_at  numeric,
    updated_at  numeric,
    is_deleted  boolean  not null default false,
    primary key (id)
);

create table cart.cart_item
(
    id          char(36) not null,
    cart_id     char(36) not null,
    product_id  char(36) not null,
    qty         numeric  not null,

    created_by  varchar(50),
    modified_by varchar(50),
    created_at  numeric,
    updated_at  numeric,
    is_deleted  boolean  not null default false,
    primary key (id),
    foreign key (cart_id) references cart.cart (id)
);
