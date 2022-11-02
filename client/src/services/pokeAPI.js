const POKEAPI_BASE_URL = 'https://pokeapi.co/api/v2';

export async function findAllPokemonNamesPokeAPI() {
    const response = await fetch(`${POKEAPI_BASE_URL}/pokemon?limit=905`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function ListPokemonNames() {
    const listPokemon = await findAllPokemonNamesPokeAPI();

    return listPokemon.results.map(p => p.name.toUpperCase());
}

export async function findPokemonURLByName(name) {
    const response = await fetch(`${POKEAPI_BASE_URL}/pokemon/${name}`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function getPokemonSprites(name) {
    const pokemon = await findPokemonURLByName(name);
    const imgUrl = pokemon.sprites.front_default;
    console.log(imgUrl);
    return imgUrl;
  }


export async function findAllMovesPokeAPI() {
    const response = await fetch(`${POKEAPI_BASE_URL}/move?limit=844`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }

}

export async function ListMoveNames() {
    const listMoves = await findAllMovesPokeAPI();

    return listMoves.results.map(b => b.name.toUpperCase());

}

export async function findMoveByName(name) {
    const response = await fetch(`${POKEAPI_BASE_URL}/move/${name}`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function getMoveDescription(move) {
    const pokeMove = await findMoveByName(move.toLowerCase());
    
    const flavorText = pokeMove.flavor_text_entries.filter(a => 
        a.language.name === 'en' &&
        a.version_group.name === 'sword-shield');


    return flavorText[0].flavor_text;
}

export async function findAllAbilitesPokeAPI() {
    const response = await fetch(`${POKEAPI_BASE_URL}/ability?limit=327`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function ListAbilityNames() {
    const listAbility = await findAllAbilitesPokeAPI();

    return listAbility.results.map(b => b.name.toUpperCase());
}

export async function findAbilityByName(name) {
    const response = await fetch(`${POKEAPI_BASE_URL}/ability/${name}`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}



export async function getAbilityDescription(ability) {
    const pokeAbility = await findAbilityByName(ability.toLowerCase());
    
    const flavorText = pokeAbility.flavor_text_entries.filter(a => 
        a.language.name === 'en' &&
        a.version_group.name === 'sword-shield');


    return flavorText[0].flavor_text;
}

