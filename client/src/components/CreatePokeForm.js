
function CreatePokeForm() {
    return (
        <>
        <h2>Create a Pokemon</h2>
        <div>
            Errors will populate here.
        </div>

        <form>
            <div className="form-group">
                <label htmlFor="name">Name:</label>
                <input id="name"
                name="name"
                type="text"
                className="form-control"
                // value={pokemon.name}
                // onChange={handleChange}
                required />
            </div>
            <div className="form-group">
                <label htmlFor="height">Height (meters):</label>
                <input id="height"
                name="height"
                type="number"
                step="0.1"
                className="form-control"
                // value={pokemon.height}
                // onChange={handleChange}
                required />
            </div>
            <div className="form-group">
                <label htmlFor="weight">Weight (kilograms):</label>
                <input id="weight"
                name="weight"
                type="number"
                step="0.1"
                className="form-control"
                // value={pokemon.weight}
                // onChange={handleChange}
                required />
            </div>
            <div className="form-group">
                <label htmlFor="nature">Nature:</label>
                <select id="nature"
                name="nature"
                className="form-control"
                // value={pokemon.weight}
                // onChange={handleChange}
                required >
                    <option selected disabled hidden>[select a nature]</option>
                    <option value="hardy">hardy</option>
                    <option value="lonely">lonely</option>
                    <option value="brave">brave</option>
                    <option value="adamant">adamant</option>
                    <option value="naughty">naughty</option>
                    <option value="bold">bold</option>
                    <option value="docile">docile</option>
                    <option value="relaxed">relaxed</option>
                    <option value="impish">impish</option>
                    <option value="lax">lax</option>
                    <option value="timid">timid</option>
                    <option value="hasty">hasty</option>
                    <option value="serious">serious</option>
                    <option value="jolly">jolly</option>
                    <option value="naive">naive</option>
                    <option value="modest">modest</option>
                    <option value="mild">mild</option>
                    <option value="quiet">quiet</option>
                    <option value="bashful">bashful</option>
                    <option value="rash">rash</option>
                    <option value="calm">calm</option>
                    <option value="gentle">gentle</option>
                    <option value="sassy">sassy</option>
                    <option value="careful">careful</option>
                    <option value="quirky">quirky</option>
                </select>
            </div>
            <div className="form-group">
                <label htmlFor="type">Type:</label>
                <select id="type"
                name="type"
                className="form-control"
                // value={pokemon.weight}
                // onChange={handleChange}
                required >
                    <option selected disabled hidden>[select a type]</option>
                    <option value="normal">normal</option>
                    <option value="fire">fire</option>
                    <option value="water">water</option>
                    <option value="grass">grass</option>
                    <option value="electric">electric</option>
                    <option value="ice">ice</option>
                    <option value="fighting">fighting</option>
                    <option value="poison">poison</option>
                    <option value="ground">ground</option>
                    <option value="flying">flying</option>
                    <option value="psychic">psychic</option>
                    <option value="bug">bug</option>
                    <option value="rock">rock</option>
                    <option value="ghost">ghost</option>
                    <option value="dark">dark</option>
                    <option value="dragon">dragon</option>
                    <option value="steel">steel</option>
                    <option value="fairy">fairy</option>
                </select>
            </div>
            <div className="form-group">
                <select class="form-select" multiple="multiple" aria-label="multiple select example">
                <option selected disabled>Select Moves (4)</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                    <option value="4">Four</option>
                </select>
            </div>
            <div className="form-check form-switch">
                <input 
                className="form-check-input" 
                type="checkbox" 
                id="flexSwitchCheckChecked" 
                name="flexSwitchCheckChecked" checked />
                <label 
                className="form-check-label" 
                htmlFor="flexSwitchCheckChecked">private</label>
            </div>
        </form>

        </>
    );
}

export default CreatePokeForm;