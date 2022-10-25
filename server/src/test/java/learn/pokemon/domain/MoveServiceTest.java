package learn.pokemon.domain;

import learn.pokemon.data.MoveRepository;
import learn.pokemon.data.PokemonRepository;
import learn.pokemon.models.Move;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MoveServiceTest {

    @Autowired
    MoveService service;

    @MockBean
    MoveRepository repository;

    @Test
    void shouldFindFlamethrowerByName() {
        Move expected = makeFlamethrower();
        when(repository.findByMoveName("Flamethrower")).thenReturn(expected);
        Move actual = service.findByMoveName("Flamethrower");

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindFlamethrowerById() {
        Move expected = makeFlamethrower();
        when(repository.findByMoveId(1)).thenReturn(expected);
        Move actual = service.findByMoveId(1);

        assertEquals(expected, actual);

    }


    Move makeFlamethrower() {
        Move move = new Move();
        move.setId(1);
        move.setName("Flamethrower");
        move.setDescription("The target is scorched with an intense blast of fire. This may also leave the target with a burn.");
        return move;
    }
}
