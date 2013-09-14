--****************************************************************************
-- 组织信息表：OPER_ORGANIZATION
--****************************************************************************
drop table OPER_ORGANIZATION;
create table OPER_ORGANIZATION(
  fullAddress varchar2(255),
  remark varchar2(2000),
  alias varchar2(255),
  code varchar2(64) not null,
  type varchar2(64),
  id varchar2(64) not null,
  parentId varchar2(64),
  districtId varchar2(64),
  chiefType varchar2(64),
  address varchar2(255),
  name varchar2(64) not null,
  fullName varchar2(255) not null,
  chiefId varchar2(64),
  primary key(ID)
);
create unique index idx_oper_organ_01 on OPER_ORGANIZATION(code);
create index idx_oper_organ_02 on OPER_ORGANIZATION(parentId);
create index idx_oper_organ_03 on OPER_ORGANIZATION(chiefType,chiefId);

comment on table OPER_ORGANIZATION is '组织信息表';
comment on column OPER_ORGANIZATION.type is '组织类型';
comment on column OPER_ORGANIZATION.chiefType is '组织主管类型：可以是人员，可以是职位，也可以是其他...';
