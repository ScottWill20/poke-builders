import PokeCard from "./PokeCard";
import PokeGrid from "./PokeGrid";

function HomePage() { // pass in a list of public pokemon as props
    // will also need a mapper to determine number of total pokemon cards 
    return (
        <PokeGrid />
    );
}

export default HomePage;