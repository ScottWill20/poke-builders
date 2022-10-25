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

        // can't predict order
        // if delete is first, we're down to 7
        // if add is first, we may go as high as 10
        assertTrue(pokemons.size() >= 7 && pokemons.size() <= 10);
    }
}
