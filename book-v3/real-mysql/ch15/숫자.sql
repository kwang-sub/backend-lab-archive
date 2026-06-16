create table tb_float (fd1 float);
insert into tb_float values (0.1);
select * from tb_float where fd1 = 0.1;


create table tb_autoinc_innodb(
    fd_pk1 int not null default '0',
    fd_pk2 int not null auto_increment,
    primary key (fd_pk1, fd_pk2)
) engine = innodb;

create table tb_autoinc_innodb(
    fd_pk1 int not null default '0',
    fd_pk2 int not null auto_increment,
    primary key (fd_pk1, fd_pk2),
    unique key ux_fdpk2(fd_pk2)
) engine = innodb;

insert into tb_autoinc_innodb(fd_pk1)
values (1);

show create table tb_autoinc_innodb;
