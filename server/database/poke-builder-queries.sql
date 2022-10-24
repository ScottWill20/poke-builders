use poke_builder;

select * from pokemon
    inner join poke_type on poke_type.pokemon_id = pokemon.pokemon_id
	inner join `type` on `type`.type_id = poke_type.type_id_1;

