insert into SYSTEM_USER(id,username,password) values(1,'aa','aa123');
insert into SYSTEM_USER(id,username,password) values(2,'bb','bb456');
insert into SYSTEM_ROLE(id,name) values(1,'ROLE_ADMIN');
insert into SYSTEM_ROLE(id,name) values(2,'ROLE_USER');
insert into SYSTEM_USER_ROLES(SYSTEM_USER_ID,ROLES_ID) values(1,1);
insert into SYSTEM_USER_ROLES(SYSTEM_USER_ID,ROLES_ID) values(2,2);