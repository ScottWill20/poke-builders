import { useState, useEffect, React } from 'react';
import { Link } from 'react-router-dom';
import { registerUser } from '../services/auth';
import ErrorList from './ErrorList';
import { useHistory } from "react-router-dom";
import SuccessMessage from './SuccessMessage';
import Select from "react-select";
import { getPokemonSprites, ListPokemonNames } from "../services/pokeAPI";

function Register() {
  const [credentials, setCredentials] = useState({
    email: '',
    username: '',
    password: '',
    confirmPassword: '',
    favPokemon: '',
    avatar: ''
  });
  const [errors, setErrors] = useState([]);
  const [pokemon, setPokemon] = useState([]);
  const [successMessage, setSuccessMessage] = useState('');
  const history = useHistory();

  useEffect(() => {
    ListPokemonNames()
    .then(setPokemon)
    .catch(console.log());
    
});

  const handleChange = (evt) => {
    const nextCredentials = { ...credentials };
    nextCredentials[evt.target.name] = evt.target.value;
    setCredentials(nextCredentials);
  };

  const handleSubmit = (evt) => {
    evt.preventDefault();

    if (credentials.password !== credentials.confirmPassword) {
      setErrors(['Passwords do not match.'])
      return;
    }

    console.log(credentials.favPokemon.value.toLowerCase());

    getPokemonSprites(credentials.favPokemon.value.toLowerCase())
    .then(imageURL => {
      const temp = {...credentials}
      temp.avatar = imageURL;

      delete temp.favPokemon;

      registerUser(temp)
      .then(data => {
        if (Array.isArray(data)) {
          setErrors(data);
        } else {
          // setSuccessMessage('Registration successful!');
          history.push("/login");
        }
      })
      .catch(console.error);
  })
}
 

  //   registerUser(credentials)
  //     .then(data => {
  //       if (Array.isArray(data)) {
  //         setErrors(data);
  //       } else {
  //         // setSuccessMessage('Registration successful!');
  //         history.push("/login");
  //       }
  //     })
  //     .catch(console.error);
  // };

  return <>
    <section className="form container-fluid">
      {/* {successMessage && <SuccessMessage message={successMessage} redirect="/login" linkText="Login" />} */}
      <ErrorList errors={errors} />
      {!successMessage && <div>
        <form onSubmit={handleSubmit}>
        <div className="mb-3">
            <label htmlFor="email" className="form-label">Email</label>
            <input
              type="email"
              name="email"
              value={credentials.email}
              onChange={handleChange}
              className="form-control"
              id="email"
              placeholder="Email"
              required />
          </div>
          <div className="mb-3">
            <label htmlFor="username" className="form-label">Username</label>
            <input
              type="text"
              name="username"
              value={credentials.username}
              onChange={handleChange}
              className="form-control"
              id="username"
              placeholder="Username"
              required />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input
              type="password"
              name="password"
              value={credentials.password}
              onChange={handleChange}
              className="form-control"
              id="password"
              placeholder="Password"
              required />
          </div>
          <div className="mb-3">
            <label htmlFor="confirmPassword" className="form-label">Confirm Password</label>
            <input
              type="password"
              name="confirmPassword"
              value={credentials.confirmPassword}
              onChange={handleChange}
              className="form-control"
              id="confirmPassword"
              placeholder="Confirm password"
              required />
          </div>
          <div className="form-group">
              <label htmlFor="favPokemon">Favorite Pokemon:</label>
              <Select 
                  placeholder={".Select..."}
                  name="favPokemon"
                  options = {pokemon.map(pokemon => {
                      return (
                          { value: pokemon, label: pokemon }
                      )
                  })}
                  className="basic-multi-select"
                  classNamePrefix="select"
                  onChange={(o) => credentials.favPokemon=o}
                  id="favPokemon"
                  required />
          </div>
          <div className="mb-3">
            <button type="submit" className="nes-btn is-primary">Save</button>
            <Link to="/" className="nes-btn">Cancel</Link>
          </div>
        </form>
        <div className="alert alert-info">
          Already have an account? Log in <Link to="/login">here</Link>.
        </div>
      </div>}
    </section>
  </>;
}

export default Register;