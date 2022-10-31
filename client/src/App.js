import Pokedex from "./components/Pokedex";
import Navigation from "./components/Navigation";
import Loader from "./components/Loader";
import HomePage from "./components/HomePage";
import CreateAccount from "./components/CreateAccount";
import CreatePokemon from "./components/CreatePokemon";
import PokeGrid from "./components/PokeGrid";
import {BrowserRouter as Router, Switch, Route } from "react-router-dom";
import AuthContext from "./contexts/AuthContext";
import {getMoveDescription, ListMoveNames} from "./services/pokeAPI";
import CreatePokeForm from "./components/CreatePokeForm";

// ListMoveNames().then(a => console.log(a));

function App() {
  
  return (
    // <AuthContext.Provider value={auth}>
      <Router>
      {/* <h1>PokeBuilder</h1> */}
        <Navigation />
          <div className="container">
            <Switch>
              <CreatePokeForm />
              {/* <HomePage /> */}
              {/* <Pokedex /> */}
              {/* <Loader /> */}
              {/* <CreateAccount /> */}
              <Route exact path="/">
                {/* <HomePage />
                <Loader /> */}
              </Route>
            </Switch>
          </div>
      </Router>
    // </AuthContext.Provider>

  );
}

export default App;
