package learn.pokemon.data.mappers;

import learn.pokemon.models.Ability;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AbilityMapper implements RowMapper<Ability> {
    @Override
    public Ability mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ability ability = new Ability();
        ability.setId(rs.getInt("ability_id"));
        ability.setName(rs.getString("ability_name"));
        ability.setDescription(rs.getString("ability_description"));
        return ability;
    }
}
