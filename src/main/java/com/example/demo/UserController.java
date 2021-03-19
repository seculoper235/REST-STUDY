package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "<h1>Hello World!</h1>";
    }

    @GetMapping("/finduser/{id}")
    public ResponseEntity<User> findUser(@PathVariable Integer id) {
        User user = userService.findUserById(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        String name = user.getName();
        user.setName(name);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newuser = userService.createUser(user);
        URI createUri = linkTo(methodOn(UserController.class).createUser(newuser)).slash(user.getId()).toUri();

        return ResponseEntity.created(createUri).body(newuser);
    }
}
