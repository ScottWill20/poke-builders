package learn.pokemon.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import learn.pokemon.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtConverter {

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final String ISSUER = "poke-builder";
    private final int EXPIRATION_MINUTES = 15;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;

    // Take an instance of `AppUser` as a parameter, instead of `UserDetails`
    public String getTokenFromUser(User user) {

        String authorities = user.getAuthorities().stream()
                .map(i -> i.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getEmail())
                // new... embed the `appUserId` in the JWT as a claim
                .claim("app_user_id", user.getUserId())
                .claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(key)
                .compact();
    }

    // Return an instance of `AppUser`
    public User getUserFromToken(String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.substring(7));

            String email = jws.getBody().getSubject();
            // new... read the `appUserId` from the JWT body
            int userId = (int)jws.getBody().get("app_user_id");
            String avatar = (String) jws.getBody().get("avatar");
            String username = (String) jws.getBody().get("username");
            String authStr = (String) jws.getBody().get("authorities");

            // Replace the Spring Security `User` with our `AppUser`
            return new User(userId, email, avatar, username, null,true
                    ,Arrays.asList(authStr.split(",")));

        } catch (JwtException e) {
            System.out.println(e);
        }

        return null;
    }
}
