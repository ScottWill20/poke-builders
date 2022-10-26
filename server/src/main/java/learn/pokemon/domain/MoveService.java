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

    public Result<Move> createMove(Move move) {
        Result<Move> result = validate(move);

        if (!result.isSuccess()) {
            return result;
        }

        if (move.getId() != 0) {
            result.addMessage("id cannot be set for `add` operation", ResultType.INVALID);
        }

        move = repository.createMove(move);
        result.setPayload(move);
        return result;
    }

    private Result<Move> validate(Move move) {
        Result<Move> result = new Result<>();
        if (move == null) {
            result.addMessage("Move cannot be null", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(move.getName())) {
            result.addMessage("Move name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(move.getDescription())) {
            result.addMessage("Move description is required", ResultType.INVALID);
        }
        if (move.getName().length() > 100) {
            result.addMessage("Move name cannot be longer than 100 characters", ResultType.INVALID);
        }
        if (move.getDescription().length() > 500) {
            result.addMessage("move description must be less than 500 characters", ResultType.INVALID);
        }
        return result;
    }

}
