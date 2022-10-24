package learn.pokemon.controllers;

//import learn.pokemon.models.AppUser;
//import learn.pokemon.security.JwtConverter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//
//@RestController
//public class AuthController {
//    private final AuthenticationManager manager;
//    private final JwtConverter converter;
//    private PasswordEncoder encoder;
//
//
//    public AuthController(AuthenticationManager manager, JwtConverter converter, PasswordEncoder encoder) {
//        this.manager = manager;
//        this.converter = converter;
//        this.encoder = encoder;
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<?> authenticate(@RequestBody AppUser user) {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//        try {
//            Authentication authentication = manager.authenticate(token);
//            if (authentication.isAuthenticated()) {
//                HashMap<String, String> body = new HashMap<>();
//                String jwt = converter.userToToken((AppUser) authentication.getPrincipal());
//                body.put("jwt", jwt);
//                return new ResponseEntity<>(body, HttpStatus.OK);
//            }
//        } catch (AuthenticationException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//    }
//
//    @PostMapping("/encode")
//    public void encode(@RequestBody HashMap<String, String> values) {
//        String encodedValue = encoder.encode(values.get("value"));
//        System.out.println(encodedValue);
//    }
//}