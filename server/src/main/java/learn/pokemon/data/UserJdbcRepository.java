package learn.pokemon.data;

import learn.pokemon.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository{
    private  final JdbcTemplate jdbcTemplate;
    RowMapper<User> mapper = (ResultSet rs, int rowIndex) -> {
        User user = new User();
        user.setUserId(rs.getInt("app_user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setEnabled(rs.getBoolean("enabled"));
        return user;
    };

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select app_user_id, username, password_hash, enabled "
                + "from app_user "
                + "where username = ?;";
        User user = jdbcTemplate.query(sql, mapper, username).stream().findFirst().orElse(null);
        if (user != null) {
            attachAuthorities(user);
        }
        return user;
    }
   @Override
    public User findById(int id) {
        String sql = "select app_user_id, username, password_hash, enabled "
                + "from app_user "
                + "where app_user_id = ?;";
        User user = jdbcTemplate.query(sql, mapper, id).stream().findFirst().orElse(null);
        if (user != null) {
            attachAuthorities(user);
        }
        return user;
    }

    private void attachAuthorities(User user) {
        String sql = "select ar.`name` "
                + "from app_user_role aur "
                + "inner join app_role ar on aur.app_role_id = ar.app_role_id "
                + "where aur.app_user_id = ?;";
        List<GrantedAuthority> authorities = jdbcTemplate.query(sql, (ResultSet rs, int index) -> {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rs.getString("name"));
            return authority;
        }, user.getUserId());
        user.setAuthorities(authorities);
    }
}
