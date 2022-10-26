package learn.pokemon.data.mappers;

import learn.pokemon.models.Move;
import learn.pokemon.models.PokeMove;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PokeMoveMapper implements RowMapper<PokeMove> {
    @Override
    public PokeMove mapRow(ResultSet rs, int rowNum) throws SQLException {
        PokeMove pokeMove = new PokeMove();
        pokeMove.setPokemonId(rs.getInt("pokemon_id"));
        pokeMove.setMoveId(rs.getInt("move_id"));
        return pokeMove;
    }
}
