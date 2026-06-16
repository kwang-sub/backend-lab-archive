create table tb_json(id int, fd json);
insert into tb_json values
                        (1, '{"user_id":123456789}'),
                        (2, '{"user_id":"123456789"}');
select id, fd, json_type(fd->"$.user_id"), json_storage_size(fd), json_storage_free(fd)
from tb_json;


update tb_json set fd = json_set(fd, '$.user_id', "12345") where id = 2;

update tb_json set fd = json_set(fd, '$.user_id', "12345678901") where id = 2;

