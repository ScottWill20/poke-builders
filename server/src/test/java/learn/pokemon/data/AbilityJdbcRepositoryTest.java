package learn.pokemon.data;

import learn.pokemon.models.Ability;
import learn.pokemon.models.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AbilityJdbcRepositoryTest {

    @Autowired
    AbilityJdbcRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAbilityByName() {
        Ability ability = repository.findByAbilityName("Adaptability");
        assertEquals(ability.getDescription(), "Powers up moves of the same type as the pokemon");
    }

    @Test
    void shouldNotFindAbilityByInvalidName() {
        Ability ability = repository.findByAbilityName("Ability Test");
        assertNull(ability);
    }

    @Test
    void shouldFindAbilityById() {
        Ability ability = repository.findByAbilityId(2);
        assertEquals(ability.getName(), "Cheek Pouch");
    }

    @Test
    void shouldNotFindMoveByInvalidId() {
        Ability ability = repository.findByAbilityId(999);
        assertNull(ability);
    }

    @Test
    void shouldCreateAbility() {
        Ability ability = createAbility();
        Ability actual = repository.createAbility(ability);
        assertNotNull(actual);
        assertEquals(4, actual.getId());
    }

    private Ability createAbility() {
        Ability ability = new Ability();
        ability.setId(0);
        ability.setName("Bad Dreams");
        ability.setDescription("Reduces the HP of sleeping opposing Pokemon.");
        return ability;
    }
}
