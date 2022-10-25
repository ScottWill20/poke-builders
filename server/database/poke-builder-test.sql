drop database if exists poke_builder_test;
create database poke_builder_test;
use poke_builder_test;

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

create table ability (
ability_id int primary key auto_increment,
ability_name varchar(50) not null,
ability_description varchar(2000) not null
);

create table pokemon (
pokemon_id int primary key auto_increment,
pokemon_name varchar(100) not null,
height double not null,
weight double not null,
birthday date not null,
app_user_id int not null,
ability_id int not null,
`type` varchar(15) not null,
vibe varchar(20) not null,
private boolean not null,
constraint fk_pokemon_ability
	foreign key (ability_id)
    references ability(ability_id),
constraint fk_pokemon_user
	foreign key (app_user_id)
    references app_user (app_user_id)
);

create table move (
move_id int primary key auto_increment,
move_name varchar(100) not null,
move_description varchar(200) not null
);

create table poke_move (
pokemon_id int not null,
move_id int not null,
constraint pk_poke_move
	primary key (pokemon_id, move_id),
constraint fk_poke_move_pokemon_id
	foreign key (pokemon_id)
    references pokemon(pokemon_id),
constraint fk_poke_move_move_id
	foreign key (move_id)
    references move(move_id)
);

delimiter //
create procedure set_known_good_state()
begin
	truncate table poke_move;
    truncate table pokemon;
	truncate table app_user;
    truncate table app_role;
    truncate table app_user_role;
    truncate table ability;
    truncate table move;

	insert into app_role (app_role_id, `name`) values
	(1, 'USER'),
    (2, 'ADMIN');
    
insert into app_user (app_user_id, username, password_hash) values
	(1, 'user', '$2a$10$O07BeyVEy.eGy9zmJQR/WeIDdb5Q6PMDbTZlUXOMQ0B.EAkbiuUK6'),
    (2, 'admin', '$2a$10$z8mwVv2mOjkWkFuzxYUFcO6SH1FaEftCw4M2Ltv6/5x7nigwEJKIO');
    
insert into app_user_role(app_user_id, app_role_id) values
	(1, 1),
    (2, 2);
    
    insert into ability (ability_name, ability_description) values
	("Adaptability", "Powers up moves of the same type as the pokemon"),
    ("Cheek Pouch", "Restores HP as well as when the pokemon eats a berry");
    
    insert into pokemon (pokemon_name, height, weight, birthday, app_user_id, ability_id, `type`, vibe, private) values
	("Snorlax", 10, 460, "2012-12-25", 1, 2, "Normal", "Naughty"),
    ("Jigglypuff", 0.5, 5.5, "2017-06-20", 1, 1, "Fairy", "Bashful");
    
    insert into move (move_name, move_description) values
	("Facade","This attack move doubles its power if the user is poisoned, burned, or paralyzed."),
    ("Yawn","The user lets loose a huge yawn that lulls the target into falling asleep on the next turn."),
    ("Body Slam","The user drops onto the target with its full body weight. This may also leave the target with paralysis."),
    ("Flamethrower","The target is scorched with an intense blast of fire. This may also leave the target with a burn.");
    
    insert into poke_move(pokemon_id, move_id) values
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 2), 
    (2, 3), 
    (2, 1),
    (2, 4);

end //
delimiter ;