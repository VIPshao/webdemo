--****************************************************************************
-- Ȩ�����t_auth_authitem
--****************************************************************************
create table t_auth_authitem
(
  id varchar(64) not null,			--Ȩ����Ψһ��key 
  parentId varchar(64),				--����Ȩ��id
  name varchar(256),				--Ȩ������ 
  description varchar(1024),			--Ȩ����Ŀ����
  authType varchar(64) not null, 	--Ȩ������
  isViewAble integer default 1,		--�Ƿ�ɼ�
  isEditAble integer default 1,		--�Ƿ�ɱ༭
  isValid integer default 1,		--isValid
  primary key(id)
);
