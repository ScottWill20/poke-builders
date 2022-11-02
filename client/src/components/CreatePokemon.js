import Canvas from "./Canvas";
import CreatePokeForm from "./CreatePokeForm";
import { useContext } from "react";
import AuthContext from "../contexts/AuthContext";
import { useRef, useState } from "react";
import { createPokemon, updatePokemon } from "../services/pokemon"

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
        type: null,
        vibe: null,
        user: null,
        moves: [],
        ability: null,
        private: false
    }

    const MOVEDEF = {
        id: 0,
        name: "",
        description: "will get this later"
    }

    const ABILITYDEF = {
        id: 0,
        name: "",
        description: "should really get this later"
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
            const toPost = {...POKEDEF}
            //if toPost.id, do put. Else, do post
            toPost.id = formData.id;
            toPost.name = formData.name;
            toPost.description = formData.description;
            toPost.url = drawingUrl;
            toPost.height = formData.height;
            toPost.weight = formData.weight;
            toPost.type = formData.type.toUpperCase();
            toPost.vibe = formData.nature.toUpperCase();
            toPost.ability = {...ABILITYDEF}
            toPost.ability.name = formData.ability.label;

            const moveOne = {...MOVEDEF};
            moveOne.name = formData.moves[0].value;
            let temp = moveOne.name.substring(0, 1).toUpperCase().concat(moveOne.name.substring(1));
            toPost.moves.push(temp);

            const moveTwo = {...MOVEDEF};
            console.log("The moves", formData.moves);
            moveTwo.name = formData.moves[1].value;
            temp = moveTwo.name.substring(0, 1).toUpperCase().concat(moveTwo.name.substring(1));
            toPost.moves.push(temp);

            const moveThree = {...MOVEDEF};
            moveThree.name = formData.moves[2].value;
            temp = moveThree.name.substring(0, 1).toUpperCase().concat(moveThree.name.substring(1));
            toPost.moves.push(temp);

            const moveFour = {...MOVEDEF};
            moveFour.name = formData.moves[3].value;
            temp = moveFour.name.substring(0, 1).toUpperCase().concat(moveFour.name.substring(1));
            toPost.moves.push(temp);
            console.log("From form: ", formData.private);
            toPost.private = formData.private;

            console.log(toPost);
            toPost.user = user;

            if (toPost.id) {
                updatePokemon(toPost).then(u=> console.log(u));
            }
            else {
            createPokemon(toPost).then(u=> console.log(u));
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
            <Canvas handleChange={setDrawingUrl}/>
            <CreatePokeForm handleClick={handleSubmit} handleChange={setFormData}/> {/**pass default object as prop. Use it in setstate? */}
            </form>
        </div>
    );
}

export default CreatePokemon;