--****************************************************************************
-- ��Ա���ñ���������Ա������ɫ���������飬�ȹ�����ϵ��OPER_OPERATOR
--****************************************************************************
DROP TABLE OPER_OPERATOR_REF_HIS;
CREATE TABLE OPER_OPERATOR_REF_HIS(
	OPERATORID VARCHAR2(64) NOT NULL,
	REFID VARCHAR2(64) NOT NULL,
	REFTYPE VARCHAR2(64) NOT NULL,
	CREATEDATE DATE DEFAULT SYSDATE NOT NULL,
	ENDDATE DATE DEFAULT SYSDATE NOT NULL
);
