package library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import library.config.UserResponse;
import library.dto.User;
import library.service.UserService;
import library.validator.ErrorField;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, Model model) {
        UserResponse userResponse = userService.create(user);
        boolean success = userResponse.isSuccess();
        model.addAttribute("success", success);
        model.addAttribute("register", !success);
        if (!success) {
            putMessages(model, userResponse.getErrorMessage());
        }
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerHome(Model model) {
        model.addAttribute("success", true);
        model.addAttribute("register", true);
        return "register";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "users";
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String change(@ModelAttribute User user, Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "users";
    }

    private void putMessages(Model model, List<ErrorField> errorFields) {
        errorFields.forEach(errorField -> model.addAttribute(errorField.getKey(), errorField.getMessage()));
    }
}
