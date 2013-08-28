--****************************************************************************
-- Ȩ�޹�����caΪcomponents_auth�ļ�д
--****************************************************************************
create table user_operator
(
  id varchar(64) not null,
  vcid varchar(64) not null,
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

