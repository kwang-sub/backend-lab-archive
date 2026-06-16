create table tb_char_escape(fd varchar(100));

insert into tb_char_escape values('ab''ba');
insert into tb_char_escape values("ab""ba");
insert into tb_char_escape values("ab\'ba");
insert into tb_char_escape values('ab\"ba');
insert into tb_char_escape values('ab""ba');
insert into tb_char_escape values("ab''ba");

select *
from tb_char_escape;