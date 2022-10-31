//set the user (and then perform all things with that user)
//for now just make sure the in the user and in the pokemon match

import { useEffect, useState, useContext } from "react";
import { useHistory } from "react-router-dom";
import { findPokemonByUserId } from "../services/pokemon";
import PokeCard from "./PokeCard";
import User from "../contexts/UserContext";
import AuthContext from "../contexts/AuthContext";


function UserProfile() {
    const {user} = useContext(AuthContext);
    const [pokemon, setPokemon] = useState([]);
    const history = useHistory();

    useEffect(() => {
        console.log("User? ", user);
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

export default UserProfile;