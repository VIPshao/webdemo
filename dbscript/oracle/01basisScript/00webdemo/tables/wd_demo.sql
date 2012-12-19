--****************************************************************************
-- demo��Ϣ��
--****************************************************************************
create table wd_demo
(
	id	varchar2(64) not null,
	isValid number(1) not null,
	loginName varchar2(64) not null,
	password varchar2(64) not null,
	parentId varchar2(64),
	subDemoId varchar2(64),
	name varchar2(64) not null,
	createdate date default sysdate not null,
	lastupdatedate date default sysdate not null,
	description varchar2(2000),
	email varchar2(256),
	primary key(id)
);
create index idx_demo_01 on wd_demo(lastupdatedate);
create unique index idx_demo_02 on wd_demo(loginName);

comment on table wd_demo is 'demo��Ϣ��';
comment on column wd_demo.id is 'Ȩ��id';
comment on column wd_demo.isValid is '�Ƿ���Ч��1��Ч 0��Ч';
