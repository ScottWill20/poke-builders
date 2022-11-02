import { motion } from 'framer-motion';
import { useState, useEffect } from 'react';
import { getPokemonSprites } from '../services/pokeAPI';
import Canvas from "./Canvas";
import CreatePokeForm from "./CreatePokeForm";
import CreatePokemon from './CreatePokemon';
import PokeCard from './PokeCard';

function Pokedex({ pokemon }){

    const variants = {
        hidden: { y: 1000, 
            x: -500
            // rotate: -180 
        },
        visible: { 
            y: 0, 
            x: 0,
            // rotate: 0,
            transition: { duration: 1, ease: "easeInOut" } 
        }
    }

    // const [pokeCard, setPokeCard] = useState(null);

    // useEffect(() => {
    //     setPokeCard(PokeCard);
    // }, []);

    // console.log(pokeCard);
    // const [index, setIndex] = useState(0);
    // const [userPokemon, setUserPokemon] = useState([]);
    // const pokemon = [...userPokemon];
    // console.log(pokemon);

    // useEffect(() => {
    //     setUserPokemon(pokemon);
    //     console.log(userPokemon);
    // }, []);

    // function handlePageLeft(event) {
    //     if(index === 0){
    //         setIndex(pokemon.length - 1);
    //     } else {
    //         setIndex(index - 1);
    //     }
    // }

    // function handlePageRight(event) {
    //     if(index === pokemon.length - 1){
    //         setIndex(0);
    //     } else {
    //         setIndex(index + 1);
    //     }
    // }

    return (
        <>
        <motion.div 
        variants={variants} 
        initial="hidden" 
        animate="visible" 
        id="pokedex"
        >
            <div id="left-grid">
                <div className="carousel-container">
                    <div className="carousel-img-container">
                    {/* {pokemon.map(p => <PokeCard key={p.pokemonId} pokemon={p} />)} */}
                        {/* <img src={PokeCard.url} /> */}
                        <PokeCard pokemon={pokemon} />
                        {/* {PokeCard} */}
                {/* <div className='user-profile-pic-border'></div>
                <img className='user-profile-pic'>{getPokemonSprites}</img> */}
                    </div>
                </div>
            </div>
            <div id="right-grid">
                <div className="form-container">
                    {/* <CreatePokeForm /> */}
                    {/* <CreatePokeForm currentPokemon={pokemon}/> */}
                    <CreatePokemon currentPokemon={pokemon} canvas={false}/>
                </div>
            </div>
        
            
        </motion.div>
        </>
    );
}

export default Pokedex;