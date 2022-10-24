drop table if exists app_user_role;
drop table if exists app_role;
drop table if exists app_user;

use poke_builder;

create table app_user (
	app_user_id int primary key auto_increment,
    username varchar(50) not null,
    password_hash varchar(1024) not null,
    enabled boolean not null default true
);

create table app_role (
	app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
	app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
		primary key(app_user_id, app_role_id),
	constraint fk_app_user_role_user_id
		foreign key(app_user_id)
        references app_user(app_user_id),
	constraint fk_app_user_role_role_id
		foreign key(app_role_id)
        references app_role(app_role_id)
);

insert into app_role (app_role_id, `name`) values
	(1, 'USER');
    
insert into app_user (app_user_id, username, password_hash) values
	(1, 'user', '$2a$10$O07BeyVEy.eGy9zmJQR/WeIDdb5Q6PMDbTZlUXOMQ0B.EAkbiuUK6');
    
insert into app_user_role(app_user_id, app_role_id) values
	(1, 1);