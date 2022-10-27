package learn.pokemon.data;

import learn.pokemon.data.mappers.MoveMapper;
import learn.pokemon.data.mappers.PokemonMapper;
import learn.pokemon.models.Ability;
import learn.pokemon.models.Move;
import learn.pokemon.models.PokeMove;
import learn.pokemon.models.Pokemon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PokemonJdbcRepository implements PokemonRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final AbilityRepository abilityRepository;
    private final MoveRepository moveRepository;

    public PokemonJdbcRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository, AbilityRepository abilityRepository, MoveRepository moveRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.abilityRepository = abilityRepository;
        this.moveRepository = moveRepository;
    }

    @Override
    public List<Pokemon> findAllPublicPokemon() {
        final String sql = "select pokemon_id, pokemon_name, height, weight, birthday, " +
                "app_user_id, ability_id, `type`, vibe, private " +
                "from pokemon " +
                "where private = false;";
        return jdbcTemplate.query(sql, new PokemonMapper(userRepository, abilityRepository));
    }

    @Override
    public List<Pokemon> findPokemonByUserId(int userId) {
        //return null if user doesn't exist. return empty list if user exists
        if (userRepository.findById(userId) == null)
            return null;
        final String sql = "select pokemon_id, pokemon_name, height, weight, birthday, " +
                "app_user_id, ability_id, `type`, vibe, private " +
                "from pokemon " +
                "where app_user_id = ?;";
        return jdbcTemplate.query(sql, new PokemonMapper(userRepository, abilityRepository), userId);
    }

    @Override
    @Transactional
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
    @Transactional
    public Pokemon createPokemon(Pokemon pokemon) {
//        if (userRepository.findById(pokemon.getUser().getUserId()) == null)
//            return null;
        storeMovesAndAbility(pokemon);
        Pokemon result = createPokemonHelper(pokemon);
        updatePokeMove(result);
        return result;
    }

    private Pokemon createPokemonHelper(Pokemon pokemon) {
        //just do the create here.
        final String sql = "insert into pokemon (pokemon_name, height, weight, birthday, " +
                "app_user_id, ability_id, `type`, vibe, private) "
                + " values (?,?,?,?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pokemon.getName());
            ps.setDouble(2, pokemon.getHeight());
            ps.setDouble(3, pokemon.getWeight());
            ps.setDate(4, pokemon.getBirthday() == null ? null : Date.valueOf(pokemon.getBirthday()));
            ps.setInt(5, pokemon.getUser().getUserId());
            ps.setInt(6, pokemon.getAbility().getId());
            ps.setString(7, pokemon.getType().getName());
            ps.setString(8, pokemon.getVibe().getName());
            ps.setBoolean(9, pokemon.isPrivate());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        pokemon.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return pokemon;
    }

    @Override
    @Transactional
    public boolean updatePokemon(Pokemon pokemon) {
        storeMovesAndAbility(pokemon);
        final String sql = "update pokemon set "
                + "pokemon_name = ?, "
                + "height = ?, "
                + "weight = ?, "
                + "birthday = ?, "
                + "app_user_id = ?, "
                + "ability_id = ?, "
                + "`type` = ?, "
                + "vibe = ?, "
                + "private = ? "
                + "where pokemon_id = ?;";
        boolean updatePokemon = jdbcTemplate.update(sql,
                pokemon.getName(),
                pokemon.getHeight(),
                pokemon.getWeight(),
                pokemon.getBirthday(),
                pokemon.getUser().getUserId(),
                pokemon.getAbility().getId(),
                pokemon.getType().getName(),
                pokemon.getVibe().getName(),
                pokemon.isPrivate(),
                pokemon.getId()) > 0;

        return updatePokemon && updatePokeMove(pokemon);
    }



    @Override
    @Transactional
    public boolean deleteByPokemonId(int pokemonId) {
        //only delete poke_move data and pokemon data
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
        pokemon.setMoves(moves);
    }

    private void storeMovesAndAbility(Pokemon pokemon) {
        pokemon.setMoves(storeMoves(pokemon.getMoves()));
        pokemon.setAbility(storeAbility(pokemon.getAbility()));
    }

    private List<Move> storeMoves(List<Move> moves) {
        for (int i = 0; i < moves.size(); i++) {
            Move inList = moveRepository.findByMoveName(moves.get(i).getName());
            if (inList == null) {
                //add move to move table
                moves.set(i, moveRepository.createMove(moves.get(i)));
            }
            else {
                moves.get(i).setId(inList.getId());
            }
        } //at this point, all moves exist in move table
        return moves;
    }

    private Ability storeAbility(Ability ability) {
        Ability inList = abilityRepository.findByAbilityName(ability.getName());
        if (inList == null) {
            //add ability to ability table
            ability = abilityRepository.createAbility(ability);
        }
        else {
            ability.setId(inList.getId());
        }
        return ability;
    }


    private boolean updatePokeMove(Pokemon pokemon) {
        jdbcTemplate.update("delete from poke_move where pokemon_id = ?;", pokemon.getId());
        final String sql = "insert into poke_move (pokemon_id, move_id) " +
                " values (?, ?);";


        for (Move m: pokemon.getMoves()) {
            PokeMove pokeMove = new PokeMove(pokemon.getId(), m.getId());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, pokeMove.getPokemonId());
                ps.setInt(2, pokeMove.getMoveId());
                return ps;
            }, keyHolder);

            if (rowsAffected <= 0) {
                return false;
            }
        }
        return true;
    }
}
