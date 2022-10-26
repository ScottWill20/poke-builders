package learn.pokemon.domain;

import learn.pokemon.data.MoveRepository;
import learn.pokemon.data.PokemonRepository;
import learn.pokemon.models.Move;
import learn.pokemon.models.Pokemon;
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

    @Test
    void shouldCreateFlamethrower() {
        Move move = makeFirePunch();
        move.setId(0);
        Move mockMove = new Move();

        when(repository.createMove(move)).thenReturn(mockMove);

        Result<Move> actual = service.createMove(move);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockMove, actual.getPayload());
    }

    @Test
    void shouldNotCreateInvalidMove() {
        Move move = makeFirePunch();
        // invalid create id
        Result<Move> result = service.createMove(move);
        assertEquals(ResultType.INVALID, result.getType());

        move.setId(0);
        move.setName("");
        // move name is required
        result = service.createMove(move);
        assertEquals(ResultType.INVALID, result.getType());

        move.setName("FirePunchFirePunchFirePunchFirePunchFirePunchFirePunchFirePunchFirePunchFirePunchFirePunchFirePunchFirePunch");
        // name over 100 characters
        result = service.createMove(move);
        assertEquals(ResultType.INVALID, result.getType());

        move.setName("Fire Punch");
        move.setDescription("The target is punched with a fiery fist. This may also leave the target with a burn.The target is punched with a fiery fist. This may also leave the target with a burn.The target is punched with a fiery fist. This may also leave the target with a burn.The target is punched with a fiery fist. This may also leave the target with a burn.The target is punched with a fiery fist. This may also leave the target with a burn.The target is punched with a fiery fist. This may also leave the target with a burn.");
        // description over 500 characters
        result = service.createMove(move);
        assertEquals(ResultType.INVALID, result.getType());

        move.setDescription("");
        // description is required
        result = service.createMove(move);
        assertEquals(ResultType.INVALID, result.getType());
    }


    Move makeFlamethrower() {
        Move move = new Move();
        move.setId(1);
        move.setName("Flamethrower");
        move.setDescription("The target is scorched with an intense blast of fire. This may also leave the target with a burn.");
        return move;
    }

    Move makeFirePunch() {
        Move move = new Move();
        move.setId(2);
        move.setName("Fire Punch");
        move.setDescription("The target is punched with a fiery fist. This may also leave the target with a burn.");
        return move;
    }
}
