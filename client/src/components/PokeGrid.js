import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import { motion } from "framer-motion";
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
        <motion.div 
        drag 
        dragPropagation 
        dragConstraints={{ left: 0, right: 300, top: 0, bottom: 300 }}
        dragElastic={0.2} 
        dragSnapToOrigin={true}
        dragMomentum={true}
        className="row row-cols-4 gx-2">
            {pokemon.map(p => <PokeCard key={p.pokemonId} pokemon={p} />)}
        </motion.div>
        </>
    );
}

export default PokeGrid;