const POKEAPI_BASE_URL = 'https://pokeapi.co/api/v2';

export async function findAllPokemonPokeAPI() {
    const response = await fetch(`${POKEAPI_BASE_URL}/pokemon`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
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
    return imgUrl;
  }

export async function findAllAbilitesPokeAPI() {
    const response = await fetch(`${POKEAPI_BASE_URL}/ability?limit=327`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
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

    return listMoves.results.map(b => b.name);

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
    const pokemove = await findMoveByName(move);
    
    const flavorText = pokemove.flavor_text_entries.filter(a => 
        a.language.name === 'en' &&
        a.version_group.name === 'sword-shield');


    return flavorText[0].flavor_text;
}

