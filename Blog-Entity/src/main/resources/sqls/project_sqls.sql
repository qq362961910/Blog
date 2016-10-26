--用户
create table blog_user(
id bigint,
create_time datetime,
update_time datetime,
username varchar(20),
email varchar(30),
phone varchar(11),
sex varchar(1),
passwd varchar(50),
avatar varchar(100)
);


-- 文章
create table blog_article(
id bigint,
title varchar(100),
create_time datetime,
update_time datetime,
owner_id bigint,
read_count int,
summary varchar(255),
content text,
like_count int,
key_words varchar(100),
deleted tinyint
);