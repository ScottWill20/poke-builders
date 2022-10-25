package learn.pokemon.domain;

import learn.pokemon.data.MoveRepository;
import learn.pokemon.models.Move;
import org.springframework.stereotype.Service;

@Service
public class MoveService {

    private final MoveRepository repository;

    public MoveService(MoveRepository moveRepository) {
        this.repository = moveRepository;
    }

    public Move findByMoveName(String moveName) {
        return repository.findByMoveName(moveName);
    }
    public Move findByMoveId(int moveId) {
        return repository.findByMoveId(moveId);
    }
}
