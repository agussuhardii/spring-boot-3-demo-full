create table user_role
(
    user_id char(36)    not null,
    role    varchar(20) not null,
    primary key (user_id, role)
);
