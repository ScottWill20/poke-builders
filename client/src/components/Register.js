import { useState } from 'react';
import { Link } from 'react-router-dom';
import { registerUser } from '../services/auth';
import ErrorList from './ErrorList';
import { useHistory } from "react-router-dom";
import SuccessMessage from './SuccessMessage';

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
  const [successMessage, setSuccessMessage] = useState('');
  const history = useHistory();

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

    registerUser(credentials)
      .then(data => {
        if (Array.isArray(data)) {
          setErrors(data);
        } else {
          // setSuccessMessage('Registration successful!');
          history.push("/login");
        }
      })
      .catch(console.error);
  };

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
          <div className="mb-3">
            <label htmlFor="favPokemon" className="form-label">Favorite Pokemon</label>
            <input
              type="text"
              name="favPokemon"
              value={credentials.favPokemon}
              onChange={handleChange}
              className="form-control"
              id="favPokemon"
              placeholder="Favorite Pokemon"
              required />
          </div>
          <div className="mb-3">
            <button type="submit" className="btn btn-primary m-3">Save</button>
            <Link to="/" className="btn btn-secondary m-3">Cancel</Link>
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