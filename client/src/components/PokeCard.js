import { motion } from 'framer-motion';

function PokeCard() { // pass in pokemon name, username, and img as props
    return (
        <>
        <motion.div className="container poke-card" whileHover={{ scale: 1.05 }}>
            <h3>Name</h3>
            <img className="poke-picture" src="https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png" alt="Bulbasaur"></img>
            <h4>@username</h4>
        </motion.div>
        </>
    );
}

export default PokeCard;