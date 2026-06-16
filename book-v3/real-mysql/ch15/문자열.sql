show character set;

select emp_no, first_name from employees where first_name = 'Matt';
select emp_no, first_name from employees where first_name = _latin1'Matt';

set character_set_client = 'utf8mb4';
set character_set_results = 'utf8mb4';
set character_set_connection = 'utf8mb4';

set names utf8mb4;
show collation;
