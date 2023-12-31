import { useContext } from "react";
import { motion } from 'framer-motion';
import { Link } from 'react-router-dom';
import AuthContext from "../contexts/AuthContext";

function Navigation() {

    const auth = useContext(AuthContext);

    return (
        <>
        <nav className="nav-bar navbar navbar-expand-lg">
        <Link to="/">
            <motion.h1 id="nav-title" className="nav-container mr-5" whileHover={{ scale: 1.1 }}>
                PokeBuilder
            </motion.h1>
        </Link>
        <button className="navbar-toggler" 
            type="button" 
            data-toggle="collapse" 
            data-target="#navbarSupportedContent" 
            aria-controls="navbarSupportedContent" 
            aria-expanded="true" 
            aria-label="Toggle navigation"
            >
            <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="nav-container navbar-nav mr-auto">
                    <Link to="/create-pokemon">
                        <motion.li className="nav-hover" whileHover={{ scale: 1.1 }}>New Pokemon</motion.li></Link>

                        {!auth.user ? <Link to="/register">
                            <motion.li className="nav-hover" whileHover={{ scale: 1.1 }}>Create Account</motion.li></Link> : 
                                        <Link to="/profile">
                            <motion.li className="nav-hover" whileHover={{ scale: 1.1 }}>View Profile</motion.li></Link>}

                        {!auth.user ? <Link to="/login">
                            <motion.li className="nav-hover" whileHover={{ scale: 1.1 }}>Login</motion.li></Link> : 
                                        <Link to="/" onClick={auth.logout}>
                            <motion.li className="nav-hover" whileHover={{ scale: 1.1 }}>Logout</motion.li></Link>}
                        
                </ul>
            </div>
        </nav> 
        </>
    );
}

export default Navigation;