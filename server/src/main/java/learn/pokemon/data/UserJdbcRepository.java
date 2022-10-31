package learn.pokemon.data;

import learn.pokemon.data.mappers.UserMapper;
import learn.pokemon.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public User findByEmail(String email) {
        List<String> roles = getRolesByEmail(email);


        final String sql = "select app_user_id, email, avatar, username, password_hash, enabled "
                + "from app_user "
                + "where email = ?;";
//        User user = jdbcTemplate.query(sql, new UserMapper(roles), username).stream().findFirst().orElse(null);
//        if (user != null) {
//            attachAuthorities(user);
//        }
        return jdbcTemplate.query(sql, new UserMapper(roles), email)
                .stream().findFirst().orElse(null);
    }
  
   @Override
    public User findById(int id) {
       List<String> roles = getRolesById(id);

       String sql = "select app_user_id, email, avatar, username, password_hash, enabled "
                + "from app_user "
                + "where app_user_id = ?;";

       return jdbcTemplate.query(sql, new UserMapper(roles), id)
               .stream().findFirst().orElse(null);
    }

    @Override
    @Transactional
    public User createUser(User user) {

        final String sql = "insert into app_user (email, avatar, username, password_hash) " +
                "values (?, ?, ?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getAvatar());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());

        updateRoles(user);

        return user;

    }

//    @Override
//    @Transactional
//    public boolean update(User user) {
//
//        final String sql = "update app_user set "
//                + "username = ?, "
//                + "enabled = ? "
//                + "where app_user_id = ?";
//
//        boolean updated = jdbcTemplate.update(sql,
//                user.getUsername(), user.isEnabled(), user.getUserId()) > 0;
//
//        if (updated) {
//            updateRoles(user);
//        }
//
//        return updated;
//    }
//
    private void updateRoles(User user) {
        // delete all roles, then re-add
        jdbcTemplate.update("delete from app_user_role where app_user_id = ?;", user.getUserId());

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities == null) {
            return;
        }

        for (GrantedAuthority role : authorities) {
            String sql = "insert into app_user_role (app_user_id, app_role_id) "
                    + "select ?, app_role_id from app_role where `name` = ?;";
            jdbcTemplate.update(sql, user.getUserId(), role.getAuthority());
        }
    }
//
    private List<String> getRolesByEmail(String email) {
        final String sql = "select r.name "
                + "from app_user_role ur "
                + "inner join app_role r on ur.app_role_id = r.app_role_id "
                + "inner join app_user au on ur.app_user_id = au.app_user_id "
                + "where au.email = ?";
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), email);
    }

    private List<String> getRolesById(int id) {
        final String sql = "select r.name "
                + "from app_user_role ur "
                + "inner join app_role r on ur.app_role_id = r.app_role_id "
                + "inner join app_user au on ur.app_user_id = au.app_user_id "
                + "where au.app_user_id = ?";
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), id);
    }

//    private void attachAuthorities(User user) {
//        String sql = "select ar.`name` "
//                + "from app_user_role aur "
//                + "inner join app_role ar on aur.app_role_id = ar.app_role_id "
//                + "where aur.app_user_id = ?;";
//        List<GrantedAuthority> authorities = jdbcTemplate.query(sql, (ResultSet rs, int index) -> {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rs.getString("name"));
//            return authority;
//        }, user.getUserId());
//        user.setAuthorities(authorities);
//    }
}
