package learn.pokemon.controllers;

import learn.pokemon.domain.PokemonService;
import learn.pokemon.domain.Result;
import learn.pokemon.models.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/pokemon")
public class PokemonController {
    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pokemon> findAllPublicPokemon() {
        return service.findAllPublicPokemon();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findPokemonByUserId(@PathVariable int userId) {
        //we need a not found.
        // this should only be accessible by the specific user. How do we do that?
        Result<List<Pokemon>> result = service.findPokemonByUserId(userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }

    @GetMapping("/{pokemonId}")
    public ResponseEntity<?> findByPokemonId(@PathVariable int pokemonId) {
        Pokemon pokemon = service.findByPokemonId(pokemonId);
        if (pokemon == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pokemon);
    }

    @PostMapping
    public ResponseEntity<?> createPokemon(@RequestBody Pokemon pokemon) {
        Result<Pokemon> result = service.createPokemon(pokemon);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{pokemonId}")
    public ResponseEntity<?> updatePokemon(@PathVariable int pokemonId, @RequestBody Pokemon pokemon) {
        if (pokemonId != pokemon.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Pokemon> result = service.updatePokemon(pokemon);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<Void> deleteByPokemonId(@PathVariable int pokemonId) {
        if (service.deleteByPokemonId(pokemonId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
