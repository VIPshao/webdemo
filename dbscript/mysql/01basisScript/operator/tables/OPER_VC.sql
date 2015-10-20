create table OPER_VC(
	id varchar(64) not null,  	-- ������id
	parentId varchar(64),		-- �ϼ�������id
	code varchar(64),			-- ������code
	name varchar(64) not null,	-- ����������
	remark varchar(2000),		-- �����ı�ע
	primary key(ID)
);
create unique index IDX_OPER_VC_01 on OPER_VC(name);
create unique index IDX_OPER_VC_02 on OPER_VC(code);
