import { motion } from "framer-motion";
import PokeCard from "./PokeCard";

function HomePage() { // pass in a list of public pokemon as props
    // will also need a mapper to determine number of total pokemon cards 
    return (
        <>
        <div className="poke-grid" layout>
            <PokeCard />
            <PokeCard />
            <PokeCard />
            <PokeCard />
            <PokeCard />
            <PokeCard />
            <PokeCard />
            <PokeCard />
        </div>
        </>
    );
}

export default HomePage;