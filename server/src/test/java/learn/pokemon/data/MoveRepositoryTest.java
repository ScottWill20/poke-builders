package learn.pokemon.data;

import learn.pokemon.models.Move;
import learn.pokemon.models.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MoveRepositoryTest {

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
    void shouldNotFindByInvalidName() {
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

}
