select emp_no, from_date, salary, avg(salary) over () as avg_salary
from salaries
where emp_no = 10001
limit 5;

select emp_no, from_date, salary, avg(salary) over () as avg_salary
from (select * from salaries where emp_no = 10001 limit 5) s2;

select e.*, dense_rank() over (order by e.hire_date) as hire_date_rank
from employees e;

select de.dept_no,
       e.emp_no,
       e.first_name,
       e.hire_date,
       rank() over (partition by de.dept_no order by e.hire_date) as hire_date_rank
from employees e
         inner join dept_emp de on de.emp_no = e.emp_no
order by de.dept_no, e.hire_date;

select emp_no, from_date, salary, avg(salary) over () as avg_aslary
from salaries
where emp_no = 10001;


select emp_no, from_date, salary, dense_rank() over (order by salary desc) as ranks
from salaries
where emp_no = 10001;

select emp_no,
       from_date,
       salary,
       min(salary) over (order by from_date range interval 1 year preceding)                                       as min_1,
       max(salary)
           over (order by from_date range between interval 1 year preceding and interval 2 year following)         as max_1,
       avg(salary) over (order by from_date rows unbounded preceding)                                              as avg_1,
       avg(salary)
           over (order by from_date rows between 1 preceding and 1 following)                                      as avg_2
from salaries
where emp_no = 10001;

select de.dept_no,
       e.emp_no,
       e.first_name,
       e.hire_date,
       rank() over (partition by de.dept_no order by e.hire_date) as hire_date_rank
from employees e
         join dept_emp de on de.emp_no = e.emp_no
where de.dept_no = 'd001'
order by de.dept_no, e.hire_date
limit 20;

select de.dept_no,
       e.emp_no,
       e.first_name,
       e.hire_date,
       dense_rank() over (partition by de.dept_no order by e.hire_date) as hire_date_rank
from employees e
         join dept_emp de on de.emp_no = e.emp_no
where de.dept_no = 'd001'
order by de.dept_no, e.hire_date
limit 20;

select de.dept_no,
       e.emp_no,
       e.first_name,
       e.hire_date,
       row_number() over (partition by de.dept_no order by e.hire_date) as hire_date_rank
from employees e
         join dept_emp de on de.emp_no = e.emp_no
where de.dept_no = 'd001'
order by de.dept_no, e.hire_date
limit 20;

select from_date, salary,
       lag(salary, 5) over(order by from_date) as prior_5th_value,
       lead(salary, 5) over(order by from_date) as next_5th_value,
       lag(salary, 5, -1) over(order by from_date) as prior_5th_default,
       lead(salary, 5, -1) over(order by from_date) as next_5th_default
from salaries
where emp_no = 10001;