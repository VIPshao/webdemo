-- ****************************************************************************
-- ��MAINFRAME_LOGIN_LOG
-- ****************************************************************************
CREATE TABLE MAINFRAME_LOGIN_LOG(
	CLIENTIPADDRESS varchar(255),
	SYSTEMID varchar(64),
	ORGANIZATIONID varchar(64),
	MESSAGE varchar(255),
	CREATEDATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��', 
	VCID varchar(64) COMMENT '������',
	LOGINTYPE TINYINT(1) NOT NULL,
	ID varchar(64)  NOT NULL,
	OPERATORID varchar(64),
	OPERATORNAME varchar(64),
	OPERATORLOGINNAME varchar(64),
	primary key(ID)
);
-- ****************************************************************************
-- ��MAINFRAME_SYSOPE_LOG
-- ****************************************************************************
create table MAINFRAME_SYSOPE_LOG(
	CLIENTIPADDRESS varchar(255),
	FUNCTION varchar(255),
	SYSTEMID varchar(64),
	ORGANIZATIONID varchar(64),
	MESSAGE varchar(255),
	CREATEDATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	VCID varchar(64),
	ID varchar(64) not null,
	OPERATORID varchar(64),
	OPERATORNAME varchar(64),
	OPERATORLOGINNAME varchar(64),
	primary key(ID)
);
-- ****************************************************************************
-- ��OPER_EMPLOYEEINFO
-- ****************************************************************************
CREATE TABLE OPER_EMPLOYEEINFO(
	leavingDate date,
	sex TINYINT(1),
	operatorid varchar(64),
	code varchar(64),
	officialDate date,
	entryDate date,
	trialPeriodEndDate date,
	leaving TINYINT(1),
	name varchar(64),
	age TINYINT(1),
	official TINYINT(1) default 0 not null,
	lastUpdatePhoneLinkInfoDate date,
	cardNum varchar(255),
	primary key(OPERATORID)
);
create unique index idx_oper_emp_00 on OPER_EMPLOYEEINFO(code);
-- ****************************************************************************
-- ��OPER_EMPLOYEEINFO
-- ****************************************************************************
CREATE TABLE OPER_EMPLOYEEINFO_HIS(
	leavingDate date,
	sex TINYINT(1),
	operatorid varchar(64),
	code varchar(64),
	officialDate date,
	entryDate date,
	trialPeriodEndDate date,
	leaving TINYINT(1),
	name varchar(64),
	age TINYINT(1),
	official TINYINT(1) default 0 not null,
	lastUpdatePhoneLinkInfoDate date,
	cardNum varchar(255),
	primary key(OPERATORID)
);
-- ****************************************************************************
-- ��OPER_OPERATOR
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_OPERATOR`; 
CREATE TABLE OPER_OPERATOR(
	id varchar(64) not null,
	valid TINYINT(1) DEFAULT 1 NOT NULL comment '�Ƿ���Ч 0 ��Ч  1��Ч  Ĭ����Ч',
	pwdErrCount int(10),
	historyPwd varchar(255),
	organizationId varchar(64),
	password varchar(255),
	invalidDate date,
	lastUpdateDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	pwdUpdateDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	mainPostId varchar(64),
	userName varchar(64),
	locked TINYINT(1) default 0 not null,
	createDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	examinePwd varchar(255),
	loginName varchar(64)  not null,
	primary key(ID)
) comment='ϵͳʹ����Ա��Ϣ��';
create unique index idx_oper_oper_00 on OPER_OPERATOR(loginName);
create index idx_oper_oper_01 on OPER_OPERATOR(loginName,password);

-- ****************************************************************************
-- ��OPER_OPERATOR
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_OPERATOR_HIS`; 
CREATE TABLE OPER_OPERATOR_HIS(
	id varchar(64),
	valid TINYINT(1) DEFAULT 1 NOT NULL,
	pwdErrCount INT(10),
	historyPwd varchar(255),
	organizationId varchar(64),
	password varchar(255),
	invalidDate date,
	lastUpdateDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	pwdUpdateDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	mainPostId varchar(64),
	userName varchar(64),
	locked TINYINT(1) default 0 not null,
	createDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	examinePwd varchar(255),
	loginName varchar(64)  not null,
	primary key(ID)
);
-- ****************************************************************************
-- ��Ա���ñ���������Ա������ɫ���������飬�ȹ�����ϵ��OPER_OPERATOR
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_OPERATOR_REF`; 
CREATE TABLE OPER_OPERATOR_REF(
	EFFECTIVEDATE timestamp,
	CREATEDATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	OPERATORID varchar(64) not null,
	REFID varchar(64) not null,
	ENDDATE timestamp,
	INVALIDDATE timestamp,
	REFTYPE varchar(255) not null,
	PRIMARY KEY(OPERATORID,REFID,REFTYPE)
);
CREATE INDEX IDX_OPER_OPERREF_00 ON OPER_OPERATOR_REF(OPERATORID,REFTYPE);
CREATE INDEX IDX_OPER_OPERREF_01 ON OPER_OPERATOR_REF(REFID,REFTYPE);
CREATE INDEX IDX_OPER_OPERREF_02 ON OPER_OPERATOR_REF(INVALIDDATE);
/*****************************************************************************
			��OPER_POSTTYPE
*****************************************************************************/
create table OPER_POSTTYPE(
	NAME varchar(64),
	CREATEDATE datetime,
	ID varchar(64),
	REMARK varchar(2000),
	LASTUPDATEDATE datetime,
	VALID bit,
	CODE varchar(64),
	primary key(ID)
);
-- ****************************************************************************
-- ��Ա���ñ���������Ա������ɫ���������飬�ȹ�����ϵ��OPER_OPERATOR
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_OPERATOR_REF_HIS`; 
CREATE TABLE OPER_OPERATOR_REF_HIS(
	EFFECTIVEDATE timestamp,
	CREATEDATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	OPERATORID varchar(64) not null,
	REFID varchar(64) not null,
	ENDDATE timestamp not null,
	INVALIDDATE timestamp,
	REFTYPE varchar(255) not null
);
CREATE INDEX IDX_OPER_OPERREF_HIS_00 ON OPER_OPERATOR_REF(OPERATORID,REFTYPE);
CREATE INDEX IDX_OPER_OPERREF_HIS_01 ON OPER_OPERATOR_REF(REFID,REFTYPE);
CREATE INDEX IDX_OPER_OPERREF_HIS_02 ON OPER_OPERATOR_REF(INVALIDDATE);
CREATE INDEX IDX_OPER_OPERREF_HIS_10 ON OPER_OPERATOR_REF(OPERATORID,REFID,REFTYPE);
-- ****************************************************************************
-- ��OPER_ORGANIZATION
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_ORGANIZATION`; 
CREATE TABLE OPER_ORGANIZATION(
  vcid varchar(64) not null,
  valid TINYINT(1) default 1 not null comment '�Ƿ���Ч 1 ��Ч 0 ��Ч',
  fullAddress varchar(255),
  remark varchar(2000),
  alias varchar(255),
  code varchar(64) not null,
  type varchar(64) comment '��֯����',
  id varchar(64) not null,
  parentId varchar(64),
  districtId varchar(64),
  chiefType varchar(64) comment '��֯�������ͣ���������Ա��������ְλ��Ҳ����������...',
  address varchar(255),
  name varchar(64) not null,
  fullName varchar(64) not null,
  chiefId varchar(64),
  primary key(ID)
) comment = '��֯��Ϣ';
create unique index idx_oper_organ_01 on OPER_ORGANIZATION(code);
create index idx_oper_organ_02 on OPER_ORGANIZATION(vcid);
create index idx_oper_organ_03 on OPER_ORGANIZATION(parentId);
create index idx_oper_organ_04 on OPER_ORGANIZATION(chiefType,chiefId);

-- ****************************************************************************
-- ��֯��Ϣ����ʷ��OPER_ORGANIZATION_HIS
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_ORGANIZATION_HIS`; 
CREATE TABLE OPER_ORGANIZATION_HIS(
  vcid varchar(64),
  valid TINYINT(1) default 1 not null,
  fullAddress varchar(255),
  remark varchar(2000),
  alias varchar(255),
  code varchar(64) not null,
  type varchar(64),
  id varchar(64) not null,
  parentId varchar(64),
  districtId varchar(64),
  chiefType varchar(64),
  address varchar(255),
  name varchar(64) not null,
  fullName varchar(64) not null,
  chiefId varchar(64),
  primary key(ID)
);
-- ****************************************************************************
-- ְλ��Ϣ��OPER_POST
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_POST`; 
CREATE TABLE OPER_POST(
	id varchar(64) not null,
	valid TINYINT(1) default 1 not null,
	parentId varchar(64),
	remark varchar(2000),
	name varchar(64),
	organizationId varchar(64),
	code varchar(64) comment 'ְλ����',
	fullName varchar(255),
	primary key(ID)
) comment ='ְλ��Ϣ';
create index idx_oper_post_00 on OPER_POST(parentId);
create index idx_oper_post_01 on OPER_POST(code);
create index idx_oper_post_02 on OPER_POST(organizationId);

-- ****************************************************************************
-- ְλ��Ϣ����ʷ��OPER_POST
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_POST_HIS`; 
create table OPER_POST_HIS(
	id varchar(64) not null,
	valid TINYINT(1) default 1 not null,
	parentId varchar(64),
	remark varchar(2000),
	name varchar(64),
	organizationId varchar(64),
	code varchar(64),
	fullName varchar(255),
	primary key(ID)
) comment = '��ʷְλ��Ϣ';
-- ****************************************************************************
-- ��OPER_VC
-- ****************************************************************************
DROP TABLE IF EXISTS `OPER_VC`; 
create table OPER_VC(
	id varchar(64) not null,
	parentId varchar(64),
	remark varchar(2000),
	name varchar(64) not null comment '��������',
	primary key(ID)
) comment = '������';
create unique index idx_OPER_VC on OPER_VC(name);

-- prompt "webdemoģ��:�������߼�  end...";
-- prompt "webdemoģ��:����sequence  start...";
-- prompt "webdemoģ��:����sequence end...";
-- prompt "webdemoģ��:����sequence  start...";
-- prompt "webdemoģ��:����sequence end...";
-- prompt "webdemoģ��:������   start...";
-- prompt "webdemoģ��:������  end...";
-- prompt "webdemoģ��:������   start...";
-- prompt "webdemoģ��:������  end...";
-- prompt "webdemoģ��:���������߼�  start...";
-- prompt "webdemoģ��:���������߼�  end...";
-- prompt "webdemoģ��:���������߼�  start...";
-- prompt "webdemoģ��:���������߼�  end...";
-- prompt "webdemoģ��:�����洢�����߼�  start...";
-- prompt "webdemoģ��:�����洢�����߼�  end...";
-- prompt "webdemoģ��:�����洢�����߼�  start...";
-- prompt "webdemoģ��:�����洢�����߼�  end...";
-- prompt "webdemoģ��:����������   start...";
-- prompt "webdemoģ��:����������  end...";
-- prompt "webdemoģ��:����������   start...";
-- prompt "webdemoģ��:����������  end...";
-- prompt "webdemoģ��:������ͼ�߼�  start...";
-- prompt "webdemoģ��:������ͼ�߼�  start...";
-- prompt "webdemoģ��:��ʼ����������  start...";

-- prompt "webdemoģ��:��ʼ����������  end...";
-- prompt "webdemoģ��:��ʼ����������  start...";
INSERT INTO OPER_OPERATOR(ID,ORGANIZATIONID,LOGINNAME,PASSWORD,USERNAME)
	values('123456','1000000','admin','admin','admin');
INSERT INTO OPER_OPERATOR(ID,ORGANIZATIONID,LOGINNAME,PASSWORD,USERNAME)
	values('123456002','1000000','pqy','pqy','pqy');
commit;
-- TRUNCATE TABLE OPER_ORGANIZATION;
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000',null,'��Ա','���Ź�˾','���Ź�˾','1000000','1000000','��˾');
 
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000','1000000','��Ա','ϵͳ������','���Ź�˾_ϵͳ������','1100000001','1100000001','����');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000','1000000','��Ա','������Դ��','���Ź�˾_ϵͳ������','1100000002','1100000002','����');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000','1000000','��Ա','�г���','���Ź�˾_�г���','1100000004','1100000004','����');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000','1100000004','��Ա','����һ��','���Ź�˾_�г���_����һ��','1100000004001','1100000004001','����');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000000','1100000004','��Ա','���۶���','���Ź�˾_�г���_���۶���','1100000004002','1100000004002','����');

insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000001','1000000','��Ա','�ֹ�˾A','���Ź�˾_�ֹ�˾A','1000001','1000001','�ֹ�˾');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000001','1000001','��Ա','����A','���Ź�˾_�ֹ�˾A_����A','100000101','100000101','����');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000001','1000001','��Ա','����B','���Ź�˾_�ֹ�˾A_����B','100000102','100000102','����');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000001','1000001','��Ա','����C','���Ź�˾_�ֹ�˾A_����C','100000103','100000103','����'); 
 
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000002','1000000','��Ա','�ֹ�˾B','�ֹ�˾B','1000002','1000002','�ֹ�˾');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('1000002','1000002','��Ա','�ֹ�˾����B','�ֹ�˾����B','1000002002','1000002002','����');

insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('2000000',null,'��Ա','��������˾A','��������˾A','2000000','2000000','�ֹ�˾');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('3000000',null,'��Ա','��������˾B','��������˾B','3000000','3000000','�ֹ�˾');
insert into OPER_ORGANIZATION(VCID,PARENTID,CHIEFTYPE,NAME,FULLNAME,ID,CODE,TYPE)
  values('3000000','3000000','��Ա','���´�A','��������˾B_���´�A','30000001','30000001','����');
commit;
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('1000000301',null,'���ž���','1000000',null,'���Ź�˾ϵͳ���������ž���');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('100000030101','1000000301','��Ŀ����','1000000',null,'���Ź�˾ϵͳ��������Ŀ����');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('100000030102','1000000301','SE','1000000',null,'���Ź�˾ϵͳ������SE');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010101','100000030101','�߼��������ʦ','1000000',null,'���Ź�˾ϵͳ�������߼��������ʦ');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010102','100000030101','����ʦ','1000000',null,'���Ź�˾ϵͳ����������ʦ');
insert into OPER_POST(id,parentId,name,organizationId,code,remark)
	values('10000003010103','100000030101','������ʦ','1000000',null,'���Ź�˾ϵͳ������������ʦ');
commit;
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('001',null,'���Ź�˾','���Ź�˾');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('00101','001','�ֹ�˾һ','�ֹ�˾һ');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('00102','001','�ֹ�˾��','�ֹ�˾��');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('002','001','��������˾A','��������˾A');
INSERT INTO OPER_VC(ID,PARENTID,NAME,REMARK)
	values('003','001','��������˾B','��������˾B');
commit;
-- prompt "webdemoģ��:��ʼ����������  end...";
-- prompt "webdemoģ��:��������job  end...";
-- prompt "webdemoģ��:��������job  end...";
