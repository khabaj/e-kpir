insert into role(role_name) 
SELECT 'ADMIN'
WHERE
NOT EXISTS (
    SELECT role_name FROM role WHERE role_name = 'ADMIN'
);

insert into role(role_name) 
SELECT 'USER'
WHERE
NOT EXISTS (
    SELECT role_name FROM role WHERE role_name = 'USER'
);

insert into user(login,password,role_id) 
SELECT 'admin', 'admin',1
WHERE
NOT EXISTS (
    SELECT login FROM user WHERE login = 'admin'
);




