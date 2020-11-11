--drop table if exists useraccounts;
--drop table if exists users;
--drop table if exists transactions;
--drop table if exists accounts;
--drop table if exists admins;

--truncate table useraccounts cascade;
--truncate table users cascade;
--truncate table transactions cascade;
--truncate table accounts cascade;
--truncate table admins cascade;

commit;
rollback;

select * from useraccounts;
select * from users;
select * from transactions;
select * from accounts;
select * from admins;

create table users (
  id serial primary key,
  username text not null unique,
  firstname text not null,
  password text not null
);

create table accounts (
	id int primary key,
	balance int not null,
	approved boolean not null
);

create table admins(
	id serial primary key,
	adminname text not null,
	password text not null
);

create table transactions (
	id serial primary key,
	context text not null,
	accountid int not null,
	constraint accountid foreign key(accountid) references accounts(id) on delete cascade on update cascade
);

create table useraccounts (
userid int not null, 
checkaccount int not null,
primary key(userid, checkaccount),

constraint fk_checkuser__admin
foreign key(userid) references admins(id) on delete cascade,

constraint fk_checkuser__user
foreign key(userid) references users(id) on delete cascade,

constraint fk_checkuser__accountid
foreign key(checkaccount) references accounts(id) on delete cascade on update cascade
);

insert into admins(adminname,password) values ('admin','pass');
insert into accounts values(12345, '5000', false);
insert into accounts values(55555, '10000', false);
insert into accounts values(77777, '5000', false);