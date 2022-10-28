import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import { findAllPublicPokemon } from "../services/pokemon";
import PokeCard from "./PokeCard";

function PokeGrid({}) {
    const [pokemon, setPokemon] = useState([]);
    const history = useHistory();

    useEffect(() => {
        findAllPublicPokemon()
            .then(setPokemon)
            .catch(() => history.push());
    }, [history]);

    return (
        <>
        <div className="row row-cols-4 g-2">
            {pokemon.map(p => <PokeCard key={p.pokemonId} pokemon={p} />)}
        </div>
        </>
    );
}

export default PokeGrid;