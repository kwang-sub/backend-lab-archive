explain
with cte1 as (select * from departments)
select *
from cte1;

explain
select cte1.*
from (select * from departments) cte1;

explain
with cte1 as (select emp_no, min(from_date) from salaries group by emp_no)
select *
from employees e
         join cte1 t1 on t1.emp_no = e.emp_no
         join cte1 t2 on t2.emp_no = e.emp_no;

with recursive cte(no) as (select 1 union all select (no + 1) from cte where no < 5)
select *
from cte;

with cte1 as (select * from departments)
select *
from cte1;

with cte1 (fd1, fd2, fd3) as (select * from departments)
select *
from cte1;

create table test_employees
(
    id         int primary key not NULL,
    name       varchar(100)    not null,
    manager_id int             null,
    index (manager_id),
    foreign key (manager_id) references test_employees (id)
);

insert into test_employees
values (333, "Yasmina", null),
       (198, "John", 333),
       (692, "Tarek", 333),
       (29, "Pedro", 198),
       (4610, "Sarah", 29),
       (72, "Pierre", 29),
       (123, "Adil", 692);

with recursive managers as (select *, 1 as lv
                            from test_employees
                            where id = 123
                            union all
                            select e.*, lv + 1
                            from managers m
                                     inner join test_employees e on e.id = m.manager_id and m.manager_id is not null)
select *
from managers
order by lv desc;

with recursive managers as (select *, cast(id as char(100)) as manager_path, 1 as lv
                            from test_employees
                            where manager_id is null
                            union all
                            select e.*,
                                   concat(e.id, '->', m.manager_path) as manager_path,
                                   lv + 1
                            from managers m
                                     inner join test_employees e on e.manager_id = m.id)
select *
from managers
order by lv asc;