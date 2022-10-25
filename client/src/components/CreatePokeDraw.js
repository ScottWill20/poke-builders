
function CreatePokeDraw() {
    return (
        <>
            <div>
                <div id="guide"></div>
                <canvas width="450" height="450" id="canvas"></canvas>
            </div>
            <div>
                <label for="colorInput">Set Color: </label>
                <input type="color" id="colorInput" />
            </div>
            <div>
                <label for="toggleGuide">Show Guide: </label>
                <input type="checkbox" id="toggleGuide" checked />
            </div>
            <div>
                <button type="button" id="clearButton">Clear</button>
            </div>
        </>
    );
}

export default CreatePokeDraw;