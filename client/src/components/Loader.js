import { motion } from "framer-motion";

function Loader() {

    // const loaderVariants = {
    //     animation: {
    //         rotate: 360,
    //         duration: 0.5
    //     }
    // }

    return (
        <>
        <motion.div className="loader"
        initial={{ rotate: 0 }}
        animate={{ rotate: 360 }}
        transition={{ duration: 2, repeat: Infinity}}
        >
            {/* <svg width="80" height="80" id="pokeball-small-circle">
                <circle cx="0" cy="0" r="40" stroke="black" stroke-width="3" fill="black" />
            </svg> */}
            <svg width="100" height="100" 
            // id="pokeball-big-circle" 
            // stroke="black" 
            // stroke-width="3"
            >
                {/* <circle cx="50" cy="50" r="40" stroke="black" stroke-width="3" fill="white" /> */}
                <path
                stroke="black" 
                stroke-width="3"
                d="M 10 60 C 30 80, 60 80, 80 60 Z" />
            </svg>
        </motion.div>
        </>
    );
}

export default Loader;