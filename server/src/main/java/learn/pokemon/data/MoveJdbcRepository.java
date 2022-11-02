package learn.pokemon.data;

import learn.pokemon.data.mappers.MoveMapper;
import learn.pokemon.models.Move;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

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

    @Override
    public Move createMove(Move move) {
        final String sql = "insert into move (move_name, move_description) "
                + "values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, move.getName());
            ps.setString(2, move.getDescription());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        move.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return move;
    }
}
