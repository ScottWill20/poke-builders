import { motion } from 'framer-motion';

function Navigation() {
    return (
        <>
        <nav className="nav-bar">
            <ul className="nav-container">
                <motion.li whileHover={{ scale: 1.1 }}><h1>PokeBuilder</h1></motion.li>
                <motion.li whileHover={{ scale: 1.1 }}>Create a Pokemon</motion.li>
                <motion.li whileHover={{ scale: 1.1 }}>Create Account</motion.li>
                <motion.li whileHover={{ scale: 1.1 }}>Login</motion.li>
            </ul>
        </nav> 
        </>
    );
}

export default Navigation;