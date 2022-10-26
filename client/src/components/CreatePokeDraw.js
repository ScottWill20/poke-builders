import { useState } from 'react';
import Canvas from './Canvas';
import DrawArea from './Canvas';

function CreatePokeDraw() {

    const draw = context => {
        //TODO functional code should go here!
      };
      


    return (
        <>
            <div>
                {/* <div id="guide"></div>
                <canvas width="450" height="450" id="canvas"></canvas> */}
                <Canvas draw={draw} height={700} width={500} />
                {/* <DrawArea /> */}
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