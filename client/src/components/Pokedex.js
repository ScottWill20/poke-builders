import Canvas from "./Canvas";
import CreatePokeForm from "./CreatePokeForm";

function Pokedex(){
    return (
        <>
        <div id="pokedex">
            <div id="left-grid">
                <div className="canvas-container">
                    <canvas id="canvas"></canvas>
                    {/* replace with our Canvas component */}
                    {/* user profile should be carousel */}
                </div>
                <div className="d-pad-container">
                    <nav className="d-pad">
                        <button className="btn btn-warning" id="d-pad-left"></button>
                        <button className="btn btn-warning" id="d-pad-right"></button>
                    </nav>
                </div>
                <div className="button-container">
                    <submit className="btn btn-success" id="submit-poke-btn">Submit Pokemon</submit>
                </div>
            </div>
            <div id="right-grid">
                <div className="form-container">
                    <CreatePokeForm />
                </div>
            </div>
        </div>
        </>
    );
}

export default Pokedex;