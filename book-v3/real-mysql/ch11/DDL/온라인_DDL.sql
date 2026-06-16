alter table salaries
    change to_date end_date date not null,
    algorithm = inplace,
    lock = none;

show create table employees;
desc employees;