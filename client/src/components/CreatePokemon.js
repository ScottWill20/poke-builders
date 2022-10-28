import Canvas from "./Canvas";
import CreatePokeForm from "./CreatePokeForm";

function CreatePokemon() {
    return (
        <div className="container">
            <Canvas />
            <CreatePokeForm />
        </div>
    );
}

export default CreatePokemon;