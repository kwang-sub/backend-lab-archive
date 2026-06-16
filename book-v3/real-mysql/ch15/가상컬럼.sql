create table tb_virtual_column
(
    id    int            not null auto_increment,
    price decimal(10, 2) not null default '0.00',
    quantity int not null default 1,
    total_price decimal(10, 2) as (quantity * price) virtual,
    primary key (id)
);

create table tb_stored_column(
    id    int            not null auto_increment,
    price decimal(10, 2) not null default '0.00',
    quantity int not null default 1,
    total_price decimal(10, 2) as (quantity * price) stored,
    primary key (id)
);

