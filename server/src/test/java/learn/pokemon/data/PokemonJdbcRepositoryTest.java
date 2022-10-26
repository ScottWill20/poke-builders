package learn.pokemon.data;

import learn.pokemon.models.Pokemon;
import learn.pokemon.models.Type;
import learn.pokemon.models.User;
import learn.pokemon.models.Vibe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
    void shouldCreatePokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Jolteon");
        pokemon.setHeight(0.8);
        pokemon.setWeight(24.5);
        pokemon.setBirthday(LocalDate.of(2018, 5, 21));
        pokemon.setUser(makeUser());
        pokemon.setType(Type.ELECTRIC);
    }

    @Test
    void shouldUpdatePokemon() {

    }

    @Test
    void shouldDeletePokemon() {

    }

    private User makeUser() {
       User user = new User();
       user.setUserId(3);
       return user;
    }
}
