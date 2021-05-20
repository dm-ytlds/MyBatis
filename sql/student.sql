drop table if exists student;
create table student(
	id int not null,
	name varchar(20) not null,
	email varchar(40),
	age int,
	primary key(id)
);


insert into student(id, name, email, age) values
(1001, 'zs', 'zs@qq.com', 23),
(1002, 'ls', 'ls@qq.com', 25);
commit;


select * from student;