import { motion } from 'framer-motion';
import Canvas from "./Canvas";
import CreatePokeForm from "./CreatePokeForm";
import CreatePokeDraw from "./CreatePokeDraw";

function Pokedex(){

    const variants = {
        hidden: { y: 1000, 
            // rotate: -180 
        },
        visible: { 
            y: 0, 
            // rotate: 0,
            transition: { duration: 1, ease: "easeInOut" } 
        }
    }

    return (
        <>
        <motion.div 
        variants={variants} 
        initial="hidden" 
        animate="visible" 
        id="pokedex">
            <div id="left-grid">
                <div className="canvas-container">
                    <motion.canvas id="canvas"></motion.canvas>
                    {/* replace with our Canvas component */}
                    {/* user profile should be carousel */}
                    <div className="canvas-controls">
                        <CreatePokeDraw />
                    </div>
                </div>
                <div className="d-pad-container">
                    <nav className="d-pad">
                        <motion.button  whileTap={{ scale: 0.9 }} className="btn btn-warning" id="d-pad-left"></motion.button>
                        <motion.button whileTap={{ scale: 0.9 }} className="btn btn-warning" id="d-pad-right"></motion.button>
                    </nav>
                </div>
                <div className="button-container">
                    <motion.submit whileTap={{ scale: 0.9 }} className="btn btn-success" id="submit-poke-btn">Submit Pokemon</motion.submit>
                </div>
            </div>
            <div id="right-grid">
                <div className="form-container">
                    <CreatePokeForm />
                </div>
            </div>
        </motion.div>
        </>
    );
}

export default Pokedex;