-- "webdemoģ��:�������߼�  start..."  
--****************************************************************************
-- demo��Ϣ��
--****************************************************************************
--****************************************************************************
-- demo��Ϣ��
--****************************************************************************
create table wd_demo
(
	id	varchar(64) not null,
	isValid integer not null,
	loginName varchar(64) not null,
	password varchar(64) not null,
	parentId varchar(64),
	subDemoId varchar(64),
	name varchar(64) not null,
	createdate date default sysdate not null,
	lastupdatedate timestamp default sysdate not null,
	description varchar(2000),
	email varchar(256),
	testBigDecimal integer,
	testNullBigDecimal integer,
	testIntegerObj integer,
	testNullIntegerObj integer,
	testBooleanObj integer,
	testNullBooleanObj integer,
	testInt integer,
	testBoolean integer,
	primary key(id)
);
create index idx_demo_01 on wd_demo(lastupdatedate);
create unique index idx_demo_02 on wd_demo(loginName);

--comment on table wd_demo is 'demo��Ϣ��';
--comment on column wd_demo.id is 'Ȩ��id';
--comment on column wd_demo.isValid is '�Ƿ���Ч��1��Ч 0��Ч';
--****************************************************************************
-- ��Ա��Ϣ��
--****************************************************************************
create table wd_operator
(
  id varchar(64),
  code varchar(64),
  name varchar(64),
  password varchar(64),
  createdate date,
  updatedate date,
  description varchar(2000),
  primary key(id)
);
-- "webdemoģ��:�������߼�  end..."
-- "webdemoģ��:�������߼�  start..."  
--****************************************************************************
-- Ȩ�޹�����caΪcomponents_auth�ļ�д
--****************************************************************************
create table t_auth_authitem
(
  id varchar(64) not null,	
  parentId varchar(64),	
  name varchar(256),	
  description varchar(1024),		
  authType varchar(64) not null, 
  isViewAble integer default 1,	
  isEditAble integer default 1,	
  isValid integer default 1,		
  primary key(id)
);
create table t_auth_authref
(
  authid varchar(128) not null,
  refid  varchar(64) not null,
  authreftype varchar(64) not null,
  createdate date default sysdate not null,
  enddate date,
  createoperid varchar(64) not null,
  primary key(authid,refid,authreftype)
);
create index idx_auth_authref_02 on t_auth_authref(refid,authreftype);
create index idx_auth_authref_03 on t_auth_authref(enddate);

--comment on table t_auth_authref is 'Ȩ�޹�������Ϣ��';
--comment on column t_auth_authref.authid is 'Ȩ��id';
--comment on column t_auth_authref.refId is '������id����Ϊ��ɫid,�û�id��';
--comment on column t_auth_authref.authreftype is 'Ȩ�޹��������� AUTHREFTYPE_OPERATOR AUTHREFTYPE_OPERATOR_TEMP AUTHREFTYPE_ROLE';
--comment on column t_auth_authref.createdate is 'Ȩ�޹������ʱ��';
--comment on column t_auth_authref.enddate is 'Ȩ�޹�����ĿʧЧʱ��';
--comment on column t_auth_authref.createoperid is 'Ȩ��������';

--****************************************************************************
-- ��ɫ��Ϣ��
--****************************************************************************
create table t_auth_role
(
  id varchar(64) not null,
  name varchar(64) not null,
  creatOperId varchar(64) not null,
  createdate date default sysdate not null,
  updatedate date,
  updateOperId varchar(64),
  description varchar(200),
  isDefault varchar(2) default 0,
  primary key (id)
);
create unique index idx_auth_role_01 on t_auth_role(name);
create index idx_auth_role_02 on t_auth_role (creatOperId);

--comment on table t_auth_role is '��ɫ��Ϣ��';
--comment on column t_auth_role.id is '��ɫid';
--comment on column t_auth_role.name is '��ɫ��';
--comment on column t_auth_role.createOperId is '��ɫ������';
--comment on column t_auth_role.updatedate is '��ɫ����ʱ��';
--comment on column t_auth_role.updateOperId is '��ɫ����޸���';
--comment on column t_auth_role.updatedate is '��ɫ����޸�ʱ��';
--comment on column t_auth_role.description is '��ɫ����';

-- "webdemoģ��:�������߼�  end..."
