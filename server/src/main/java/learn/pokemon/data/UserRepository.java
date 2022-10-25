package learn.pokemon.data;

import learn.pokemon.models.User;

public interface UserRepository {
    User findByUsername(String name);
  
    User findById(int id);
}
