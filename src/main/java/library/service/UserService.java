package library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.config.UserResponse;
import library.dao.UserDao;
import library.dto.User;
import library.validator.ErrorField;
import library.validator.UserValidator;

@Service
public class UserService {

    private UserValidator userValidator;
    private UserDao userDao;

    @Autowired
    public UserService(UserValidator userValidator, UserDao userDao) {
        this.userValidator = userValidator;
        this.userDao = userDao;
    }

    public UserResponse create(User user) {
        List<ErrorField> errorFields = userValidator.validate(user);
        if (!errorFields.isEmpty()) {
            return new UserResponse(errorFields);
        }
        boolean success = userDao.create(user);
        return success ? new UserResponse()
                : new UserResponse(new ErrorField("usernameError", user.getUsername() + " already exist!"));
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }
}
