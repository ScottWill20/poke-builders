import React, { useRef, useEffect, useState } from 'react';
import { motion } from 'framer-motion';

function Canvas( {handleChange}) {

  const [drawing, setDrawing] = useState(false);
  const [color, setColor] = useState(null);
  const [rangeSlider, setRangeSlider] = useState(1);
  const canvasRef = useRef(null);
  const contextRef = useRef(null);

  useEffect(() => {
    const canvas = canvasRef.current;
    canvas.style.width = "100%";
    canvas.style.height = "90vh";
    canvas.width = canvas.offsetWidth;
    canvas.height = canvas.offsetHeight;

  }, [window.innerWidth, window.innerHeight]);

  useEffect(() => {
    const canvas = canvasRef.current;

    const context = canvas.getContext('2d');
    context.scale(1, 1);
    context.lineCap = 'round';
    context.strokeStyle = color;
    context.lineWidth = rangeSlider;
    contextRef.current = context;
  }, [color, rangeSlider]);

  const startDraw = ({ nativeEvent }) => {
    const { offsetX, offsetY } = nativeEvent;
    contextRef.current.beginPath();
    contextRef.current.moveTo(offsetX, offsetY);
    setDrawing(true);
  }

  const stopDraw = () => {
    contextRef.current.closePath();
    setDrawing(false);
    handleChange(canvasRef.current.toDataURL());
  }

  const draw = ({ nativeEvent }) => {
    if(!drawing) return;
    const { offsetX, offsetY } = nativeEvent;
    contextRef.current.lineTo(offsetX, offsetY);
    contextRef.current.stroke();
  }

  const clear = () => {
    contextRef.current.clearRect(
      0,
      0,
      canvasRef.current.width,
      canvasRef.current.height
    );
  }

  const save = () => { // make this an exportable 
    // function called when pokemon detail form is submitted?
    const dataURL = canvasRef.current.toDataURL();
    console.log(dataURL);
    // const encodedString = dataURL.substring(22);
    // const saveURL = atob(encodedString);
    // console.log(saveURL);
    //data:image/png;base64,
  }


return (
  <>
    <canvas 
    ref={canvasRef} 
    onMouseDown={startDraw}
    onMouseUp={stopDraw}
    onMouseMove={draw}
    id="canvas" 
    />
    <div className='canvasDrawTool'>
      <div>
          <motion.button whileTap={{ scale: 0.9 }} onClick={clear} type="button" className='nes-btn'>Clear</motion.button>
      </div>
      <div className='canvasEdit'>
        <div>
            <label id='colorInputLabel' htmlFor="colorInput">Set Color: </label>
            <input type="color" id="colorInput" value={color} onChange={e => setColor(e.target.value)} />
        </div>
        <div>
          <label id= 'lineWidthLabel' htmlFor='lineWidth'>Line Width:</label>
          <input type="range" id="range-slider" min="1" max="100" className="slider" value={rangeSlider} onChange={e => setRangeSlider(e.target.value)} />
          <span id='rangeSliderNumber'>{" "}{rangeSlider}{'px'}</span>
        </div>
      </div>
    </div>
    </>
  );
};


export default Canvas;