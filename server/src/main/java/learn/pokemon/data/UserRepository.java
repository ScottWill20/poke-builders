package learn.pokemon.data;

import learn.pokemon.models.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository {
    User findByEmail(String name);

    User findById(int id);

    @Transactional
    User createUser(User user);
}
