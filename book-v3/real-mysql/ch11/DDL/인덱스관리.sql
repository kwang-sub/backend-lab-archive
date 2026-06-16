alter table employees
    add primary key (emp_no) algorithm = inplace,
    lock = none;
alter table employees
    add unique index ux_empno (emp_no),
    algorithm = inplace,
    lock = none;

show index from employees;

show create table employees;
show table status like 'employees';


alter table salaries rename index ix_salary to ix_salary2;
show index from salaries;

alter table employees
    add index index_new (first_name, last_name);
alter table employees
    drop index ix_firstname, rename index index_new to ix_firstname,
    algorithm = inplace,
    lock = none;

alter table employees
    alter index ix_firstname invisible;
alter table employees
    alter index ix_firstname visible;

explain
select *
from employees
where first_name = 'Matt';

show create table employees;

alter table employees
    add index ix_lastname (last_name, first_name),
    add index ix_birthdate (birth_date),
    algorithm = inplace,
    lock = none;
