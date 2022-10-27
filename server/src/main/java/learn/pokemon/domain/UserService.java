package learn.pokemon.domain;

import learn.pokemon.data.UserJdbcRepository;
import learn.pokemon.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserJdbcRepository repository;

    public UserService(UserJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null || !user.isEnabled()) {
            throw  new UsernameNotFoundException("username" + username + "not found");
        }
        return user;
    }
}
