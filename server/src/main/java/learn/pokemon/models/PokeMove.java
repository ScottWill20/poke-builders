package learn.pokemon.models;

public class PokeMove {
    private int moveId;
    private int pokemonId;

    public PokeMove() {
    }

    public PokeMove(int moveId, int pokemonId) {
        this.moveId = moveId;
        this.pokemonId = pokemonId;
    }

    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }
}
