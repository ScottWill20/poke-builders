package learn.pokemon.domain;

import learn.pokemon.data.PokemonRepository;
import learn.pokemon.models.Pokemon;
import learn.pokemon.models.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PokemonServiceTest {

    @Autowired
    PokemonService service;

    @MockBean
    PokemonRepository repository;


    @Test
    void shouldFindAllPublicPokemon() {}

    @Test
    void shouldFindScottsPokemon() {}

    @Test
    void shouldFindSnorlax() {}

    @Test
    void shouldNotAddWhenInvalid() {}

    @Test
    void shouldAdd() {}

    @Test
    void shouldNotUpdateMissing() {}

    @Test
    void shouldNotUpdateWhenInvalid() {}

    @Test
    void shouldUpdate() {}

    @Test
    void shouldNotDeleteWhenInvalid() {}

    @Test
    void shouldDelete() {}

//    Pokemon makePokemon() {
//        Pokemon pokemon = new Pokemon();
//        pokemon.setId(1);
//        pokemon.setName("Party Animal");
//        pokemon.setHeight(1.2);
//        pokemon.setWeight(6);
//        pokemon.setBirthday(LocalDate.now());
//        pokemon.setType(Type.FIRE);
//    }


}
