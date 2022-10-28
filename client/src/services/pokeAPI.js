const POKEAPI_BASE_URL = 'https://pokeapi.co/api/v2';

export async function findAllPokemonPokeAPI() {
    const response = await fetch(`${POKEAPI_BASE_URL}/pokemon`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function findPokemonURL(name) {
    const response = await fetch(`${POKEAPI_BASE_URL}/pokemon/${name}`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function getPokemonSprites(name) {
    const pokemon = await findPokemonURL(name);
    console.log(pokemon.sprites.front_default);
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

