import CreatePokeForm from "./components/CreatePokeForm";
import CreatePokeDraw from "./components/CreatePokeDraw";
import Pokedex from "./components/Pokedex";

function App() {
  return (
    <>
    <h1>PokeBuilder</h1>
    <div className="container">
      <Pokedex />
      {/* <CreatePokeDraw />
      <CreatePokeForm /> */}
    </div>
    
    </>
  );
}

export default App;
