package learn.pokemon.domain;

import learn.pokemon.data.PokemonRepository;
import learn.pokemon.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PokemonServiceTest {

    @Autowired
    PokemonService service;

    @MockBean
    PokemonRepository repository;

    User scott = new User();
    User jane = new User();
    ArrayList<Move> moves = new ArrayList<>();
    Ability abilty = new Ability(1,"Adaptability", "Powers up moves of the same type as the pokemon");


    @Test
    void shouldFindAllPublicPokemon() {
        List<Pokemon> expected = repository.findAllPublicPokemon();
        Pokemon snorlax = makeSnorlax();
        Pokemon jigglypuff = makeJigglypuff();
        expected.add(snorlax);
        expected.add(jigglypuff);

        when(repository.findAllPublicPokemon()).thenReturn(expected);
        List<Pokemon> actual = service.findAllPublicPokemon();
        assertEquals(expected, actual);

    }

    @Test
    void shouldFindScottsPokemon() {
        scott.setUserId(1);
        List<Pokemon> expected = repository.findByUserId(1);
        Pokemon snorlax = makeSnorlax();
        expected.add(snorlax);

        when(repository.findByUserId(1)).thenReturn(expected);

        List<Pokemon> actual = service.findByUserId(1);
        assertEquals(expected,actual);

    }

    @Test
    void shouldFindSnorlax() {

        Pokemon expected = makeSnorlax();

        when(repository.findByPokemonId(1)).thenReturn(expected);

        Pokemon actual = service.findByPokemonId(1);

        assertEquals(expected, actual);

    }

    @Test
    void shouldNotAddWhenInvalid() {
        Pokemon snorlax = makeSnorlax();
        Result<Pokemon> result = service.createPokemon(snorlax);
        assertEquals(ResultType.INVALID, result.getType());

        snorlax.setId(0);
        snorlax.setWeight(0);
        result = service.createPokemon(snorlax);
        assertEquals(ResultType.INVALID, result.getType());

        snorlax.setWeight(460);
        snorlax.setHeight(10000000);
        result = service.createPokemon(snorlax);
        assertEquals(ResultType.INVALID, result.getType());

        snorlax.setHeight(10);
        snorlax.setName("");
        result = service.createPokemon(snorlax);
        assertEquals(ResultType.INVALID, result.getType());

        snorlax.setName("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm");
        // name over 100 characters
        result = service.createPokemon(snorlax);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldAdd() {
        Pokemon snorlax = makeSnorlax();
        Pokemon mockLax = makeSnorlax();
        snorlax.setId(0);

        when(repository.createPokemon(snorlax)).thenReturn(mockLax);

        Result<Pokemon> actual = service.createPokemon(snorlax);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockLax, actual.getPayload());
    }

    @Test
    void shouldNotUpdateMissing() {
        Pokemon snorlax = makeSnorlax();
        snorlax.setId(3);

        when(repository.updatePokemon(snorlax)).thenReturn(false);
        Result<Pokemon> actual = service.updatePokemon(snorlax);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        Pokemon snorlax = makeSnorlax();
        snorlax.setWeight(0);
        Result<Pokemon> result = service.updatePokemon(snorlax);
        assertEquals(ResultType.INVALID, result.getType());

        snorlax.setWeight(460);
        snorlax.setHeight(10000000);
        result = service.updatePokemon(snorlax);
        assertEquals(ResultType.INVALID, result.getType());

        snorlax.setHeight(10);
        snorlax.setName("");
        result = service.updatePokemon(snorlax);
        assertEquals(ResultType.INVALID, result.getType());

        snorlax.setName("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm");
        // name over 100 characters
        result = service.updatePokemon(snorlax);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldUpdate() {
        Pokemon snorlax = makeSnorlax();
        snorlax.setWeight(800);

        when(repository.updatePokemon(snorlax)).thenReturn(true);
        Result<Pokemon> actual = service.updatePokemon(snorlax);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldDelete() {
        Pokemon snorlax = makeSnorlax();

        when(repository.deleteByPokemonId(snorlax.getId())).thenReturn(true);

        assertTrue(service.deleteByPokemonId(1));
    }

    Pokemon makeSnorlax() {
        Pokemon snorlax = new Pokemon();
        snorlax.setId(1);
        snorlax.setName("Snorlax");
        snorlax.setHeight(10);
        snorlax.setWeight(460);
        snorlax.setBirthday(LocalDate.now());
        snorlax.setType(Type.NORMAL);
        snorlax.setVibe(Vibe.NAUGHTY);
        snorlax.setUser(scott);
        snorlax.setMoves(moves);
        snorlax.setAbility(abilty);
        snorlax.setPrivate(false);
        return snorlax;
    }

    Pokemon makeJigglypuff() {
        Pokemon jigglypuff = new Pokemon();
        jigglypuff.setId(2);
        jigglypuff.setName("Jigglypuff");
        jigglypuff.setHeight(.5);
        jigglypuff.setWeight(5.5);
        jigglypuff.setBirthday(LocalDate.now());
        jigglypuff.setType(Type.NORMAL);
        jigglypuff.setVibe(Vibe.BASHFUL);
        jigglypuff.setUser(jane);
        jigglypuff.setMoves(moves);
        jigglypuff.setAbility(abilty);
        jigglypuff.setPrivate(false);
        return jigglypuff;
    }
}
