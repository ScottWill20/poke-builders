package learn.pokemon.data;

import learn.pokemon.models.Move;

public interface MoveRepository {
    Move findByMoveName(String moveName);

    Move findByMoveId(int moveId);

}
