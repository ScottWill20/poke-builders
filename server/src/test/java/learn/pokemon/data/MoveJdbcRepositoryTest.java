package learn.pokemon.data;

import learn.pokemon.models.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MoveJdbcRepositoryTest {

    @Autowired
    MoveJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindMoveByName() {
       Move move = repository.findByMoveName("Facade");
       assertEquals(move.getDescription(), "This attack move doubles its power if the user is poisoned, burned, or paralyzed.");
    }

    @Test
    void shouldNotFindMoveByInvalidName() {
        Move move = repository.findByMoveName("Skull Bash");
        assertNull(move);
    }

    @Test
    void shouldFindMoveById() {
        Move move = repository.findByMoveId(2);
        assertEquals(move.getName(), "Yawn");
    }

    @Test
    void shouldNotFindMoveByInvalidId() {
        Move move = repository.findByMoveId(999);
        assertNull(move);
    }

    @Test
    void shouldCreateMove() {
        Move move = createMove();
        Move actual = repository.createMove(move);
        assertNotNull(actual);
        assertEquals(6, actual.getId());
    }

    private Move createMove() {
        Move move = new Move();
        move.setId(0);
        move.setName("Pound");
        move.setDescription("The target is physically pounded with a long tail, a foreleg, or the like.");
        return move;
    }

}
