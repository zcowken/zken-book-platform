show databases;
use books_platform;

insert into school(school_name)
values ('asd')
;

# delete from  school where  id  =

# update school set school_name = school_name;

insert into contribute (title, brief_introduction, url, summary, user_id, picture, create_time, updateTime)
values (title, brief_introduction, url, summary, user_id, picture, create_time, updateTime);

# delete from contribute where

select contribute_id
from approved_contribute
where review_result = 1;

select *
from contribute
where id in (select contribute_id
             from approved_contribute
             where review_result = 1)
  and id in (select contribute_id from recommend)
  and user_id = 1;

# select * from  recommend where  contribute_id = 1

# select * from approved_contribute


select contribute.*
from read_tasks
         join read_tasks_contribute on
    read_tasks.id = read_tasks_contribute.read_tasks_id
         join contribute on read_tasks_contribute.contribute_id = contribute.id
where read_tasks_id = 1;


select *
from read_tasks;

# insert into  solicitation (teacher_id, cover_url, title, introduction, requirements, solicitation_content)

# insert into solicitation_contribute ( solicitation_id, contribute_id) values

update solicitation_contribute
set contribute_id   = 1,
    solicitation_id = 2

select contribute_id
from recommend;

select *
from contribute
where title like '%%';
select *
from contribute
where title like concat('%', '', '%');
