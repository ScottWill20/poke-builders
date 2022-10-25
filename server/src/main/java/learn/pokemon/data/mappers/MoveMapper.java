package learn.pokemon.data.mappers;

import learn.pokemon.models.Move;
import learn.pokemon.models.Pokemon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveMapper implements RowMapper<Move> {

    @Override
    public Move mapRow(ResultSet rs, int rowNum) throws SQLException {
        Move move = new Move();
        move.setId(rs.getInt("move_id"));
        move.setName(rs.getString("move_name"));
        move.setDescription(rs.getString("move_description"));
        return move;
    }

}
