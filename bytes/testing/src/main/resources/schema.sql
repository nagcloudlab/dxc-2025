

create table if not exists user_accounts (
    username varchar not null,
    password varchar not null,
    primary key (username)
);