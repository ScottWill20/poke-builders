import CreatePokeDraw from "./components/CreatePokeDraw";
import Pokedex from "./components/Pokedex";
import Navigation from "./components/Navigation";
import Loader from "./components/Loader";
import HomePage from "./components/HomePage";
import CreateAccount from "./components/CreateAccount";

function App() {
  return (
    <>
    {/* <h1>PokeBuilder</h1> */}
    <Navigation />
    <div className="container">
      <HomePage />
      {/* <Pokedex /> */}
      {/* <Loader /> */}
      {/* <CreateAccount /> */}
    </div>
    
    </>
  );
}

export default App;
