alter table users
    alter column email type varchar(200) using email::varchar(200);

alter table users
    alter column email set not null ;

