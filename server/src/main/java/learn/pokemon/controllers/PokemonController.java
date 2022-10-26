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

    @GetMapping("/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable int userId) {
        //we need a not found.
        // this should only be accessible by the specific user. How do we do that?
        Result<List<Pokemon>> result = service.findByUserId(userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }

}
