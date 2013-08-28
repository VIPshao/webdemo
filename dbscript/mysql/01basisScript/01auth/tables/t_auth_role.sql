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

