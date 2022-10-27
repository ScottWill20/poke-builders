import { motion } from 'framer-motion';

function Navigation() {
    return (
        <>
        <nav className="nav-bar navbar navbar-expand-lg">
        <motion.h1 className="nav-container mr-5" whileHover={{ scale: 1.1 }}><h1>PokeBuilder</h1></motion.h1>
        <button class="navbar-toggler" 
            type="button" 
            data-toggle="collapse" 
            data-target="#navbarSupportedContent" 
            aria-controls="navbarSupportedContent" 
            aria-expanded="false" 
            aria-label="Toggle navigation"
            >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="nav-container navbar-nav mr-auto">
                <motion.li className="nav-item" whileHover={{ scale: 1.1 }}>Create a Pokemon</motion.li>
                <motion.li className="nav-item" whileHover={{ scale: 1.1 }}>Create Account</motion.li>
                <motion.li className="nav-item" whileHover={{ scale: 1.1 }}>Login</motion.li>
            </ul>
            
            </div>
        </nav> 
        </>
    );
}

export default Navigation;