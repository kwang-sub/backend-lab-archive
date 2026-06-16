show databases;
show global variables like 'performance_schema';

select variable_name, variable_value
from performance_schema.global_variables
where variable_name like '%performance_schema%'
and variable_name not in ('performance_schema', 'performance_schema_show_processlist');

select * from sys.memory_global_total;

select *
from sys.schema_unused_indexes;