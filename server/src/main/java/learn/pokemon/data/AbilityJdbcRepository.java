package learn.pokemon.data;

import learn.pokemon.data.mappers.AbilityMapper;
import learn.pokemon.models.Ability;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class AbilityJdbcRepository implements AbilityRepository{

    private final JdbcTemplate jdbcTemplate;

    public AbilityJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Ability findByAbilityId(int abilityId) {
        final String sql = "select ability_id, ability_name, ability_description " +
                "from ability " +
                "where ability_id = ?;";

        return jdbcTemplate.query(sql, new AbilityMapper(), abilityId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Ability findByAbilityName(String abilityName) {
        final String sql = "select ability_id, ability_name, ability_description " +
                "from ability " +
                "where ability_name = ?;";

        return jdbcTemplate.query(sql, new AbilityMapper(), abilityName).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Ability createAbility(Ability ability) {
        final String sql = "insert into ability (ability_id, ability_name, ability_description) "
                + "values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ability.getId());
            ps.setString(2, ability.getName());
            ps.setString(3, ability.getDescription());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        ability.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return ability;
    }
}
