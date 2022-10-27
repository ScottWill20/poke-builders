drop database if exists poke_builder;
create database poke_builder;
use poke_builder;

drop table if exists app_user_role;
drop table if exists app_role;
drop table if exists app_user;

create table app_user (
	app_user_id int primary key auto_increment,
    email varchar(250) not null,
    avatar varchar(300) not null,
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
    
insert into app_user (app_user_id, email, avatar, username, password_hash) values
	(1, 'Jane@email.com', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/134.png', 'jane', '$2a$10$O07BeyVEy.eGy9zmJQR/WeIDdb5Q6PMDbTZlUXOMQ0B.EAkbiuUK6'),
    (2, 'Delanie@email.com', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png', 'delanie', '$2a$10$z8mwVv2mOjkWkFuzxYUFcO6SH1FaEftCw4M2Ltv6/5x7nigwEJKIO'),
    (3, 'Scott@email.com', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/009.png', 'scott', '');
insert into app_user_role(app_user_id, app_role_id) values
	(1, 2),
    (2, 2),
    (3, 2);

create table ability (
ability_id int primary key auto_increment,
ability_name varchar(50) not null,
ability_description varchar(2000) not null
);

insert into ability (ability_name, ability_description) values
	("Adaptability", "Powers up moves of the same type as the pokemon"),
    ("Cheek Pouch", "Restores HP as well as when the pokemon eats a berry"),
    ("Water Absorb", "Restores HP if hit by a Water-type move instead of taking damage.");

create table `type` (
type_id int primary key auto_increment,
type_name varchar(50) not null
);

insert into `type` (type_name) values 
	("Normal"),
    ("Fire"),
    ("Fairy"),
    ("Water");
    
create table pokemon (
pokemon_id int primary key auto_increment,
pokemon_name varchar(100) not null,
pokemon_description varchar(2000) null,
url varchar(300) not null,
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

insert into pokemon (pokemon_name, pokemon_description, url, height, weight, birthday, app_user_id, ability_id, `type`, vibe, private) values
	("Snorlax", "It is not satisfied unless it eats over 880 pounds of food every day. When it is done eating, it goes promptly to sleep.", "https://www.serebii.net/swordshield/pokemon/143.png", 10, 460, "2012-12-25", 1, 2, "Normal", "Naughty", false),
    ("Jigglypuff", "Jigglypuff has top-notch lung capacity, even by comparison to other Pokémon. It won't stop singing its lullabies until its foes fall asleep.", "https://www.serebii.net/swordshield/pokemon/039.png", 0.3, 5.5, "2017-06-20", 1, 1, "Fairy", "Bashful", true),
    ("Eevee", "It has the ability to alter the composition of its body to suit its surrounding environment.", "https://www.serebii.net/swordshield/pokemon/133.png", 0.3, 6.5, "2022-09-01", 2, 1, "Normal", "Mild", false),
    ("Vaporeon", "When Vaporeon's fins begin to vibrate, it is a sign that rain will come within a few hours.", "https://www.serebii.net/swordshield/pokemon/134.png", 1, 29, "2020-09-01", 1, 3, "Water", "Jolly", true),
    ("Ditto", "It can reconstitute its entire cellular structure to change into what it sees, but it returns to normal when it relaxes.", "https://www.serebii.net/swordshield/pokemon/132.png", 0.3, 4, "2002-09-01", 2, 1, "Normal", "Impish", false);

create table poke_type (
pokemon_id int not null,
type_id_1 int not null,
type_id_2 int null,
constraint pk_poke_type
	primary key (pokemon_id, type_id_1),
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

insert into poke_type (pokemon_id, type_id_1, type_id_2) values
	(1,1,null),
    (2,1,3);
    

create table move (
move_id int primary key auto_increment,
move_name varchar(100) not null,
move_description varchar(500) not null
);

insert into move (move_name, move_description) values
	("Facade","This attack move doubles its power if the user is poisoned, burned, or paralyzed."),
    ("Yawn","The user lets loose a huge yawn that lulls the target into falling asleep on the next turn."),
    ("Body Slam","The user drops onto the target with its full body weight. This may also leave the target with paralysis."),
    ("Flamethrower","The target is scorched with an intense blast of fire. This may also leave the target with a burn."),
    ("Charm", "The user gazes at the target rather charmingly, making it less wary. This harshly lowers the target’s Attack stat.");

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
constraint fk_poke_move_move_id_2
	foreign key (move_id_2)
    references move(move_id),
constraint fk_poke_move_move_id_3
	foreign key (move_id_3)
    references move(move_id),
constraint fk_poke_move_move_id_4
	foreign key (move_id_4)
    references move(move_id)
);

insert into poke_move(pokemon_id, move_id) values
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 2), 
    (2, 3), 
    (2, 1),
    (2, 4),
    (3, 5);

