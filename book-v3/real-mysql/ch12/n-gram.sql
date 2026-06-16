show variables like '%ngram%';

create table tb_bi_gram (
    id bigint not null auto_increment,
    title varchar(100),
    body text,
    primary key (id),
    fulltext index fx_msg(title, body) with parser ngram
);
insert into tb_bi_gram values (null, 'Real MySQL', '이 책은 지금까지의 메뉴얼 번역이나 단편적인 지식 수준을 벗어나 저자와 다른 많은 MySQL 전문가의')

select count(*)
from tb_bi_gram
where match(title, body) AGAINST('단편' in boolean mode);

select count(*)
from tb_bi_gram
where match(title, body) against('적인' in boolean mode);

select count(*)
from tb_bi_gram
where match(title, body) against('단편적인' in boolean mode);

select count(*)
from tb_bi_gram
where match(title, body) against('이' in boolean mode);


create table tb_tri_gram(
    id bigint auto_increment,
    title varchar(100),
    body text,
    primary key (id),
    fulltext index fx_msh(title, body) with parser ngram
);

insert into tb_tri_gram values (null, 'Real MySQL', '이 책은 지금까지의 메뉴얼 번역이나 단편적인 지식 수준을 벗어나 저자와 다른 많은 MySQL 전문가');

select count(*) from tb_tri_gram where match(title, body) against('단편적' in boolean mode);
select count(*) from tb_tri_gram where match(title, body) against('편적인' in boolean mode);

select count(*) from tb_tri_gram where match(title, body) against('단편적인' in boolean mode);
select count(*) from tb_tri_gram where match(title, body) against('이' in boolean mode);
select count(*) from tb_tri_gram where match(title, body) against('책' in boolean mode);

select count(*) from tb_tri_gram where match(title, body) against('단편' in boolean mode);
select count(*) from tb_tri_gram where match(title, body) against('적인' in boolean mode);


