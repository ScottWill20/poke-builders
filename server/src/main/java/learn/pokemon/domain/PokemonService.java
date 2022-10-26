package learn.pokemon.domain;

import learn.pokemon.data.PokemonRepository;
import learn.pokemon.models.Pokemon;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository repository;
    public PokemonService(PokemonRepository repository) { this.repository = repository; }

    public static int getMaxBirthday() {return Year.now().getValue(); }

    public List<Pokemon> findAllPublicPokemon() {
        return repository.findAllPublicPokemon();
    }

    public Result<List<Pokemon>> findByUserId(int userId) {
        //if user doesn't exist, we return not found
        Result<List<Pokemon>> result = new Result<>();
        List<Pokemon> pokemonList = repository.findByUserId(userId);
        if (pokemonList == null) {
            result.addMessage(String.format("User with %s id not found", userId), ResultType.NOT_FOUND);
            return result;
        }
        result.setPayload(pokemonList);
        return result;
    }

    public Pokemon findByPokemonId(int pokemonId){
        return repository.findByPokemonId(pokemonId);
    }

    public Result<Pokemon> createPokemon(Pokemon pokemon) {
        Result<Pokemon> result = validate(pokemon);

        if (!result.isSuccess()) {
            return result;
        }

        if (pokemon.getId() != 0) {
            result.addMessage("id cannot be set for `add` operation", ResultType.INVALID);
        }

        pokemon = repository.createPokemon(pokemon);
        result.setPayload(pokemon);
        return result;

    }

    public Result<Pokemon> updatePokemon(Pokemon pokemon) {
        Result<Pokemon> result = validate(pokemon);

        if (!result.isSuccess()) {
            return result;
        }

        if (pokemon.getId() <= 0) {
            result.addMessage("pokemonId must be set for `update` operation", ResultType.INVALID);
        }

        if (!repository.updatePokemon(pokemon)) {
            String msg = String.format("id: %s, not found", pokemon.getId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteByPokemonId(int pokemonId) {
        return repository.deleteByPokemonId(pokemonId);
    }


    private Result<Pokemon> validate(Pokemon pokemon) {
        Result<Pokemon> result = new Result<>();
        if (pokemon == null) {
            result.addMessage("Pokemon cannot be null", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(pokemon.getName())) {
            result.addMessage("name is required", ResultType.INVALID);
        }
        if (pokemon.getName().length() > 100) {
            result.addMessage("name cannot be longer than 100 characters", ResultType.INVALID);
        }
        if (pokemon.getHeight() == 0 || pokemon.getHeight() > 9999999) {
            result.addMessage("height cannot be zero and must be less than 10,000,000", ResultType.INVALID);
        }
        if (pokemon.getWeight() == 0 || pokemon.getWeight() > 9999999) {
            result.addMessage("weight cannot be zero and must be less than 10,000,000", ResultType.INVALID);
        }
        return result;
    }
}
