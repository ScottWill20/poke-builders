package learn.pokemon.data.mappers;

import learn.pokemon.data.AbilityJdbcRepository;
import learn.pokemon.data.AbilityRepository;
import learn.pokemon.data.UserJdbcRepository;
import learn.pokemon.data.UserRepository;
import learn.pokemon.models.Pokemon;
import learn.pokemon.models.Type;
import learn.pokemon.models.User;
import learn.pokemon.models.Vibe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonMapper implements RowMapper<Pokemon> {
    private final UserRepository userRepository;
    private final AbilityRepository abilityRepository;

    public PokemonMapper(UserRepository userRepository, AbilityRepository abilityRepository) {
        this.userRepository = userRepository;
        this.abilityRepository = abilityRepository;
    }

    @Override
    public Pokemon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(rs.getInt("pokemon_id"));
        pokemon.setName(rs.getString("pokemon_name"));
        pokemon.setHeight(rs.getDouble("height"));
        pokemon.setWeight(rs.getDouble("weight"));
        pokemon.setBirthday(rs.getDate("birthday").toLocalDate());
        pokemon.setUser(userRepository.findById(rs.getInt("app_user_id")));
        pokemon.setAbility(abilityRepository.findByAbilityId(rs.getInt("ability_id")));
        pokemon.setType(Type.getTypeByName(rs.getString("type")));
        pokemon.setVibe(Vibe.getVibeByName(rs.getString("vibe")));
        pokemon.setPrivate(rs.getBoolean("private"));
        return pokemon;
    }
}
