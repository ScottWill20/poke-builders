import { React, useState, useEffect, useParams } from "react";
import Select from "react-select";
import { ListMoveNames } from "../services/pokeAPI";
import { ListAbilityNames } from"../services/pokeAPI";
import { motion } from "framer-motion";
import { updatePokemon } from "../services/pokemon";
import { createPokemon } from "../services/pokemon";
import { findByPokemonId } from "../services/pokemon";

function CreatePokeForm({handleClick, handleChange, currentPokemon}) {
    /*
    for edit.
    if currentPokemon has a value...is truthy
    rig pokemonData to have the values in it.
    setSelectedOptions and setAbility
    */

    const formDataDef = {
        name: ``,
        height: ``,
        weight: ``,
        description: ``,
        type: ``,
        nature: ``,
        ability: ``,
        moves: [],
        private: true
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
    const [selectedAbility, setSelectedAbility] = useState();
    const [selectedOptions, setSelectedOptions] = useState([]);
    const [pokemonData, setPokemonData] = useState(formDataDef);
    const [isPrivate, setIsPrivate] = useState(true);

    // const [pokemon, setPokemon] = useState(POKE_DEFAULT);
    // const [editPokemonId, setEditPokemonId] = useState(0);
    const [errors, setErrors] = useState([]);
    // const { id } = useParams();


    useEffect(() => {
        ListMoveNames()
        .then(setMoves)
        .catch(console.log());

        ListAbilityNames()
        .then(setAbilities)
        .catch(console.log());

        if (currentPokemon) {
            //do stuff here
            console.log(currentPokemon);
            const newPokemonData = {...pokemonData};
            
            newPokemonData.id = currentPokemon.id;
            newPokemonData.name = currentPokemon.name;
            newPokemonData.description = currentPokemon.description;
            newPokemonData.height = currentPokemon.height;
            newPokemonData.weight = currentPokemon.weight;
            newPokemonData.type = currentPokemon.type;
            newPokemonData.nature = currentPokemon.vibe;
            newPokemonData.url = currentPokemon.url;

            newPokemonData.ability = { value: currentPokemon.ability.name, 
            label: currentPokemon.ability.name};
            
            //newPokemonData.moves = currentPokemon.moves;
            
            //newPokemonData.moves.push({value: currentPokemon.moves[0].name, label: currentPokemon.moves[0].name })
            newPokemonData.moves = [];
            currentPokemon.moves.forEach(move => {
                newPokemonData.moves.push({value: move.name, label: move.name })
            });
            newPokemonData.private = currentPokemon.private;
            console.log("Private?", newPokemonData.private);
            setSelectedAbility(newPokemonData.ability);
            setSelectedOptions(newPokemonData.moves);
            setPokemonData(newPokemonData);
        }
    }, [currentPokemon]);

    const handleFormUpdate = (evt) => {
        const newPokemonData = {...pokemonData};
        if (evt.target.name === "private") {
            newPokemonData[evt.target.name] = evt.target.checked;
            console.log("Is private????", evt.target.checked);
            console.log("Is private????", newPokemonData["private"]);
        }
        else {
            newPokemonData[evt.target.name] = evt.target.value;
        }
        newPokemonData.ability = pokemonData.ability;
        newPokemonData.moves = pokemonData.moves;
        setPokemonData(newPokemonData);
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
                required
                maxLength="100" />
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
                required
                min="0.1"
                max="1000000" />
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
                required
                min="0.1"
                max="1000000" />
            </div>
            <div className="form-group">
                <label htmlFor="description">Description:</label>
                <textarea id="description"
                name="description"
                // type="textarea"
                className="form-control"
                value={pokemonData.description}
                onChange={handleFormUpdate}
                maxLength="2000"
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
                    <option value="NORMAL">NORMAL</option>
                    <option value="FIRE">FIRE</option>
                    <option value="WATER">WATER</option>
                    <option value="GRASS">GRASS</option>
                    <option value="ELECTRIC">ELECTRIC</option>
                    <option value="ICE">ICE</option>
                    <option value="FIGHTING">FIGHTING</option>
                    <option value="POISON">POISON</option>
                    <option value="GROUND">GROUND</option>
                    <option value="FLYING">FLYING</option>
                    <option value="PSYCHIC">PSYCHIC</option>
                    <option value="BUG">BUG</option>
                    <option value="ROCK">ROCK</option>
                    <option value="GHOST">GHOST</option>
                    <option value="DARK">DARK</option>
                    <option value="DRAGON">DRAGON</option>
                    <option value="STEEL">STEEL</option>
                    <option value="FAIRY">FAIRY</option>
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
                    <option value="JOLLY">JOLLY</option>
                    <option value="naive">NAIVE</option>
                    <option value="modest">MODEST</option>
                    <option value="mild">MILD</option>
                    <option value="quiet">QUIET</option>
                    <option value="BASHFUL">BASHFUL</option>
                    <option value="RASH">RASH</option>
                    <option value="CALM">CALM</option>
                    <option value="GENTLE">GENTLE</option>
                    <option value="SASSY">SASSY</option>
                    <option value="CAREFUL">CAREFUL</option>
                    <option value="QUIRKY">QUIRKY</option>
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
                    value={selectedAbility}
                    onChange={(o) =>  {
                        setSelectedAbility(o);
                        pokemonData.ability=o}}
                    className="basic-multi-select"
                    classNamePrefix="select"
                    required
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
                    value={selectedOptions}
                    className="basic-multi-select"
                    classNamePrefix="select" 
                    isOptionDisabled={() => selectedOptions.length === 4}
                    required
                    />
            </div>
            <div className="form-group">
                <label>
                    <input type="checkbox" 
                    className="nes-checkbox is-dark" 
                    name="private" 
                    checked={pokemonData.private}
                    onChange={handleFormUpdate} />
                    <span id="private-checkbox"> Private</span>
                </label>
            </div>


            </div>
            <div className="button-container">
                    <motion.button whileTap={{ scale: 0.9 }} 
                    className="nes-btn is-success" 
                    id="submit-poke-btn" 
                    type="submit" 
                    onSubmit={handleClick}>{pokemonData.id > 0 ? "Edit Pokemon" : "Submit Pokemon"}</motion.button>
                </div>

        </>
    );
}

export default CreatePokeForm;