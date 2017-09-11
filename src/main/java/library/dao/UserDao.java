package library.dao;

import library.dto.User;

import java.util.List;
/**
 * Created by RENT on 2017-09-07.
 */
public interface UserDao {
    boolean create(User user);

    List<User> getAll();
}
