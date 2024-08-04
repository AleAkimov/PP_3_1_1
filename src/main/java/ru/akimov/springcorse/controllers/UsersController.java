package ru.akimov.springcorse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.akimov.springcorse.model.User;
import ru.akimov.springcorse.service.UserService;
@Validated
@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "users_d";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "new_d";
    }

    @PostMapping()
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_d";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUsers(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "edit_d";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_d";
        }
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
            userService.delete(id);
        return "redirect:/users";
    }




    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
            StringBuilder errors = new StringBuilder();
            ex.getBindingResult().getFieldErrors().forEach(error -> {
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            });
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}