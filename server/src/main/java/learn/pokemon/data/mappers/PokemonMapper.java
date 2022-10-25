package learn.pokemon.data.mappers;

import learn.pokemon.data.AbilityJdbcRepository;
import learn.pokemon.data.AbilityRepository;
import learn.pokemon.data.UserJdbcRepository;
import learn.pokemon.data.UserRepository;
import learn.pokemon.models.Pokemon;
import learn.pokemon.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonMapper implements RowMapper<Pokemon> {
    private UserRepository userRepository;
    private AbilityRepository abilityRepository;

    @Override
    public Pokemon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(rs.getInt("pokemon_id"));
        pokemon.setName(rs.getString("pokemon_name"));
        pokemon.setHeight(rs.getDouble("height"));
        pokemon.setWeight(rs.getDouble("weight"));
        pokemon.setBirthday(rs.getDate("birthday").toLocalDate());
        pokemon.setUser(userRepository.findById(rs.getInt("app_user_id")));
        pokemon.setAbility(abilityRepository.findById(rs.getInt("ability_id")));
        return pokemon;
    }
}
