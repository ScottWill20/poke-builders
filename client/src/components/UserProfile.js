//set the user (and then perform all things with that user)
//for now just make sure the in the user and in the pokemon match

import { useEffect, useState, useContext } from "react";
import { useHistory } from "react-router-dom";
import { findPokemonByUserId } from "../services/pokemon";
import { motion } from "framer-motion";
import PokeCard from "./PokeCard";
import User from "../contexts/UserContext";
import AuthContext from "../contexts/AuthContext";
import Carousel from "./Carousel";
import Pokedex from "./Pokedex";


function UserProfile() {
    const {user} = useContext(AuthContext);
    const [pokemon, setPokemon] = useState([]);
    const [index, setIndex] = useState(0);
    const history = useHistory();

    useEffect(() => {
        console.log("User? ", user);
        findPokemonByUserId(user.userId)
            .then(setPokemon)
            .catch(() => history.push("/error"));
    }, [history]);

    useEffect(() => {
        setIndex(index);
    }, [index]);

    // console.log(pokemon);
    // console.log(index);

    function handlePageLeft(event) {
        if(index === 0){
            setIndex(pokemon.length - 1);
        } else {
            setIndex(index - 1);
        }
    }

    function handlePageRight(event) {
        if(index === pokemon.length - 1){
            setIndex(0);
        } else {
            setIndex(index + 1);
        }
    }

    return (
        <>
            <div>
                <Pokedex PokeCard={<PokeCard pokemon={pokemon[index]} />} />
                {/* <PokeCard pokemon={pokemon[index]} /> */}
            </div>
            <div className="d-pad-container">
            <nav className="d-pad">
                <motion.button  whileTap={{ scale: 0.9 }} className="btn btn-warning" id="d-pad-left" onClick={handlePageLeft}></motion.button>
                <motion.button whileTap={{ scale: 0.9 }} className="btn btn-warning" id="d-pad-right" onClick={handlePageRight}></motion.button>
            </nav>
            </div>
        </>
    );
}

export default UserProfile;