package learn.pokemon.data;

import learn.pokemon.models.Ability;

public interface AbilityRepository {
    Ability findByAbilityId(int id);
    Ability findByAbilityName(String name);

    Ability createAbility(Ability ability);
}

