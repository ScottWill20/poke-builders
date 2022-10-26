package learn.pokemon.data;

import learn.pokemon.data.mappers.AbilityMapper;
import learn.pokemon.data.mappers.MoveMapper;
import learn.pokemon.data.mappers.PokemonMapper;
import learn.pokemon.models.Ability;
import learn.pokemon.models.Move;
import learn.pokemon.models.Pokemon;
import learn.pokemon.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PokemonJdbcRepository implements PokemonRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final AbilityRepository abilityRepository;

    public PokemonJdbcRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository, AbilityRepository abilityRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.abilityRepository = abilityRepository;
    }

    @Override
    public List<Pokemon> findAllPublicPokemon() {
        final String sql = "select pokemon_id, pokemon_name, height, weight, birthday, " +
                "app_user_id, ability_id, `type`, vibe " +
                "from pokemon " +
                "where private = false;";
        return jdbcTemplate.query(sql, new PokemonMapper(userRepository, abilityRepository));
    }

    @Override
    public List<Pokemon> findByUserId(int userId) {
        final String sql = "select pokemon_id, pokemon_name, height, weight, birthday, " +
                "app_user_id, ability_id, `type`, vibe, private " +
                "from pokemon " +
                "where app_user_id = ?;";
        return jdbcTemplate.query(sql, new PokemonMapper(userRepository, abilityRepository), userId);
    }

    @Override
    public Pokemon findByPokemonId(int pokemonId) {
        final String sql = "select pokemon_id, pokemon_name, height, weight, birthday, " +
                "app_user_id, ability_id, `type`, vibe, private " +
                "from pokemon " +
                "where pokemon_id = ?;";
        Pokemon pokemon = jdbcTemplate.query(sql, new PokemonMapper(userRepository, abilityRepository), pokemonId).stream()
                .findFirst().orElse(null);

        if (pokemon != null) {
            attachMoves(pokemon);
        }

        return pokemon;
    }

    @Override
    public Pokemon createPokemon(Pokemon pokemon) {
        final String sql = "insert into pokemon (pokemon_id, pokemon_name, height, weight, birthday, " +
                "app_user_id, ability_id, `type`, vibe, private) "
                + " values (?,?,?,?,?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pokemon.getId());
            ps.setString(2, pokemon.getName());
            ps.setDouble(3, pokemon.getHeight());
            ps.setDouble(4, pokemon.getWeight());
            ps.setDate(5, pokemon.getBirthday() == null ? null : Date.valueOf(pokemon.getBirthday()));
            ps.setInt(6, pokemon.getUser().getUserId());
            ps.setInt(7, pokemon.getAbility().getId());
            ps.setString(8, pokemon.getType().getName());
            ps.setString(9, pokemon.getVibe().getName());
            ps.setBoolean(10, pokemon.isPrivate());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        pokemon.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return pokemon;
    }

    @Override
    public boolean updatePokemon(Pokemon pokemon) {
        final String sql = "update pokemon set "
                + "pokemon_id = ?, "
                + "pokemon_name = ?, "
                + "height = ?, "
                + "weight = ?, "
                + "birthday = ? "
                + "app_user_id = ? "
                + "ability_id = ? "
                + "`type` = ? "
                + "vibe = ? "
                + "private = ? "
                + "where pokemon_id = ?;";

        return jdbcTemplate.update(sql,
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getHeight(),
                pokemon.getWeight(),
                pokemon.getBirthday(),
                pokemon.getUser().getUserId(),
                pokemon.getAbility().getId(),
                pokemon.getType().getName(),
                pokemon.getVibe().getName(),
                pokemon.isPrivate()) > 0;
    }

    @Override
    public boolean deleteByPokemonId(int pokemonId) {
        jdbcTemplate.update("delete from poke_move where pokemon_id = ?;", pokemonId);
        return jdbcTemplate.update("delete from pokemon where pokemon_id = ?;", pokemonId) > 0;
    }

    //attachMoves
    private void attachMoves(Pokemon pokemon) {
        final String sql = "select m.move_id, m.move_name, m.move_description "
                + "from move m "
                + "inner join poke_move pm on pm.move_id = m.move_id "
                + "inner join pokemon p on pm.pokemon_id = p.pokemon_id "
                + "where pm.pokemon_id = ?;";
        List<Move> moves = jdbcTemplate.query(sql, new MoveMapper(), pokemon.getId());
        pokemon.setMoves((ArrayList<Move>) moves);
    }
}
