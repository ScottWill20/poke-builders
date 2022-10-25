package learn.pokemon.data;

import learn.pokemon.models.Ability;

public interface AbilityRepository {
    Ability findById(int id);
}
}
