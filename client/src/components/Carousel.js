import { useEffect, useState } from "react";
import { motion } from "framer-motion";
import { findPokemonByUserId } from "../services/pokemon";

function Carousel(src) {

    // const [index, setIndex] = useState(0);
    // const [userPokemon, setUserPokemon] = useState([]);
    // // const pokemon = [...userPokemon];
    // // console.log(pokemon);

    // useEffect(() => {
    //     setUserPokemon(src);
    // }, [userPokemon]);

    // function handlePageLeft(event) {
    //     if(index === 0){
    //         setIndex(userPokemon.length - 1);
    //     } else {
    //         setIndex(index - 1);
    //     }
    // }

    // function handlePageRight(event) {
    //     if(index === userPokemon.length - 1){
    //         setIndex(0);
    //     } else {
    //         setIndex(index + 1);
    //     }
    // }

    return (
        <>
        {/* <div className="carousel-container">
            <img src={src} />
        </div> */}
        {/* <div className="d-pad-container">
        <nav className="d-pad">
            <motion.button  whileTap={{ scale: 0.9 }} className="btn btn-warning" id="d-pad-left" onClick={handlePageLeft}></motion.button>
            <motion.button whileTap={{ scale: 0.9 }} className="btn btn-warning" id="d-pad-right" onClick={handlePageRight}></motion.button>
        </nav>
    </div> */}
    </>
    );
}

export default Carousel;