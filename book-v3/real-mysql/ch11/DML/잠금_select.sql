select * from employees where emp_no = 10001 for share;
select * from employees where emp_no = 10001 for update;

select *
from employees e
join dept_emp de on de.emp_no = e.emp_no
join departments d on d.dept_no = de.dept_no
for update ;

explain
select *
from employees e
inner join dept_emp de on de.emp_no = e.emp_no
inner join departments d on d.dept_no = de.dept_no
where e.emp_no = 10001
for update of e;

select *
from employees
where emp_no = 10001
for update nowait;

select * from salaries where emp_no = 10001 for update skip locked;




