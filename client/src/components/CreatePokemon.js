import Canvas from "./Canvas";
import CreatePokeForm from "./CreatePokeForm";
import { useContext } from "react";
import AuthContext from "../contexts/AuthContext";
import { useRef, useState } from "react";

function CreatePokemon() {
    
    const {user} = useContext(AuthContext);
    const formDataDef = {
        name: ``,
        height: 0,
        weight: 0,
        description: ``,
        type: ``,
        nature: ``,
        ability: ``,
        moves: [],
        private: false
    }

    const POKEDEF = {
        id: 0,
        name: '',
        description: '',
        url: '',
        height: 0,
        weight: 0,
        birthday: Date.now(),
        type: null,
        vibe: null,
        user: null,
        moves: [],
        ability: null,
        isPrivate: false
    }

    const [drawingUrl, setDrawingUrl] = useState(``);
    const [formData, setFormData] = useState(formDataDef);

    //handleSubmit...getting all the inputs. including the canvas url
    const handleSubmit = (evt) => {
        evt.preventDefault();
        console.log("Here's johnny!");
        console.log("Why is this empty??", drawingUrl);
        console.log("The form???", formData);
        if (user) {
            const toPost = {
                
            }
        }
        //gotta clean up the data so I can send it in as a pokemon.
        //but also, gotta make sure the user is clean too. They ALSO
        //have to be sent with the pokemon
        //make 
    }

    return (
        <div className="container">
            {}
            <form onSubmit={handleSubmit}>
            <Canvas handleChange={setDrawingUrl}/> {/**pass canvasRef as prop */}
            <CreatePokeForm handleClick={handleSubmit} handleChange={setFormData}/> {/**pass default object as prop. Use it in setstate? */}
            </form>
        </div>
    );
}

export default CreatePokemon;