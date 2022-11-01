import { React, useState, useEffect, useParams } from "react";
import Select from "react-select";
import { ListMoveNames } from "../services/pokeAPI";
import { ListAbilityNames } from"../services/pokeAPI";
import { motion } from "framer-motion";
import { updatePokemon } from "../services/pokemon";
import { createPokemon } from "../services/pokemon";
import { findByPokemonId } from "../services/pokemon";

function CreatePokeForm({handleClick, handleChange}) {

    const formDataDef = {
        name: ``,
        height: ``,
        weight: ``,
        description: ``,
        type: ``,
        nature: ``,
        ability: ``,
        moves: [],
        private: false
    }

    const POKE_DEFAULT = {
        name: "",
        height: 0,
        weight: 0,
        description: "",
        type: "",
        vibe: "",
        ability: "",
        moves: [],
        private: true
    }

    // const endpoint = "http://localhost:8080/api/pokemon";
    const [abilities, setAbilities] = useState([]);
    const [moves, setMoves] = useState([]);
    const [selectedOptions, setSelectedOptions] = useState([]);
    const [pokemonData, setPokemonData] = useState(formDataDef);

    const [pokemon, setPokemon] = useState(POKE_DEFAULT);
    // const [editPokemonId, setEditPokemonId] = useState(0);
    // const [errors, setErrors] = useState([]);
    // const { id } = useParams();


    useEffect(() => {
        ListMoveNames()
        .then(setMoves)
        .catch(console.log());

        ListAbilityNames()
        .then(setAbilities)
        .catch(console.log());

    });

    const handleFormUpdate = (evt) => {
        const newPokemonData = {...pokemonData};
        newPokemonData[evt.target.name] = evt.target.value;
        newPokemonData.ability = pokemonData.ability;
        newPokemonData.moves = pokemonData.moves;
        setPokemonData(newPokemonData);
        console.log(newPokemonData);
        handleChange(newPokemonData);
    }

    return (
        <>
        {/* {errors.length > 0 && (
        <div className="errors-container">
          <h3>The following errors occured:</h3>
          <ul>
            {errors.map((error) => {
              return <li>{error}</li>;
            })}
          </ul>
        </div>
      )} */}

            <div className="text-input-container">
            <div className="form-group">
                <label htmlFor="name">Name:</label>
                <input id="name"
                name="name"
                type="text"
                className="form-control"
                value={pokemonData.name}
                onChange={handleFormUpdate}
                required />
            </div>
            <div className="form-group">
                <label htmlFor="height">Height (meters):</label>
                <input id="height"
                name="height"
                type="number"
                step="0.1"
                className="form-control"
                value={pokemonData.height}
                onChange={handleFormUpdate}
                required />
            </div>
            <div className="form-group">
                <label htmlFor="weight">Weight (kilograms):</label>
                <input id="weight"
                name="weight"
                type="number"
                step="0.1"
                className="form-control"
                value={pokemonData.weight}
                onChange={handleFormUpdate}
                required />
            </div>
            <div className="form-group">
                <label htmlFor="description">Description:</label>
                <textarea id="description"
                name="description"
                // type="textarea"
                className="form-control"
                value={pokemonData.description}
                onChange={handleFormUpdate}
                />
            </div>
            </div>
            <div className="drop-downs-container">
            <div className="form-group">
                <label htmlFor="type">Type:</label>
                <select id="type"
                name="type"
                className="form-control"
                value={pokemonData.type}
                onChange={handleFormUpdate}
                required >
                    <option value="" selected disabled hidden>[select a type]</option>
                    <option value="normal">NORMAL</option>
                    <option value="fire">FIRE</option>
                    <option value="water">WATER</option>
                    <option value="grass">GRASS</option>
                    <option value="electric">ELECTRIC</option>
                    <option value="ice">ICE</option>
                    <option value="fighting">FIGHTING</option>
                    <option value="poison">POISON</option>
                    <option value="ground">GROUND</option>
                    <option value="flying">FLYING</option>
                    <option value="psychic">PSYCHIC</option>
                    <option value="bug">BUG</option>
                    <option value="rock">ROCK</option>
                    <option value="ghost">GHOST</option>
                    <option value="dark">DARK</option>
                    <option value="dragon">DRAGON</option>
                    <option value="steel">STEEL</option>
                    <option value="fairy">FAIRY</option>
                </select>
            </div>
            <div className="form-group">
                <label htmlFor="nature">Nature:</label>
                <select id="nature"
                name="nature"
                className="form-control"
                value={pokemonData.nature}
                onChange={handleFormUpdate}
                required >
                    <option value="" selected disabled hidden>[select a nature]</option>
                    <option value="hardy">HARDY</option>
                    <option value="lonely">LONELY</option>
                    <option value="brave">BRAVE</option>
                    <option value="adamant">ADAMANT</option>
                    <option value="naughty">NAUGHTY</option>
                    <option value="bold">BOLD</option>
                    <option value="docile">DOCILE</option>
                    <option value="relaxed">RELAXED</option>
                    <option value="impish">IMPISH</option>
                    <option value="lax">LAX</option>
                    <option value="timid">TIMID</option>
                    <option value="hasty">HASTY</option>
                    <option value="serious">SERIOUS</option>
                    <option value="jolly">JOLLY</option>
                    <option value="naive">NAIVE</option>
                    <option value="modest">MODEST</option>
                    <option value="mild">MILD</option>
                    <option value="quiet">QUIET</option>
                    <option value="bashful">BASHFUL</option>
                    <option value="rash">RASH</option>
                    <option value="calm">CALM</option>
                    <option value="gentle">GENTLE</option>
                    <option value="sassy">SASSY</option>
                    <option value="careful">CAREFUL</option>
                    <option value="quirky">QUIRKY</option>
                </select>
            </div>
            <div className="form-group">
                <label htmlFor="ability">Ability:</label>
                <Select 
                    placeholder={".Select..."}
                    name="ability"
                    options = {abilities.map(ability => {
                        return (
                            { value: ability, label: ability }
                        )
                    })}
                    
                    onChange={(o) => pokemonData.ability=o}
                    className="basic-multi-select"
                    classNamePrefix="select"
                    />
            </div>
            <div className="form-group">
                <label htmlFor="moves">Moves:</label>
                <Select 
                    placeholder={".Select..."}
                    onChange={(o) => {
                        setSelectedOptions(o);
                        pokemonData.moves = o;}}
                    isMulti
                    name="moves"
                    options = {moves.map(move => {
                        return (
                            { value: move, label: move }
                        )
                    })}
                    className="basic-multi-select"
                    classNamePrefix="select" 
                    isOptionDisabled={() => selectedOptions.length === 4}
                    />
            </div>
            <div className="form-check form-switch">
                <input 
                className="form-check-input" 
                type="checkbox" 
                id="flexSwitchCheckChecked" 
                name="flexSwitchCheckChecked" 
                value={pokemon.private}
                // onChange={handleChange}
                />
                <label 
                className="form-check-label" 
                htmlFor="flexSwitchCheckChecked">private</label>
            </div>
            </div>
            <div className="button-container">
                    <motion.button whileTap={{ scale: 0.9 }} className="nes-btn is-success" id="submit-poke-btn" type="submit" onSubmit={handleClick}>Submit Pokemon</motion.button>
                </div>

        </>
    );
}

export default CreatePokeForm;