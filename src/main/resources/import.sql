-- Rôles
insert into role (name) values ('ROLE_PRODUCT_MANAGER');
insert into role (name) values ('ROLE_MANAGER');

-- Mot de passe BCrypt 'secret'
insert into member (created, disabled, email, first_name, last_name, login , password,role_id) values ('2019-05-01', null, 'pmanager@grocery.com', 'Mr', 'ProductManager', 'pmanager', '$2a$10$aK81fm.BSg13nxvjEKcEfeD/3cCphRLVCHiQgyhvX1tHYLkH309RC',1);
insert into member (created, disabled, email, first_name, last_name, login , password,role_id) values ('2019-05-01', null, 'manager@grocery.com', 'Mr', 'Manager', 'manager', '$2a$10$aK81fm.BSg13nxvjEKcEfeD/3cCphRLVCHiQgyhvX1tHYLkH309RC',2);
