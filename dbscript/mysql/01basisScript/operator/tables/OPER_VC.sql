create table OPER_VC(
	id varchar(64) not null,  	-- ������id
	parentId varchar(64),		-- �ϼ�������id
	code varchar(64),			-- ������code
	name varchar(64) not null,	-- ����������
	remark varchar(2000),		-- �����ı�ע
	primary key(ID)
);
create unique index idx_OPER_VC on OPER_VC(name);
create unique index idx_OPER_VC on OPER_VC(code);
