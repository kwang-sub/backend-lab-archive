select emp_no, (select dept_name from departments where dept_name = "Sales1")
from dept_emp
limit 10;

select emp_no, (select dept_name from departments)
from dept_emp
limit 10;

select emp_no, (select dept_no, dept_name from departments where dept_name = "Sales1")
from dept_emp
limit 10;

select count(concat(e1.first_name, (select e2.first_name from employees e2 where e2.emp_no = e1.emp_no)))
from employees e1;

select count(concat(e1.first_name, e2.first_name))
from employees e1,
     employees e2
where e1.emp_no = e2.emp_no;

select e.emp_no, e.first_name, s2.salary, s2.from_date, s2.to_date
from employees e
         join lateral (select * from salaries s where s.emp_no = e.emp_no order by s.from_date desc limit 1) s2
              on s2.emp_no = e.emp_no
where e.emp_no = 499999;

explain
select *
from (select * from employees) y;

explain ANALYZE
select *
from dept_emp de
where de.emp_no = (select e.emp_no
                   from employees e
                   where e.first_name = 'Georgi'
                     and e.last_name = 'Facello'
                   limit 1);

explain
select *
from dept_emp de
where (emp_no, from_date) = (select salaries.emp_no, salaries.from_date
                             from salaries
                             where de.emp_no = 100001
                             limit 1);

explain
select *
from employees e
where e.emp_no in (select de.emp_no from dept_emp de where de.from_date = '1995-01-01');
