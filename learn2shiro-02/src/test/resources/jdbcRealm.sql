/*通过默认jdbcRealm获取权限数据*/
delete from users;
delete from user_roles;
delete from roles_permissions;
insert into users(username, password, password_salt) values('admin', '123456', null);
insert into user_roles(username, role_name) values('admin', 'administrator');
insert into user_roles(username, role_name) values('admin', 'engineer');
insert into roles_permissions(role_name, permission) values('administrator', 'appModule:addApp');
insert into roles_permissions(role_name, permission) values('administrator', 'appModule:qryApp');
