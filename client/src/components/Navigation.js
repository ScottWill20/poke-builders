import { motion } from 'framer-motion';
import { Link } from 'react-router-dom';

function Navigation() {
    return (
        <>
        <nav className="nav-bar navbar navbar-expand-lg">
        <motion.h1 className="nav-container mr-5" whileHover={{ scale: 1.1 }}>
            <Link to="/"><h1>PokeBuilder</h1></Link>
            </motion.h1>
        <button className="navbar-toggler" 
            type="button" 
            data-toggle="collapse" 
            data-target="#navbarSupportedContent" 
            aria-controls="navbarSupportedContent" 
            aria-expanded="false" 
            aria-label="Toggle navigation"
            >
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="nav-container navbar-nav mr-auto">
                <motion.li className="nav-item" whileHover={{ scale: 1.1 }}><Link to="/create-pokemon">Create a Pokemon</Link></motion.li>
                <motion.li className="nav-item" whileHover={{ scale: 1.1 }}><Link to="/register">Create Account</Link></motion.li>
                <motion.li className="nav-item" whileHover={{ scale: 1.1 }}><Link to="/login">Login</Link></motion.li>
            </ul>
            
            </div>
        </nav> 
        </>
    );
}

export default Navigation;