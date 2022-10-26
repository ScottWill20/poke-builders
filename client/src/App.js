import CreatePokeForm from "./components/CreatePokeForm";
import CreatePokeDraw from "./components/CreatePokeDraw";
import Pokedex from "./components/Pokedex";
import Navigation from "./components/Navigation";

function App() {
  return (
    <>
    {/* <h1>PokeBuilder</h1> */}
    <Navigation />
    <div className="container">
      <Pokedex />
    </div>
    
    </>
  );
}

export default App;
