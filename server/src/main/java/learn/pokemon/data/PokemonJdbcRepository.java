package learn.pokemon.data;

import learn.pokemon.models.Pokemon;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PokemonJdbcRepository implements PokemonRepository {
    private final JdbcTemplate jdbcTemplate;

    public PokemonJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pokemon> findAllPublicPokemon() {
        final String sql = "select * from pokemon where private = false;";


        return null;
    }

    @Override
    public List<Pokemon> findByUserId() {
        return null;
    }

    @Override
    public Pokemon findByPokemonId(int pokemonId) {
        return null;
    }

    @Override
    public Pokemon createPokemon(Pokemon pokemon) {
        return null;
    }

    @Override
    public boolean updatePokemon(Pokemon pokemon) {
        return false;
    }

    @Override
    public boolean deleteByPokemonId(int pokemonId) {
        return false;
    }
}
