package learn.pokemon.domain;

import learn.pokemon.data.AbilityJdbcRepositoryTest;
import learn.pokemon.data.AbilityRepository;
import learn.pokemon.data.MoveRepository;
import learn.pokemon.models.Ability;
import learn.pokemon.models.Move;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AbilityServiceTest {
    @Autowired
    AbilityService service;

    @MockBean
    AbilityRepository repository;

    @Test
    void shouldFindBatteryByName() {
        Ability expected = makeBattery();
        when(repository.findByAbilityName("Battery")).thenReturn(expected);
        Ability actual = service.findByAbilityName("Battery");

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindDisguiseById() {
        Ability expected = makeDisguise();
        when(repository.findByAbilityId(2)).thenReturn(expected);
        Ability actual = service.findByAbilityId(2);

        assertEquals(expected, actual);

    }

    @Test
    void shouldCreateBattery() {
        Ability ability = makeBattery();
        ability.setId(0);
        Ability mockAbility = new Ability();

        when(repository.createAbility(ability)).thenReturn(mockAbility);

        Result<Ability> actual = service.createAbility(ability);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockAbility, actual.getPayload());
    }

    @Test
    void shouldNotCreateInvalidMove() {
        Ability ability = makeDisguise();
        // invalid create id
        Result<Ability> result = service.createAbility(ability);
        assertEquals(ResultType.INVALID, result.getType());

        ability.setId(0);
        ability.setName("");
        // move name is required
        result = service.createAbility(ability);
        assertEquals(ResultType.INVALID, result.getType());

        ability.setName("DisguiseDisguiseDisguiseDisguiseDisguiseDisguiseDisguise");
        // name over 50 characters
        result = service.createAbility(ability);
        assertEquals(ResultType.INVALID, result.getType());

        ability.setName("Disguise");
        ability.setDescription("Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.Once per battle, the shroud that covers the Pokemon can protect it from an attack.");
        // description over 2000 characters
        result = service.createAbility(ability);
        assertEquals(ResultType.INVALID, result.getType());

        ability.setDescription("");
        // description is required
        result = service.createAbility(ability);
        assertEquals(ResultType.INVALID, result.getType());
    }


    Ability makeBattery() {
        Ability ability = new Ability();
        ability.setId(1);
        ability.setName("Battery");
        ability.setDescription("Powers up ally Pokemon's special moves.");
        return ability;
    }

    Ability makeDisguise() {
        Ability ability = new Ability();
        ability.setId(2);
        ability.setName("Disguise");
        ability.setDescription("Once per battle, the shroud that covers the Pokemon can protect it from an attack.");
        return ability;
    }
}
