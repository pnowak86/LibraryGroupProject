package library.dao;

import library.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by RENT on 2017-09-07.
 */
@Repository
public class JdbcUserDao implements UserDao {
    private static String INSERT_USERS_QUERY = "INSERT INTO users (username,password,enabled) VALUES(?,?,true)";
    private static final String INSERT_ROLE_QUERY = "INSERT INTO user_roles (username,role) VALUES(?, 'ROLE_USER')";
    private static final String EXIST_USERS_QUERY = "SELECT count(*) FROM users WHERE username = ?";
    private static final String SELECT_ALL = "SELECT * FROM users u join user_roles ur on u.username = ur.username WHERE ur.username NOT IN (SELECT ur.username FROM user_roles ur where ur.role = 'ROLE_ADMIN')";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(User user) {
        String username = user.getUsername();
        if (exist(username)) {
            return false;
        }
        jdbcTemplate.update(INSERT_USERS_QUERY, username, user.getPassword());
        jdbcTemplate.update(INSERT_ROLE_QUERY, username);
        return true;

    }



    @Override
    public List<User> getAll() {
        List<User> allUsers = jdbcTemplate.query(SELECT_ALL, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                String  username = resultSet.getString("username");
                String role = resultSet.getNString("role");
                User user = new User();
                user.setUsername(username);
                user.setRole(role.substring(5));

                return user;
            }
        });

        return allUsers;
    }


    private boolean exist(String username) {
    Integer count = jdbcTemplate.queryForObject(EXIST_USERS_QUERY, Integer.class, username);
        return false;
    }}