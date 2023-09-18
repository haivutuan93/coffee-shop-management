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

create table if not exists role_permission_restrict
(
    id                   serial
        primary key,
    role                 varchar(255),
    permissions_restrict text[]
);

alter table role_permission_restrict
    owner to postgres;

