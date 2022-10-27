
function PokeCard() { // pass in pokemon name, username, and img as props
    return (
        <>
        <div className="container poke-card">
            <h3>Name</h3>
            <img className="poke-picture" src="https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png" alt="Bulbasaur"></img>
            <h4>@username</h4>
        </div>
        </>
    );
}

export default PokeCard;