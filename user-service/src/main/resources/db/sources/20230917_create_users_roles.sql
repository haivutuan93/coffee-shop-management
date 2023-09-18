create table if not exists users
(
    id              serial
    primary key,
    username        varchar(255) not null,
    password        varchar(255) not null,
    email           varchar(255),
    mobile_number   varchar(20),
    regular_address text,
    total_score     numeric,
    created_at      timestamp with time zone default CURRENT_TIMESTAMP,
    updated_at      timestamp with time zone default CURRENT_TIMESTAMP,
                                  created_by      varchar(50),
    updated_by      varchar(50)
    );

alter table users
    owner to postgres;

create index if not exists idx_users_mobile_number
    on users (mobile_number);

create table if not exists roles
(
    id        serial
    primary key,
    role_name varchar(50) not null
    unique
    );

alter table roles
    owner to postgres;

create index if not exists idx_role_name
    on roles (role_name);

create table if not exists user_roles
(
    user_id integer not null
    references users,
    role_id integer not null
    references roles,
    shop_id integer,
    primary key (user_id, role_id)
    );

alter table user_roles
    owner to postgres;

create index if not exists idx_userroles_user_id
    on user_roles (user_id);

create index if not exists idx_userroles_role_id
    on user_roles (role_id);


create table if not exists user_devices
(
    id           serial
    primary key,
    user_id      integer
    references users,
    device_token text not null,
    platform     varchar(20),
    created_at   timestamp with time zone default CURRENT_TIMESTAMP,
    updated_at   timestamp with time zone default CURRENT_TIMESTAMP,
                               created_by   varchar(50),
    updated_by   varchar(50)
    );

alter table user_devices
    owner to postgres;

create index if not exists idx_device_user_id
    on user_devices (user_id);

create index if not exists idx_device_created_at
    on user_devices (created_at);