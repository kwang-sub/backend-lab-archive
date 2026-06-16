select first_name, last_name
from employees
order by last_name;

select first_name, last_name
from employees
order by 2;

-- 정렬 무시된다.
select first_name, last_name
from employees
order by "last_name";

alter table salaries
    add index ix_salary_fromdate (salary desc, from_date asc);

explain
select t.*
from salaries t
where 1 = 1
order by t.salary desc
limit 10;

select *
from salaries
order by cos(salary);