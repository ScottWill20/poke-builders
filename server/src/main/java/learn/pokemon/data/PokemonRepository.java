package learn.pokemon.data;

import learn.pokemon.models.Pokemon;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PokemonRepository {
    List<Pokemon> findAllPublicPokemon();
    List<Pokemon> findPokemonByUserId(int userId);
    Pokemon findByPokemonId(int pokemonId);
    Pokemon createPokemon(Pokemon pokemon);
    boolean updatePokemon(Pokemon pokemon);
    @Transactional
    boolean deleteByPokemonId(int pokemonId);

}
