import { Link } from "react-router-dom";
import { motion } from "framer-motion";

function NotFound() {
  return (
    <main>
      <div className="fourohfour-page">
        <div className="fourohfour-title">
          <h1>Oh No!</h1>
        </div>
        <div className="fourohfour-message">
          <p>Looks like Shinx is lost.</p>
          <Link className="body-link" to="/"><p>Go Home{`>`}</p></Link>
        </div>
        <div className="fourohfour-img">
          <img alt="Luxio Pokémon Drawing" 
          title="Luxio Pokémon Drawing" 
          src="https://assets.pokemon.com/assets/cms2/img/pokedex/full/403.png"
          width="400"
          height="400"></img>
        </div>
      </div>
    </main>
  );
}

export default NotFound;