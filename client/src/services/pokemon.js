const POKEMON_API_URL = 'http://localhost:8080/api/pokemon';

export async function findAllPublicPokemon() {
    const response = await fetch(POKEMON_API_URL);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function findPokemonByUserId(userId) {
    const response = await fetch(`${POKEMON_API_URL}/user/${userId}`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function findByPokemonId(pokemonId) {
    const response = await fetch(`${POKEMON_API_URL}/${pokemonId}`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

async function createPokemon(pokemon) {
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(pokemon)
    };

    const response = await fetch(POKEMON_API_URL, init);
    if (response.ok) {
        return Promise.resolve();
    } else if (response.status === 400) {
        const errs = await response.json();
        return Promise.reject(errs);
    } else {
        return Promise.reject();
    } 
}

async function updatePokemon(pokemon) {

    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(pokemon)
    };

    const response = await fetch(`${POKEMON_API_URL}/${pokemon.pokemonId}`, init);
    if (response.ok) {
        return Promise.resolve();
    } else if (response.status === 400) {
        const errs = await response.json();
        return Promise.reject(errs);
    } else {
        return Promise.reject();
    }
}

// export async function save(pokemon) {
//     return pokemon.pokemonId > 0 ? update(pokemon) : add(pokemon);
// }

export async function deleteByPokemonId(pokemonId) {
    const init = {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        }
    };
    const response = await fetch(`${POKEMON_API_URL}/${pokemonId}`, init);
    if (response.ok) {
        return Promise.resolve();
    } else {
        return Promise.reject();
    }
}
