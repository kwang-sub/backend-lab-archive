insert ignore into salaries (emp_no, salary, from_date, to_date) values
(10001, 60117, '1986-06-26', '1987-06-26');

insert ignore into salaries
values (null, null, null, null);

select * from salaries where emp_no = 0;

insert ignore into salaries
values (0, 10, 0000-00-00, null) on duplicate key update salary = 0;