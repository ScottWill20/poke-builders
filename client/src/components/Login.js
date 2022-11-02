import {Link, useHistory} from "react-router-dom";
import {useState, useContext} from "react";
import {authenticate} from "../services/auth";
import AuthContext from "../contexts/AuthContext";

function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [hasErr, setHassErr] = useState(false);

    const {login} = useContext(AuthContext);
    const history = useHistory();

    const handleEmailChange = (evt) => setEmail(evt.target.value);
    const handlePasswordChange = (evt) => setPassword(evt.target.value);

    function handleSubmit(evt) {
        evt.preventDefault();

        const user = {
            email,
            password
        };
        authenticate(user)
            .then(u => {
                if (Array.isArray(u)) {
                    setHassErr(true);
                } else {
                login(u);
                console.log("What is this?", u);
                history.push("/profile");
            }})
            .catch(() => setHassErr(true));
    }

    return (
        <>
        <form className="login-container" onSubmit={handleSubmit}>
            <div className="form-group">
                <label htmlFor="email" className="form-label">Email:</label>
                <input type="text" id="email" name="email"  className="form-control"
                value={email} onChange={handleEmailChange}></input>
            </div>
            <div className="form-group">
                <label htmlFor="password" className="form-label">Password:</label>
                <input type="password" id="password" name="password" className="form-control"
                value={password} onChange={handlePasswordChange}></input>
            </div>
            {hasErr && <div className="alert alert-danger">
                Bad Credentials. :(
            </div>}
            <div>
                <button className="nes-btn is-primary mb-3" type="submit">Login</button>
                <Link className="nes-btn is-warning mb-3" to="/">Cancel</Link>
            </div>
            <div className="alert alert-info">
                 Don't have an account? Create one <Link to="/register">here</Link>.
            </div>
        </form>

      </>
    );
}

export default Login;