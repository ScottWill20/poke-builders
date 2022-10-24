drop database if exists poke_builder;
create database poke_builder;
use poke_builder;

use poke_builder;

drop table if exists app_user_role;
drop table if exists app_role;
drop table if exists app_user;

create table app_user (
	app_user_id int primary key auto_increment,
    username varchar(50) not null,
    password_hash varchar(1024) not null,
    enabled boolean not null default true
);

create table app_role (
	app_role_id int primary key auto_increment,
    `name` varchar(50) not null
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
	(1, 'USER'),
    (2, 'ADMIN');
    
insert into app_user (app_user_id, username, password_hash) values
	(1, 'user', '$2a$10$O07BeyVEy.eGy9zmJQR/WeIDdb5Q6PMDbTZlUXOMQ0B.EAkbiuUK6'),
    (2, 'admin', '$2a$10$z8mwVv2mOjkWkFuzxYUFcO6SH1FaEftCw4M2Ltv6/5x7nigwEJKIO');
    
insert into app_user_role(app_user_id, app_role_id) values
	(1, 1),
    (2, 2);

create table vibe (
vibe_id int primary key auto_increment,
vibe_name varchar(50) not null
);

create table ability (
ability_id int primary key auto_increment,
ability_name varchar(50) not null,
ability_description varchar(2000) not null
);

create table `type` (
type_id int primary key auto_increment,
type_name varchar(50) not null
);

create table poke_type (
pokemon_id int not null,
type_id_1 int not null,
type_id_2 int null,
constraint pk_poke_type
	primary key (pokemon_id, type_id_1, type_id_2),
constraint fk_poke_type_pokemon_id
	foreign key (pokemon_id)
    references pokemon(pokemon_id),
constraint fk_poke_type_type_id_1
	foreign key (type_id_1)
    references `type`(type_id),
constraint fk_poke_type_type_id_2
	foreign key (type_id_2)
    references `type`(type_id)
);

create table move (
move_id int primary key auto_increment,
move_name varchar(100) not null,
move_description varchar(200) not null,
type_id int not null
);

create table poke_move (
pokemon_id int not null,
move_id_1 int not null,
move_id_2 int not null,
move_id_3 int not null,
move_id_4 int not null,
constraint pk_poke_move
	primary key (pokemon_id, move_id_1, move_id_2, move_id_3, move_id_4),
constraint fk_poke_move_pokemon_id
	foreign key (pokemon_id)
    references pokemon(pokemon_id),
constraint fk_poke_move_move_id_1
	foreign key (move_id_1)
    references move(move_id),
constraint fk_poke_type_type_id_2
	foreign key (move_id_2)
    references move(move_id),
constraint fk_poke_move_move_id_3
	foreign key (move_id_3)
    references move(move_id),
constraint fk_poke_type_type_id_4
	foreign key (move_id_4)
    references move(move_id)
);

create table pokemon (
pokemon_id int primary key auto_increment,
pokemon_name varchar(100) not null,
height int not null,
weight int not null,
birthday date not null,
vibe_id int not null,
user_id int not null,
ability_id int not null,
constraint fk_pokemon_ability
	foreign key (ability_id)
    references ability(ability_id),
constraint fk_pokemon_vibe
	foreign key (vibe_id)
    references vibe(vibe_id),
constraint fk_pokemon_user
	foreign key (user_id)
    references app_user (user_id)
);