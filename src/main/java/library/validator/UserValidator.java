package library.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import library.dto.User;

@Component
public class UserValidator {

    public List<ErrorField> validate(User user) {
        List<ErrorField> errorFields = new ArrayList<>();

        if (StringUtils.isEmpty(user.getUsername())) {
            errorFields.add(new ErrorField("usernameError", "Username can not be empty!"));
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            errorFields.add(new ErrorField("passwordError", "Password can not be empty!"));
        }
        if (!Objects.equals(user.getPassword(), user.getRepeatedPassword())) {
            errorFields.add(new ErrorField("repeatedPasswordError", "Passwords don't match!"));
        }
        return errorFields;
    }
}