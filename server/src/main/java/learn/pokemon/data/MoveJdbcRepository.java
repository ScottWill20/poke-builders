package learn.pokemon.data;

import learn.pokemon.data.mappers.MoveMapper;
import learn.pokemon.data.mappers.PokemonMapper;
import learn.pokemon.models.Move;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MoveJdbcRepository implements MoveRepository {

    private final JdbcTemplate jdbcTemplate;

    public MoveJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Move findByMoveName(String moveName) {
        final String sql = "select move_id, move_name, move_description " +
                "from move " +
                "where move_name = ?;";

        return jdbcTemplate.query(sql, new MoveMapper(), moveName).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Move findByMoveId(int moveId) {
        final String sql = "select move_id, move_name, move_description " +
                "from move " +
                "where move_id = ?;";

        return jdbcTemplate.query(sql, new MoveMapper(), moveId).stream()
                .findFirst().orElse(null);
    }

}
