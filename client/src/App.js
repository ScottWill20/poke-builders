import Pokedex from "./components/Pokedex";
import Navigation from "./components/Navigation";
import Loader from "./components/Loader";
import HomePage from "./components/HomePage";
import CreateAccount from "./components/CreateAccount";
import CreatePokemon from "./components/CreatePokemon";
import PokeGrid from "./components/PokeGrid";
import {BrowserRouter as Router, Switch, Route } from "react-router-dom";
import AuthContext from "./contexts/AuthContext";
import User from "./contexts/UserContext";

function App() {

  const user_default = {
    userId: 0,
    username: "",
    password: ""
  }

  const [user, setUser] = useState(user_default);

  const login = setUser;
  const logout = () => {
    setUser();
    localStorage.removeItem("jwt");
  }

  const auth = {
    user,
    login, 
    logout
  }
  
  return (
      <AuthContext.Provider value={auth}>
        <User.Provider value={user}>
      <Router>
      {/* <h1>PokeBuilder</h1> */}
        <Navigation />
          <div className="container">
            <Switch>
              {/* <HomePage /> */}
              {/* <Pokedex /> */}
              {/* <Loader /> */}
              {/* <CreateAccount /> */}
              <Route exact path="/">
                <HomePage />
                <Loader />
              </Route>
            </Switch>
          </div>
      </Router>
      </User.Provider>
     </AuthContext.Provider>

  );
}

export default App;
