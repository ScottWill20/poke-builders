//set the user (and then perform all things with that user)
//for now just make sure the in the user and in the pokemon match

import { useEffect, useState, useContext } from "react";
import { useHistory } from "react-router-dom";
import { findPokemonByUserId } from "../services/pokemon";
import User from "../contexts/UserContext";

function UserProfile({ loggedOn }) {
    //const [user, setUser] = useState(loggedOn);
    const user = useContext(User);
    const [pokemon, setPokemon] = useState();
    const history = useHistory();

    useEffect(() => {
        findPokemonByUserId(user.userId)
            .then(setPokemon)
            .catch(() => history.push("/error"));
    }, [history]);

    return (
        <>
            <div className="row row-cols-4 g-2">
            {pokemon.map(p => <PokeCard key={p.pokemonId} pokemon={p} />)}
        </div>
        </>
    );
}