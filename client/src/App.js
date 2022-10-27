import CreatePokeDraw from "./components/CreatePokeDraw";
import Pokedex from "./components/Pokedex";
import Navigation from "./components/Navigation";
import Loader from "./components/Loader";

function App() {
  return (
    <>
    {/* <h1>PokeBuilder</h1> */}
    <Navigation />
    <div className="container">
      <Pokedex />
      <Loader />
    </div>
    
    </>
  );
}

export default App;
