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
        initial={{ rotate: 0, scale: 0.7 }}
        animate={{ rotate: 360 }}
        transition={{ duration: 1.5, repeat: Infinity}}
        >
            <i className="nes-pokeball"></i>
        </motion.div>
        </>
    );
}

export default Loader;