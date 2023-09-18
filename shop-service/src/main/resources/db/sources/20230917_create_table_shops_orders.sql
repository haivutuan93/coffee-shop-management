create table if not exists shops
(
    id              serial
        primary key,
    name            varchar(255) not null,
    address         text,
    latitude        numeric,
    longitude       numeric,
    contact_details text,
    queue_quantity  integer default 1,
    queue_size      integer default 1,
    opening_time    time,
    closing_time    time,
    created_at      timestamp with time zone default CURRENT_TIMESTAMP,
    updated_at      timestamp with time zone default CURRENT_TIMESTAMP,
    created_by      varchar(50),
    updated_by      varchar(50)
);

alter table shops
    owner to postgres;

create table if not exists queues
(
    id         serial
        primary key,
    shop_id    integer
        references shops,
    current_quantity_orders   integer default 0,
    queue_size integer default 1
);

alter table queues
    owner to postgres;

create index if not exists idx_queues_shop_id
    on queues (shop_id);

create table if not exists products
(
    id            serial
        primary key,
    name          varchar(255) not null,
    description   text,
    default_price numeric default 0
);

alter table products
    owner to postgres;

create table if not exists shop_products
(
    id                serial
        primary key,
    shop_id           integer
        references shops,
    product_id        integer
        references products,
    price             numeric default 0,
    quantity_remaining numeric default 1,
    created_at        timestamp with time zone default CURRENT_TIMESTAMP,
    updated_at        timestamp with time zone default CURRENT_TIMESTAMP,
    created_by        varchar(50),
    updated_by        varchar(50)
);

alter table shop_products
    owner to postgres;

create index if not exists idx_shop_products_shop_id_product_id
    on shop_products (shop_id, product_id);

create table if not exists orders
(
    id                 serial
        primary key,
    customer_id        integer,
    shop_id            integer
        references shops,
    queue_id           integer
        references queues,
    total_price        numeric default 0,
    position_in_queue  integer default 0,
    expected_wait_time integer default 0,
    order_status       varchar(50)              default 'In Queue'::character varying,
    created_at         timestamp with time zone default CURRENT_TIMESTAMP,
    updated_at         timestamp with time zone default CURRENT_TIMESTAMP,
    created_by         varchar(50),
    updated_by         varchar(50)
);

alter table orders
    owner to postgres;

create index if not exists idx_orders_customer_id_shop_id
    on orders (customer_id, shop_id);

create index if not exists idx_orders_created_at
    on orders (created_at);

create table if not exists order_details
(
    id              serial
        primary key,
    order_id        integer
        references orders,
    shop_product_id integer
        references shop_products,
    quantity        integer default 0
);

alter table order_details
    owner to postgres;

create index if not exists idx_order_details_order_id
    on order_details (id);