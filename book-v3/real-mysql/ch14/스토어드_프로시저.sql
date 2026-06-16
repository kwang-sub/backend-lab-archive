delimiter ;;

drop procedure sp_sum;
create procedure sp_sum(IN param1 integer, in param2 integer, out param3 integer)
begin
    set param3 = param1 + param2;
end;;

delimiter ;

alter procedure sp_sum sql security definer;

set @result:=0;
select @result;

call sp_sum(1, 2, @result);
set @param1:=1;
set @param2:=2;
call sp_sum(@param1,@param2, @result);


create procedure sp_selectEmployees (in in_empno integer)
begin
    select * from employees where emp_no = in_empno;
end;

call sp_selectEmployees(10001);

create procedure sp_sum(IN param1 integer, in param2 integer, out param3 integer)
begin
    select '> Stored procedure started.' as debug_messaage;
    select concat('> param1 ', param1) as debug_messaage;
    select concat('> param2 ', param2) as debug_messaage;
    set param3 = param1 + param2;
    select '> Stored procedure completed.' as debug_messaage;
end;;

select routine_schema, routine_name, routine_type
from information_schema.ROUTINES
where routine_schema='test';


