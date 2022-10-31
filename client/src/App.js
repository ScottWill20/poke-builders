import Pokedex from "./components/Pokedex";
import Navigation from "./components/Navigation";
import Loader from "./components/Loader";
import HomePage from "./components/HomePage";
import CreateAccount from "./components/CreateAccount";
import CreatePokemon from "./components/CreatePokemon";
import PokeGrid from "./components/PokeGrid";
import { useState } from "react";
import {BrowserRouter as Router, Switch, Route } from "react-router-dom";
import AuthContext from "./contexts/AuthContext";
import User from "./contexts/UserContext";
import Login from "./components/Login";
import UserProfile from "./components/UserProfile";
import Register from "./components/Register";
import CreatePokeForm from "./components/CreatePokeForm";

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
              {/* <Pokedex /> */}
              {/* <Loader /> */}
              <Route exact path="/">
                <HomePage />
              </Route>
              <Route exact path="/login">
                <Login/>
              </Route>
              <Route exact path="/register">
                <Register/>
              </Route>
              <Route exact path="/create-pokemon">
                <CreatePokemon/>
              </Route>
              <Route exact path="/profile">
                <UserProfile/>
              </Route>
            </Switch>
          </div>
      </Router>
      </User.Provider>
     </AuthContext.Provider>

  );
}

export default App;
