explain
select *
from employees e,
     dept_emp de
where e.emp_no = de.emp_no;


create table tb_test1
(
    user_id   int,
    user_type int,
    primary key (user_id)
);

create table tb_test2
(
    user_type char(1),
    type_desc varchar(10),
    primary key (user_type)
);

explain
select *
from tb_test1 tb1,
     tb_test2 tb2
where tb1.user_type = tb2.user_type;

explain
select *
from employees e
         left join dept_emp de on de.emp_no = e.emp_no
         left join departments d on d.dept_no = de.dept_no and d.dept_name = 'Development';

explain
select *
from employees e
         left join dept_emp de on de.emp_no = e.emp_no
         join departments d on d.dept_no = de.dept_no and d.dept_name = 'Development';


explain
select t.*
from employees t
         left join dept_manager mgr on mgr.emp_no = t.emp_no
where 1 = 1
  and mgr.dept_no = 'd001';


explain
select t.*
from employees t
         left join dept_manager mgr on mgr.emp_no = t.emp_no
where 1 = 1
  and mgr.emp_no is null;

explain
select e.*
from salaries s, employees e
where e.emp_no = s.emp_no
and s.emp_no between 10001 and 13000
group by s.emp_no
order by sum(s.salary) desc
limit 10;


explain
select e.*
from (
    select s.emp_no
    from salaries s
    where s.emp_no between 10001 and 13000
    group by s.emp_no
    order by SUM(s.salary) desc
     ) x, employees e
where e.emp_no = x.emp_no;

explain
select *
from employees e
         left join lateral (select *
                            from salaries s
                            where s.emp_no = e.emp_no
                            order by s.from_date desc
                            limit 2
    ) s2 on s2.emp_no = e.emp_no
where e.first_name = 'Matt';


explain
select e.emp_no, e.first_name, e.last_name, de.from_date
from dept_emp de, employees e
where de.from_date > '2001-10-01' and e.emp_no < 10005;