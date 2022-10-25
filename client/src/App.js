import CreatePokeForm from "./components/CreatePokeForm";
import CreatePokeDraw from "./components/CreatePokeDraw";

function App() {
  return (
    <>
    <h1>PokeBuilder</h1>
    <div className="container">
      <CreatePokeDraw />
      <CreatePokeForm />
    </div>
    
    </>
  );
}

export default App;
