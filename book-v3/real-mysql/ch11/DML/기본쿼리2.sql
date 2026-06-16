explain
select count(*)
from employees
where hire_date < '2011-07-23 11:10:12';

explain
select count(*)
from employees
where hire_date < UNIX_TIMESTAMP('2011-07-23 11:10:12');

explain
select count(t.emp_no)
from salaries t
where 1 = 1;

explain
select count(t.emp_no)
from salaries t
where 1 = 1
  and convert_tz(from_date, '+00:00', '+09:00') > '1991-01-01';

explain
select count(t.emp_no)
from salaries t
where 1 = 1
  and to_date < '1985-01-01';



select count(t.emp_no)
from salaries t
where 1 = 1
  and convert_tz(from_date, '+00:00', '+09:00') > '1991-01-01'
  and to_date < '1985-01-01';

select count(t.emp_no)
from salaries t
where 1 = 1
  and to_date < '1985-01-01'
  and convert_tz(from_date, '+00:00', '+09:00') > '1991-01-01';

explain
select t.*
from employees t
where 1 = 1
  and t.last_name = 'Aamodt'
  and t.first_name = 'Matt';

explain
select t.*
from employees t
where 1 = 1
  and t.last_name = 'Aamodt'
  and t.first_name = 'Matt'
  and month(birth_date) = 1;

explain
select t.*
from employees t
where 1 = 1
  and exists(select 1
             from salaries s
             where s.emp_no = t.emp_no
               and s.to_date > '1995-01-01'
             group by s.salary
             having count(*) > 1)
  and t.last_name = 'Aamodt'
;

flush status;
show status like 'Handler%';

explain
select t.*
from employees t
where 1 = 1
and t.emp_no between 10001 and 10010
order by t.first_name
limit 0, 5;


select * from employees limit 10;
select * from employees limit 100;

select table_schema, table_name, table_rows, (DATA_LENGTH + INDEX_LENGTH) / 1024/1024/1024 as table_size_gb
from information_schema.tables
where table_schema = 'real_mysql' and table_name='employees';
