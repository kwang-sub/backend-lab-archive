alter table employees
    convert to character set UTF8MB4 collate utf8mb4_general_ci,
    algorithm = inplace,
    lock = none;

alter table employees
    engine = InnoDB,
    algorithm = inplace,
    lock = none;

rename table table1 to table2;
rename table db1.table1 to db2.table2;

rename table batch to batch_old, batch_new to batch;

show create table employees;
show table status like 'employees';
select * from information_schema.tables where table_schema = 'employees'
and table_name = 'employees';

create table if not exists temp_employees like employees;
