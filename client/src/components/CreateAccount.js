
function CreateAccount() {
    return (
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
        </form>
    );

}

export default CreateAccount;