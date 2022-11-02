import Canvas from "./Canvas";
import CreatePokeForm from "./CreatePokeForm";
import AuthContext from "../contexts/AuthContext";
import { useRef, useState, useContext } from "react";
import { createPokemon, updatePokemon } from "../services/pokemon"
import { useHistory, Link } from "react-router-dom";

function CreatePokemon({currentPokemon, canvas}) {
    
    const {user} = useContext(AuthContext);
    const history = useHistory();

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
    const [errors, setErrors] = useState(false);

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
            if(formData.url){
                toPost.url = formData.url;
            } else {
                toPost.url = drawingUrl;
            }
            toPost.height = formData.height;
            toPost.weight = formData.weight;
            toPost.type = formData.type.toUpperCase();
            toPost.vibe = formData.nature.toUpperCase();
            toPost.ability = {...ABILITYDEF}
            toPost.ability.name = formData.ability.label;

            toPost.moves = [];
            formData.moves.forEach(move => {
                toPost.moves.push(move.value.substring(0, 1).toUpperCase().concat(move.value.substring(1)));
            })

            
            console.log("From form: ", formData.private);
            toPost.private = formData.private;

            console.log("Form?", formData);
            console.log(toPost);
            toPost.user = user;

            if (toPost.id) {
                updatePokemon(toPost).then(() => window.location.reload());
            }
            else {
                    createPokemon(toPost).then(() => history.push("/profile"));
            }
        } else {
            setErrors(true);
        }
        //gotta clean up the data so I can send it in as a pokemon.
        //but also, gotta make sure the user is clean too. They ALSO
        //have to be sent with the pokemon
        //make 
    }

    return (
        <div className="container">
            {!user && 
                <div className="alert alert-info">
                An account is required to save create Pokemon. Don't have an account? Create one <Link to="/register">here </Link>
                or <Link to="/login">Login</Link>.
                </div>
            }
            <form onSubmit={handleSubmit}>
            {canvas && 
                <Canvas handleChange={setDrawingUrl} />
            }
            <CreatePokeForm handleClick={handleSubmit} handleChange={setFormData} currentPokemon={currentPokemon} /> {/**pass default object as prop. Use it in setstate? */}
            </form>
            {errors && 
                <div className="alert alert-info">
                An account is required to save create Pokemon. Don't have an account? Create one <Link to="/register">here </Link>
                or <Link to="/login">Login</Link>.
                </div>
            }
        </div>
    );
}

export default CreatePokemon;