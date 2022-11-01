import { React, useState, useEffect } from "react";
import Select from "react-select";
import { ListPokemonNames } from "../services/pokeAPI";

function CreateAccount() {

    const [pokemon, setPokemon] = useState([]);

    useEffect(() => {
        ListPokemonNames()
        .then(setPokemon)
        .catch(console.log());
        
    });

    
    return (
        <div className="create-account-container">
        <form>
            <div className="form-group">
                <label htmlFor="email">Email:</label>
                <input id="email"
                name="email"
                type="email"
                className="form-control"
                required
                />
            </div>
            <div className="form-group">
                <label htmlFor="username">Username:</label>
                <input id="username"
                name="username"
                type="text"
                className="form-control"
                required
                />
            </div>
            <div className="form-group">
            <label htmlFor="password">Password:</label>
                <input id="password"
                name="password"
                type="text"
                className="form-control"
                required
                />
            </div>
            <div className="form-group">
                <label htmlFor="favPokemon">Favorite Pokemon:</label>
                <Select 
                    placeholder={".Select..."}
                    name="favPokemon"
                    options = {pokemon.map(pokemon => {
                        return (
                            { value: pokemon, label: pokemon }
                        )
                    })}
                    className="basic-multi-select"
                    classNamePrefix="select" />
            </div>
        </form>
        </div>
    );

}

export default CreateAccount;