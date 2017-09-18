conn system/manager
grant connect, resource to auction identified by auction
/
conn auction/auction
drop table bidding_info
/
drop table mailinfo
/
drop table item_master
/
drop table category_master
/
drop table uinfo_master
/
create table uinfo_master
(uname	varchar2(20) primary key,
pwd	varchar(10) not null,
fname	varchar2(10),
lname	varchar2(10),
email	varchar2(25) not null,
phno	varchar2(15),
address	varchar2(50),
city	varchar2(15),
state	varchar2(15),
pin	varchar2(6),
country	varchar2(20),
ccardno	number(16))
/
create table category_master
(catid	varchar2(10) primary key,
cat_name varchar2(20))
/
insert into category_master values('E00001', 'Electronics')
/
insert into category_master values('C00002', 'Computers')
/
insert into category_master values('M00003', 'Mobiles')
/
insert into category_master values('J00004', 'Jewellery')
/
insert into category_master values('A00005', 'Art '||'&'||' Collections')
/
insert into category_master values('H00006', 'Home '||'&'||'Life')
/
insert into category_master values('T00007', 'Travel')
/
create table item_master
(itemid	varchar2(6) primary key,
catid varchar2(10) references category_master(catid),
itemname varchar2(20),
description varchar2(200),
summary	varchar2(50),
startprice number(8),
incr_price number(5),
stdate date,
enddate date,
sellerid varchar2(20) references uinfo_master(uname),
bidcnt number(4))
/
create table bidding_info
(bidderid varchar2(20) references uinfo_master(uname),
bamt number(8),
itemid varchar2(6) references item_master(itemid) on delete cascade,
biddate	date )
/
create table mailinfo
(bidderid varchar(20) references uinfo_master on delete cascade,
itemid varchar(6) references item_master on delete cascade,
paid varchar(3))
/
commit
/