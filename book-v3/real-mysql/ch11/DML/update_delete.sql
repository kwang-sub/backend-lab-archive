create table tb_test1
(
    emp_no     int,
    first_name varchar(14),
    primary key (emp_no)
);

insert into tb_test1
values (10001, null),
       (10002, null),
       (10003, null),
       (10004, null);

update tb_test1 t1, employees e
set t1.first_name = e.first_name
where e.emp_no = t1.emp_no;

select *
from tb_test1;

# update departments d, dept_emp de
# set d.emp_count = count(*)
# where de.dept_no = d.dept_no
# group by de.dept_no;

update departments d
set d.emp_count = (select count(*)
                   from dept_emp de
                   where de.dept_no = d.dept_no
                   group by de.dept_no);

create table user_level
(
    user_id   bigint   not null,
    user_lv   int      not null,
    create_at datetime not null,
    primary key (user_id)
);

insert user_level
values
    (1, 1, now()),
    (2, 2, now()),
    (3, 3, now());


update user_level ul
    inner join (values row (1, 1), row (2, 4)) new_user_level(user_id, user_lv)
    on new_user_level.user_id = ul.user_id
set ul.user_lv = ul.user_lv + new_user_level.user_lv;

select *
from user_level;

