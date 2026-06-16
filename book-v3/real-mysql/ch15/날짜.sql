select now(6);

create table tb_datetime(current datetime(6));

insert into tb_datetime values (now());
select * from tb_datetime;

insert into tb_datetime values(now(6));
select * from tb_datetime;

create table tb_timezone(fd_datetime datetime, fd_timestamp timestamp);
SET time_zone = 'system';

insert into tb_timezone values (now(), now());

select * from tb_timezone;

show variables like '%time_zone%';

create table tb_autoupdate(
    id bigint not null auto_increment,
    title varchar(20),
    create_at_ts timestamp default current_timestamp,
    updated_at_ts timestamp default current_timestamp on update current_timestamp,
    create_at_dt datetime default current_timestamp,
    updated_at_dt datetime default current_timestamp on update current_timestamp,
    primary key (id)
);

insert into tb_autoupdate(id, title)
values (null, 'Initial data');
select * from tb_autoupdate;

update tb_autoupdate set title = 'Changed data' where id = 1;
select * from tb_autoupdate;