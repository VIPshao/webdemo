--****************************************************************************
-- ��ɫ��Ϣ��
--****************************************************************************
create table t_auth_role
(
  id varchar2(64) not null,
  name varchar2(64) not null,
  creatOperId varchar2(64) not null,
  createdate date default sysdate not null,
  updatedate date,
  updateOperId varchar2(64),
  description varchar2(200),
  isDefault varchar2(2) default 0,
  primary key (id)
);
create unique index idx_auth_role_01 on t_auth_role(name);
create index idx_auth_role_02 on t_auth_role (creatOperId);

comment on table t_auth_role is '��ɫ��Ϣ��';
comment on column t_auth_role.id is '��ɫid';
comment on column t_auth_role.name is '��ɫ��';
comment on column t_auth_role.createOperId is '��ɫ������';
comment on column t_auth_role.updatedate is '��ɫ����ʱ��';
comment on column t_auth_role.updateOperId is '��ɫ����޸���';
comment on column t_auth_role.updatedate is '��ɫ����޸�ʱ��';
comment on column t_auth_role.description is '��ɫ����';

