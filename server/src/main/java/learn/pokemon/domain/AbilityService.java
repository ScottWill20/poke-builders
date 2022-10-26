package learn.pokemon.domain;

import learn.pokemon.data.AbilityJdbcRepository;
import learn.pokemon.data.AbilityRepository;
import learn.pokemon.data.MoveRepository;
import learn.pokemon.models.Ability;
import learn.pokemon.models.Move;
import org.springframework.stereotype.Service;

@Service
public class AbilityService {


    private final AbilityRepository repository;

    public AbilityService(AbilityRepository abilityRepository) {
        this.repository = abilityRepository;
    }

    public Ability findByAbilityName(String abilityName) {
        return repository.findByAbilityName(abilityName);
    }
    public Ability findByAbilityId(int abilityId) {
        return repository.findByAbilityId(abilityId);
    }

    public Result<Ability> createAbility(Ability ability) {
        Result<Ability> result = validate(ability);

        if (!result.isSuccess()) {
            return result;
        }

        if (ability.getId() != 0) {
            result.addMessage("id cannot be set for `add` operation", ResultType.INVALID);
        }

        ability = repository.createAbility(ability);
        result.setPayload(ability);
        return result;
    }

    private Result<Ability> validate(Ability ability) {
        Result<Ability> result = new Result<>();
        if (ability == null) {
            result.addMessage("Ability cannot be null", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(ability.getName())) {
            result.addMessage("Ability name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(ability.getDescription())) {
            result.addMessage("Ability description is required", ResultType.INVALID);
        }
        if (ability.getName().length() > 50) {
            result.addMessage("Ability name cannot be longer than 50 characters", ResultType.INVALID);
        }
        if (ability.getDescription().length() > 2000) {
            result.addMessage("Ability description must be less than 2000 characters", ResultType.INVALID);
        }
        return result;
    }

}
