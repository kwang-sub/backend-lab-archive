explain
select s.emp_no, count(distinct e.first_name) as cnt
from salaries s
         join real_mysql.employees e on s.emp_no = e.emp_no
where s.emp_no in (100001, 100002)
group by s.emp_no
having avg(s.salary) > 1000
order by avg(s.salary)
limit 10;


explain
select t.*
from salaries t
where 1 = 1
  and t.salary * 10 > 150000;

explain
select t.*
from salaries t
where 1 = 1
  and t.salary > 150000 / 10;

select null = null;
select null <=> null;

explain
select t.*
from titles t
where 1 = 1
and t.to_date is null;

select count(*)
from employees
where hire_date > str_to_date('2011-07-23', '%Y-%m-%d');

select count(*)
from employees
where hire_date > '2011-07-23';

select count(*)
from employees
where date_format(hire_date, '%Y-%m-%d') > '2011-07-23';

select count(*)
from employees
where date_add(hire_date, interval 1 year) > '2011-07-23';

select count(*)
from employees
where hire_date > date_sub('2011-07-23', interval 1 year);

select count(*)
from employees
where hire_date > date(now());

select STR_TO_DATE('2011-06-30', '%Y-%m-%d') < str_to_date('2011-06-30 00:00:01','%Y-%m-%d %H:%i:%s');