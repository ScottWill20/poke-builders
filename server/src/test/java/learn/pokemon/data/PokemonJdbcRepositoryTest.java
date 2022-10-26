package learn.pokemon.data;

import learn.pokemon.models.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void shouldFindAllPokemon() {
        List<Pokemon> pokemons = repository.findAllPublicPokemon();
        assertNotNull(pokemons);

        assertTrue(pokemons.size() >= 1 && pokemons.size() <= 2);
    }

    @Test
    void shouldFindByUserId() {
        //check that two users with distinct pokemon have different lists
    }

    @Test
    void shouldNotFindByInvalidUserId() {
        //unhappy path for userId find
    }

    @Test
    void shouldFindByPokemonId() {

    }

    @Test
    void shouldNotFindByPokemonId() {
        
    }
}
