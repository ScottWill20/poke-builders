import { motion } from 'framer-motion';
import { Link } from 'react-router-dom';

function Navigation() {
    return (
        <>
        <nav className="nav-bar navbar navbar-expand-lg">
        <Link to="/">
            <motion.h1 className="nav-container mr-5" whileHover={{ scale: 1.1 }}>
            <h1>PokeBuilder</h1>
            </motion.h1>
        </Link>
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
            <Link to="/create-pokemon">
                <motion.li className="nav-item" whileHover={{ scale: 1.1 }}>Create New Pokemon</motion.li></Link>
            <Link to="/register">
                <motion.li className="nav-item" whileHover={{ scale: 1.1 }}>Create Account</motion.li></Link>
            <Link to="/login">
                <motion.li className="nav-item" whileHover={{ scale: 1.1 }}>Login</motion.li></Link>
            </ul>
            
            </div>
        </nav> 
        </>
    );
}

export default Navigation;