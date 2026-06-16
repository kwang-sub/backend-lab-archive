select dept_no, count(*)
from dept_emp
group by dept_no
with rollup;

select first_name, last_name, count(*)
from employees
group by first_name, last_name
with rollup;

select if(grouping(first_name), 'ALL first_name', first_name) as first_name,
       if(grouping(last_name), 'ALL last_name', last_name)    as last_name,
       count(*)
from employees
group by first_name, last_name
with rollup;

select dept_no, count(*) as emp_count
from dept_emp
group by dept_no;

select
    sum(case when dept_no = 'd001' then emp_count else 0 end) as count_d001,
    sum(case when dept_no = 'd002' then emp_count else 0 end) as count_d002,
    sum(case when dept_no = 'd003' then emp_count else 0 end) as count_d003,
    sum(case when dept_no = 'd004' then emp_count else 0 end) as count_d004,
    sum(case when dept_no = 'd005' then emp_count else 0 end) as count_d005,
    sum(case when dept_no = 'd006' then emp_count else 0 end) as count_d006,
    sum(case when dept_no = 'd007' then emp_count else 0 end) as count_d007,
    sum(case when dept_no = 'd008' then emp_count else 0 end) as count_d008,
    sum(case when dept_no = 'd009' then emp_count else 0 end) as count_d009,
    sum(emp_count) as count_total
from (select dept_no, count(*) as emp_count from dept_emp group by dept_no) tb_derived;

explain
select
    sum(case when e.hire_date between '1980-01-01' and '1989-12-31' then 1 else 0 end) as cnt_1980,
    sum(case when e.hire_date between '1990-01-01' and '1999-12-31' then 1 else 0 end) as cnt_1980,
    sum(case when e.hire_date between '2000-01-01' and '2009-12-31' then 1 else 0 end) as cnt_1980
from dept_emp de, employees e
where e.emp_no = de.emp_no
group by de.dept_no;

