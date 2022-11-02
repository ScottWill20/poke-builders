//set the user (and then perform all things with that user)
//for now just make sure the in the user and in the pokemon match

import { useEffect, useState, useContext } from "react";
import { useHistory, Link } from "react-router-dom";
import { findPokemonByUserId } from "../services/pokemon";
import { motion } from "framer-motion";
import PokeCard from "./PokeCard";
import User from "../contexts/UserContext";
import AuthContext from "../contexts/AuthContext";
import Pokedex from "./Pokedex";
import Loader from "./Loader";
import { deleteByPokemonId } from "../services/pokemon";


function UserProfile() {
    const {user} = useContext(AuthContext);
    const [pokemon, setPokemon] = useState([]);
    const [index, setIndex] = useState(0);
    const history = useHistory();
    // const [formData, setFormData] = useState(formDataDef);

    // const formDataDef = {
    //     name: ``,
    //     height: 0,
    //     weight: 0,
    //     description: ``,
    //     type: ``,
    //     nature: ``,
    //     ability: ``,
    //     moves: [],
    //     private: false
    // }

    useEffect(() => {
        if(user){
            console.log("User? ", user);
            findPokemonByUserId(user.userId)
                .then(setPokemon)
                .catch(() => history.push("/error"));
        }
    }, [user]);

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


    const handleEditPokemon = () => {}

    if(!pokemon.length) {
        return (
            <>
                <Loader />
                <div>
                    <h3>Oops!</h3>
                    <p>You haven't made any Pokemon! You can make a Pokemon <Link to="/create-pokemon">here</Link>.</p>
                </div>
            </>
        );
    }

    const variants = {
        hidden: { x: -900, 
            // rotate: -180 
        },
        visible: { 
            x: 0, 
            // rotate: 0,
            transition: { duration: 1, ease: "easeInOut" } 
        }
    }

    function handleDeletePokemon() {
        if(window.confirm(
            `Delete Pokemon ${pokemon[index].name}? This action cannot be undone.`
        )){
            deleteByPokemonId(pokemon[index].id)
            .then(() => {
                findPokemonByUserId(user.userId)
                .then(setPokemon)
            })
            .catch(() => history.push("/error"));
        }
    }

    return (
        <>
            {!pokemon &&
                <div>
                    <h3>Oops!</h3>
                    <p>You haven't made any Pokemon! You can make a Pokemon <Link path="/create-pokemon">here</Link>.</p>
                </div>
            }
            <div>
                {/* <Pokedex PokeCard={<PokeCard pokemon={pokemon[index]} />} /> */}
                <Pokedex pokemon={pokemon[index]}/>
                {/* <PokeCard pokemon={pokemon[index]} /> */}
            </div>
            <motion.div 
                variants={variants} 
                initial="hidden" 
                animate="visible" 
                className="d-pad-container"
                >
                <nav className="d-pad">
                    <motion.button  whileTap={{ scale: 0.9 }} className="nes-btn is-primary" id="d-pad-left" onClick={handlePageLeft}></motion.button>
                    <motion.button whileTap={{ scale: 0.9 }} className="nes-btn is-primary" id="d-pad-right" onClick={handlePageRight}></motion.button>
                </nav>
                {/* <motion.button  whileTap={{ scale: 0.9 }} className="nes-btn is-primary" id="edit-pokemon-btn" >Edit</motion.button> */}
                <motion.button whileTap={{ scale: 0.9 }} className="nes-btn is-warning" id="delete-pokemon-btn" onClick={handleDeletePokemon}>Delete</motion.button>
            {/* </div> */}
            </motion.div>
        </>
    );
}

export default UserProfile;