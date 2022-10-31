import { useState } from "react";

function Carousel() {

    const [index, setIndex] = useState(0);

    function handlePageLeft(event) {
        if(index === 0){
            setIndex(array.length - 1);
        } else {
            setIndex(index - 1);
        }
    }

    function handlePageRight(event) {
        if(index === array.length - 1){
            setIndex(0);
        } else {
            setIndex(index + 1);
        }
    }

    return (
        <div className="carousel-container">
            <button className=""
            <img src={array[index].url} alt={array[index].description} />
        </div>
    );
}

export default Carousel;