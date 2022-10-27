package learn.pokemon.security;

import learn.pokemon.data.UserJdbcRepository;
import learn.pokemon.domain.Result;
import learn.pokemon.domain.ResultType;
import learn.pokemon.domain.Validations;
import learn.pokemon.models.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserJdbcRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserJdbcRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user == null || !user.isEnabled()) {
            throw  new UsernameNotFoundException("username" + email + "not found");
        }
        return user;
    }

    public Result<User> createUser(String email, String avatar, String username, String password) {
        Result<User> result = validate(email, avatar, username, password);
        if (!result.isSuccess()) {
            return result;
        }

        password = encoder.encode(password);

        User user = new User(0, email, avatar, username, password, true, List.of("USER"));

        try {
            user = repository.createUser(user);
            result.setPayload(user);
        } catch (DuplicateKeyException e) {
            result.addMessage("The provided username already exists", ResultType.INVALID);
        }
        return result;
    }

    private Result<User> validate(String email, String avatar, String username, String password) {
        Result<User> result = new Result<>();

        if (Validations.isNullOrBlank(email)) {
            result.addMessage("email is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(password)) {
            result.addMessage("password is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(username)) {
            result.addMessage("username is required", ResultType.INVALID);
        }
        if (username.length() > 50) {
            result.addMessage("username must be less than 50 characters", ResultType.INVALID);
        }

        if (Validations.isValidPassword(password)) {
            result.addMessage("password must be at least 12 characters and contain a digit," +
                    " a letter, and a non-digit/non-letter", ResultType.INVALID);
        }
        return result;
    }
}
