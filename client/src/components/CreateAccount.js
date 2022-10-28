
function CreateAccount() {
    return (
        <div className="create-account-container">
        <form>
            <div className="form-group">
                <label htmlFor="email">Email:</label>
                <input id="email"
                name="email"
                type="email"
                className="form-control"
                required
                />
            </div>
            <div className="form-group">
                <label htmlFor="username">Username:</label>
                <input id="username"
                name="username"
                type="text"
                className="form-control"
                required
                />
            </div>
            <div className="form-group">
            <label htmlFor="password">Password:</label>
                <input id="password"
                name="password"
                type="text"
                className="form-control"
                required
                />
            </div>
            <div className="form-group">
            <label htmlFor="fav-pokemon">Favorite Pokemon:</label>
                <input id="search-fav-pokemon"
                name="search-fav-pokemon"
                className="form-control"
                type="search"
                placeholder="Search..."
                />
                <select id="fav-pokemon"
                name="fav-pokemon"
                className="form-control"
                required
                >
                    {/* need to map api fetch options here to fill out options list */}
                    <option>Bulbasaur</option>
                    <option>Charizard</option>
                    <option>Eevee</option>
                    <option>Pikachu</option>
                </select>
            </div>
        </form>
        </div>
    );

}

export default CreateAccount;