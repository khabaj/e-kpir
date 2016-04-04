insert into user(login,password,role)
SELECT 'admin', 'admin','ADMIN'
WHERE
NOT EXISTS (
    SELECT login FROM user WHERE login = 'admin'
);




