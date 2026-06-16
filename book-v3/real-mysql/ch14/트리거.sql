create trigger on_delete before delete on employees
    for each row
    begin
        delete from salaries where salaries.emp_no = OLD.emp_no;
    end;;

show global variables like 'event_scheduler';

show processlist ;
