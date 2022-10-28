import userEvent from "@testing-library/user-event";
import { useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import { motion } from "framer-motion";

function PokeCard( {pokemon} ) { // pass in pokemon name, username, and img as props

// const { user } = useContext(AuthContext);

    return (
        <div className="col">
            <motion.div className="container poke-card" 
                whileHover={{ 
                    scale: 1.03,
                    backgroundColor: "#fff"
                    }}>
                <h4>{pokemon.name}</h4>
                <img src={pokemon.url}/>
                <h5>{pokemon.user.username}</h5>
                {/* <h3>Name</h3>
                <img className="poke-picture" src="https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png" alt="Bulbasaur"></img>
                <h4>@username</h4> */}
            </motion.div>
        </div>
    );
}

export default PokeCard;

