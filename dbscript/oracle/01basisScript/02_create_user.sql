prompt EXECUTE 02_create_user.sql...

-- �����û�
CREATE USER wdadmin IDENTIFIED BY wdadmin
  DEFAULT TABLESPACE WD_DAT
  TEMPORARY TABLESPACE WD_TEMP;
GRANT DBA TO wdadmin;

-- ��Ȩ
GRANT ALL PRIVILEGE TO wdadmin;
ALTER USER fnimsadmin DEFAULT ROLE ALL;

prompt EXECUTE 02_create_user.sql...DONE.

EXIT;

