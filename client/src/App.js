import Pokedex from "./components/Pokedex";
import Navigation from "./components/Navigation";
import Loader from "./components/Loader";
import HomePage from "./components/HomePage";
import CreateAccount from "./components/CreateAccount";
import CreatePokemon from "./components/CreatePokemon";

function App() {
  return (
    <>
    {/* <h1>PokeBuilder</h1> */}
    <Navigation />
    <div className="container">
      {/* <HomePage /> */}
      {/* <Pokedex /> */}
      <Loader />
      {/* <CreateAccount /> */}
      <CreatePokemon />
    </div>
    
    </>
  );
}

export default App;
