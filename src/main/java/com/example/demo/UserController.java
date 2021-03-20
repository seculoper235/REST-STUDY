package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

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
        System.out.println("사용자 ID = " + user.getId() + "\n사용자 이름 = " + user.getName() + "\n사용자 설명 = " + user.getDescription());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newuser = userService.createUser(user);
        URI createUri = linkTo(methodOn(UserController.class).createUser(newuser)).slash(user.getId()).toUri();

        return ResponseEntity.created(createUri).body(newuser);
    }
}
