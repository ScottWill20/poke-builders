package learn.pokemon.controllers;

import learn.pokemon.domain.Result;
import learn.pokemon.models.User;
import learn.pokemon.security.JwtConverter;
import learn.pokemon.security.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    private final AuthenticationManager manager;
    private final JwtConverter converter;
    private PasswordEncoder encoder;

    private final UserService userService;

    public AuthController(AuthenticationManager manager, JwtConverter converter, PasswordEncoder encoder, UserService userService) {
        this.manager = manager;
        this.converter = converter;
        this.encoder = encoder;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        try {
            Authentication authentication = manager.authenticate(token);
            if (authentication.isAuthenticated()) {
                HashMap<String, String> body = new HashMap<>();
                String jwt = converter.getTokenFromUser((User) authentication.getPrincipal());
                body.put("jwt", jwt);
                return new ResponseEntity<>(body, HttpStatus.OK);
            }
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

//    @PostMapping("/encode")
//    public void encode(@RequestBody HashMap<String, String> values) {
//        String encodedValue = encoder.encode(values.get("password"));
//        System.out.println(encodedValue);
//    }

    // NEW
    @PostMapping("/refresh_token")
    public ResponseEntity<Map<String, String>> refreshToken(@AuthenticationPrincipal User user) {
        String jwtToken = converter.getTokenFromUser(user);

        HashMap<String, String> map = new HashMap<>();
        map.put("jwt_token", jwtToken);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createAccount(@RequestBody Map<String, String> credentials) {

        String email = credentials.get("email");
        String avatar = credentials.get("avatar");
        String username = credentials.get("username");
        String password = credentials.get("password");

        Result<User> result = userService.createUser(email, avatar, username, password);

        // unhappy path...
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }

        // happy path...
        HashMap<String, Integer> map = new HashMap<>();
        map.put("userId", result.getPayload().getUserId());

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}