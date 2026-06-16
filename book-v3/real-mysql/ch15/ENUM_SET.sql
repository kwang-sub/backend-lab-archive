create table tb_enum
(
    fd_enum ENUM ('PROCESSING', 'FAILURE', 'SUCCESS')
);


insert into tb_enum
values ('PROCESSING'),
       ('FAILURE');
select *
from tb_enum;
select fd_enum * 1
from tb_enum;

select *
from tb_enum
where fd_enum = 1;
select *
from tb_enum
where fd_enum = 'PROCESSING';


alter table tb_enum
    modify fd_enum enum ('PROCESSING', 'FAILURE', 'SUCCESS', 'REFUND');


alter table tb_enum
    modify fd_enum enum ('PROCESSING', 'FAILURE', 'REFUND', 'SUCCESS');

select fd_enum * 1, fd_enum
from tb_enum
order by fd_enum;

select fd_enum * 1, fd_enum
from tb_enum
order by cast(fd_enum as char);


create table tb_set
(
    fd_set set ('TENNIS', 'SOCCER', 'GOLF', 'TABLE-TENNIS', 'BASKETBALL', 'BILLIARD')
);

insert into tb_set(fd_set)
values ('SOCCER'),
       ('GOLF,TENNIS');
select *
from tb_set;
select *
from tb_set
where find_in_set('GOLF', fd_set);

SELECT *
from tb_set
where fd_set like '%GOLF%';

select *
from tb_set
where find_in_set('TENNIS', fd_set) >= 1;

