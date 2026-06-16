select *
from departments
where dept_no = 'd001';

select *
from departments
where dept_no = "d001";

select *
from departments
where dept_no = 'd''001';

create table tab_test
(
    `table` varchar(20) not null
);
insert into tab_test value ('''sdf');

explain
select t.*
from employee_name t
where 1 = 1
  and t.emp_no > '0';

explain
select t.*
from dept_emp t
where 1 = 1
  and t.from_date = '1993-08-03';

create table tb_boolean
(
    bool_value BOOLEAN
);
insert into tb_boolean
values (false),
       (1),
       (2);
explain
select t.*
from tb_boolean t
where 1 = 1
  and t.bool_value is true;

select 1 = 1, null = null, 1 = null;
select 1 <=> 1, null <=> null, 1 <=> null;
select !1;
select ! true;
select NOT true;
select NOT false;
select NOT (1 = 1);

select 29 / 9;
select 29 div 9;
select mod(29, 9);
select 29 % 9;

select 'abc' REGEXP '^[x-z]]';
select 'baa' like '__a';

explain
select count(t.emp_no)
from employees t
where 1 = 1
  and t.first_name like 'Christ%';

explain
select count(t.emp_no)
from employees t
where 1 = 1
  and t.first_name like '%rist';

explain
select *
from dept_emp
where emp_no = 10001
  and dept_no = 'd005';

explain
select *
from dept_emp
where dept_no between 'd003' and 'd005'
  and emp_no = 10001;

explain
select *
from dept_emp
where dept_no in ('d003', 'd004', 'd005')
  and emp_no = 10001;

explain
select *
from dept_emp
where (dept_no, emp_no) in (('d001', 10017), ('d002', 10144));

explain
select t.emp_no, t.salary, t.from_date, t.to_date
from salaries t
where 1 = 1
  and t.emp_no = 10001
  and t.from_date > now();

explain
select t.emp_no, t.salary, t.from_date, t.to_date
from salaries t
where 1 = 1
  and t.emp_no = 10001
  and t.from_date > sysdate();

select date_format(now(), '%Y-%m-%d %H');
select date_add(now(), interval 1 day);
select date_add(now(), interval -1 day);

select group_concat(dept_no)
from departments;
select group_concat(dept_no separator '|')
from departments;

select group_concat(dept_no order by emp_no desc)
from dept_emp
where emp_no between 100001 and 100003;

select emp_no,
       first_name,
       case gender
           when 'M' then 'Man'
           when 'F' then 'Woman'
           else 'Unknown' end as gender
from employees;

select de.dept_no,
       e.first_name,
       e.gender,
       case
           when e.gender = 'F' then
               (select s.salary from salaries s where s.emp_no = e.emp_no order by s.from_date desc limit 1)
           else 0 end as last_salary
from dept_emp de,
     employees e
where e.emp_no = de.emp_no
  and de.dept_no = 'd001';

select md5('abc');
select sha('abc');

create table tab_binary
(
    col_md5      binary(16),
    col_sha      binary(20),
    col_sha2_256 binary(32)
);
insert into tab_binary
values (unhex(md5('abc')), unhex(sha('abc')), unhex(sha2('abc', 256)));
select *
from tab_binary;
select hex(col_md5), hex(col_sha), hex(col_sha2_256)
from tab_binary;

create table tb_accesslog
(
    access_id   bigint primary key not null auto_increment,
    access_url  varchar(1000)      not null,
    access_dttm datetime           not null,
    index ix_accessurl ((md5(access_url)))
);

insert into tb_accesslog
values (1, 'http://matt.com', now());

explain
select t.*
from tb_accesslog t
where 1 = 1
  and md5(t.access_url) = md5('http://matt.com');

explain
select t.*
from tb_accesslog t
where 1 = 1
  and t.access_url = 'http://matt.com';

explain
select sleep(1.5)
from employees t
where 1 = 1
  and t.emp_no between 10001 and 10010;

select benchmark(10000000, md5('abcdefghijk'));
select benchmark(10000000, (select count(*) from salaries s));

select hex(inet6_aton('fdfe::5a55:caff:fefa:9089'));
select hex(inet6_aton('10.0.5.9'));
select inet6_ntoa(unhex('FDFE0000000000005A55CAFFFEFA9089'));

explain
select json_pretty(t.doc)
from employee_docs t
where 1 = 1
  and t.emp_no = 10005;

explain
select t.emp_no, JSON_STORAGE_SIZE(t.doc)
from employee_docs t
where 1 = 1
limit 2;

explain
select t.emp_no, json_extract(t.doc, "$.first_name")
from employee_docs t
where 1 = 1;

select t.emp_no, JSON_UNQUOTE(json_extract(t.doc, "$.first_name"))
from employee_docs t
where 1 = 1;

select t.emp_no, t.doc -> "$.first_name"
from employee_docs t
where 1 = 1;

select t.emp_no, t.doc ->> "$.first_name"
from employee_docs t
where 1 = 1;

explain
select t.*
from employee_docs t
where 1 = 1
  and JSON_CONTAINS(t.doc, '"Christian"', '$.first_name');

select json_object(
               "empNo", emp_no,
               "salary", salary
       )
from salaries
limit 3;

explain
select t.dept_no, JSON_OBJECTAGG(t.emp_no, t.from_date)
from dept_manager t
where 1 = 1
  and dept_no in ('d001', 'd002')
group by t.dept_no;


select e2.emp_no, e2.first_name, e2.gender
from employee_docs e1,
     JSON_TABLE(doc, "$" columns (
         emp_no int path "$.emp_no",
         gender char(1) path "$.gender",
         first_name varchar(20) path "$.first_name"
         )
     ) as e2
where e1.emp_no in (10001, 10002);
