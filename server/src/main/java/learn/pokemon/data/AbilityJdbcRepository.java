package learn.pokemon.data;

import learn.pokemon.data.mappers.AbilityMapper;
import learn.pokemon.models.Ability;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AbilityJdbcRepository implements AbilityRepository{

    private final JdbcTemplate jdbcTemplate;

    public AbilityJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Ability findById(int abilityId) {
        final String sql = "select ability_id, ability_name, ability_description " +
                "from ability " +
                "where ability_id = ?;";

        return jdbcTemplate.query(sql, new AbilityMapper(), abilityId).stream()
                .findFirst().orElse(null);
    }
}
