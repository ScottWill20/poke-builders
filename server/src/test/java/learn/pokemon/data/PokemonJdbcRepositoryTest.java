package learn.pokemon.data;

import learn.pokemon.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PokemonJdbcRepositoryTest {
    final static int NEXT_ID = 9;

    @Autowired
    PokemonJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllPublicPokemon() {
        List<Pokemon> pokemons = repository.findAllPublicPokemon();
        assertNotNull(pokemons);

        assertTrue(pokemons.size() >= 2 && pokemons.size() <= 3);
        for (Pokemon p: pokemons) {
            assertFalse(p.isPrivate());
        }
    }

    @Test
    void shouldFindByUserId() {
        //check that two users with distinct pokemon have different lists
        List<Pokemon> pokemons1 = repository.findByUserId(1);
        assertNotNull(pokemons1);
        assertTrue(pokemons1.size() >= 3);
        for (Pokemon p: pokemons1) {
            assertEquals(1, p.getUser().getUserId());
        }

        List<Pokemon> pokemons2 = repository.findByUserId(2);
        assertNotNull(pokemons2);
        assertTrue(pokemons2.size() >= 1);
        for (Pokemon p: pokemons2) {
            assertEquals(2, p.getUser().getUserId());
        }
    }

    @Test
    void shouldNotFindByInvalidUserId() {
        List<Pokemon> pokemons = repository.findByUserId(999);
        assertNull(pokemons); //change to assertNull
    }

    @Test
    void shouldFindByPokemonId() {
        Pokemon pokemon = repository.findByPokemonId(1);
        assertNotNull(pokemon);
        assertEquals("Snorlax", pokemon.getName());
        assertEquals(10, pokemon.getHeight());
        assertEquals(460, pokemon.getWeight());
        assertEquals(LocalDate.of(2012, 12, 25), pokemon.getBirthday());
        assertEquals(1, pokemon.getUser().getUserId());
        assertEquals("Cheek Pouch", pokemon.getAbility().getName());
        assertEquals(Type.NORMAL, pokemon.getType());
        assertEquals(Vibe.NAUGHTY, pokemon.getVibe());
        assertFalse(pokemon.isPrivate());
        assertEquals(4, pokemon.getMoves().size());
        assertEquals("Facade", pokemon.getMoves().get(0).getName());
        assertEquals("Yawn", pokemon.getMoves().get(1).getName());
        assertEquals("Body Slam", pokemon.getMoves().get(2).getName());
        assertEquals("Flamethrower", pokemon.getMoves().get(3).getName());
    }

    @Test
    void shouldNotFindByPokemonId() {
        Pokemon pokemon = repository.findByPokemonId(999);
        assertNull(pokemon);
    }

    @Test
    void shouldCreatePokemonWithNewMovesAndAbility() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Jolteon");
        pokemon.setHeight(0.8);
        pokemon.setWeight(24.5);
        pokemon.setBirthday(LocalDate.of(2018, 5, 21));
        pokemon.setUser(makeUser());
        pokemon.setType(Type.ELECTRIC);
        pokemon.setVibe(Vibe.BRAVE);
        pokemon.setPrivate(true);
        List<Move> moveList = new ArrayList<>();
        moveList.add(new Move(0, "Yawn","The user lets loose a huge yawn that lulls the target into falling asleep on the next turn."));
        moveList.add(new Move(0, "Thunderbolt", "A strong electric blast crashes down on the target. This may also leave the target with paralysis."));
        moveList.add(new Move(0, "Charm", "The user gazes at the target rather charmingly, making it less wary. This harshly lowers the target’s Attack stat."));
        moveList.add(new Move(0, "Rain-Dance", "The user summons a heavy rain that falls for five turns, powering up Water-type moves. It lowers the power of Fire-type moves."));
        pokemon.setMoves(moveList);
        Ability ability = new Ability(0, "Volt-Absorb", "Restores HP if hit by an Electric-type move instead of taking damage.");
        pokemon.setAbility(ability);

        Pokemon created = repository.createPokemon(pokemon);
        assertNotNull(created);
        assertEquals("Jolteon", created.getName());
        for (Move m : created.getMoves()) {
            assertNotEquals(0, m.getId());
        }
        assertNotEquals(0, created.getAbility().getId());
    }

    @Test
    void shouldCreatePokemonUsingExistingMovesAndAbilities() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Omanyte");
        pokemon.setHeight(0.4);
        pokemon.setWeight(7.5);
        pokemon.setBirthday(LocalDate.of(2018, 5, 21));
        pokemon.setUser(makeUser());
        pokemon.setType(Type.ROCK);
        pokemon.setVibe(Vibe.BRAVE);
        pokemon.setPrivate(true);
        pokemon.setMoves(getExistingMoves());
        pokemon.setAbility(new Ability(0, "Water Absorb", "Restores HP if hit by a Water-type move instead of taking damage."));
        Pokemon created = repository.createPokemon(pokemon);
        assertNotNull(created);
        assertEquals("Omanyte", created.getName());
        for (Move m : created.getMoves()) {
            assertNotEquals(0, m.getId());
        }
        assertNotEquals(0, created.getAbility().getId());
    }

    @Test
    void shouldUpdatePokemon() {
        //("Vaporeon", 1, 29, "2020-09-01", 1, 3, "Water", "Jolly", true)
        //change birthday and private and vibe.
        Pokemon pokemon = new Pokemon();
        pokemon.setId(4);
        pokemon.setName("Vaporeon");
        pokemon.setHeight(1);
        pokemon.setWeight(29);
        pokemon.setBirthday(LocalDate.now());
        User user = new User();
        user.setUserId(1);
        pokemon.setUser(user);
        pokemon.setType(Type.WATER);
        pokemon.setVibe(Vibe.BRAVE);
        pokemon.setPrivate(false);
        pokemon.setMoves(getExistingMoves());
        pokemon.setAbility(new Ability(0, "Adaptability", "Powers up moves of the same type as the pokemon"));

        assertTrue(repository.updatePokemon(pokemon));
    }

    @Test
    void shouldDeletePokemon() {
        assertTrue(repository.deleteByPokemonId(3));
        assertFalse(repository.deleteByPokemonId(3));
    }

    private User makeUser() {
       User user = new User();
       user.setUserId(2);
       return user;
    }

    private List<Move> getExistingMoves() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(0, "Body Slam","The user drops onto the target with its full body weight. This may also leave the target with paralysis."));
        moves.add(new Move(0, "Flamethrower", "The target is scorched with an intense blast of fire. This may also leave the target with a burn."));
        moves.add(new Move(0, "Yawn","The user lets loose a huge yawn that lulls the target into falling asleep on the next turn."));
        moves.add(new Move(0, "Charm", "The user gazes at the target rather charmingly, making it less wary. This harshly lowers the target’s Attack stat."));
        return moves;
    }
}
