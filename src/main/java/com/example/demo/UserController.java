package com.example.demo;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
/* consumes와 produces란?
 * REST API를 좀더 Type Safe하게 만들어주는 것으로써,
 * consumes는 요청 헤더의 Conten-Type이 해당 미디어 타입과 일치할 때, 즉 올바른 타입일 경우만 매핑하고,
 * produces는 요청 헤더의 Accept에 해당 미디어 타입이 존재할 때, 즉 응답을 받을 수 있을 때 매핑이 된다
 */
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home() {
        return "<h1>Hello World!</h1>";
    }

    @Transactional(readOnly = true)
    @GetMapping("/finduser/{id}")
    public ResponseEntity<User> findUser(@PathVariable Integer id) {
        User user = userService.findUserById(id);

        return ResponseEntity.ok(user);
    }

    @Transactional(readOnly = true)
    @GetMapping("/getuser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        System.out.println("사용자 ID = " + user.getId() + "\n사용자 이름 = " + user.getName() + "\n사용자 설명 = " + user.getDescription());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> findUserAll() {
        // Entity 각각의 출력할 데이터 설정
        List<EntityModel<User>> userList = userService.findUserAll().stream()
                .map(user -> EntityModel.of(user
                        , linkTo(methodOn(UserController.class).findUser(user.getId())).withSelfRel()
                        , linkTo(methodOn(UserController.class).findUserAll()).withRel("users")))
                .collect(Collectors.toList());

        // 전체 list의 출력할 데이터 설정
        return CollectionModel.of(userList,
                linkTo(methodOn(UserController.class).findUserAll()).withSelfRel());
    }

    @Transactional(readOnly = true)
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newuser = userService.createUser(user);
        URI createUri = linkTo(methodOn(UserController.class).createUser(newuser)).slash(user.getId()).toUri();

        return ResponseEntity.created(createUri).body(newuser);
    }

    /* PUT과 DELETE?
     * 두 메소드는 한국 인터넷 진흥원의 보안 가이드를 보면, GET/POST 외의 불필요한 메소드는 보안에 취약하니 사용하지 말라고 쓰여 있다.
     * 하지만 실제로 꼭 필요해서 사용하는 메소드는 절대 불필요하지 않으므로, 사용에 대해선 논란이 많은 듯 하다.
     * 우선 해당 레포지토리는 공부 목적이므로 RESTful을 충실히 이행하도록 한다.
     * (하지만 DELETE 메소드는 정말 좋지 않으니, 절대 사용하지 않도록 한다!)
     */
    @PutMapping("/user/{id}")
    public EntityModel<User> updateUser(@RequestBody User user, @PathVariable Integer id) {
        user.setId(id);
        User result = userService.updateUser(user);

        return EntityModel.of(result,
                linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).findUserAll()).withRel("users"));
    }

    /* DELETE 메소드는 기본 차단되므로, POST 메소드로 변경한다.
     * POST 메소드의 의가 생성 이외에도 리소스를 변경한다는 뜻도 있기에 의미적으로도 더 가깝고,
     * 변경 이후의 Location도 제공하므로 더욱 적합하다.
     * GET 메소드는 requestbody가 없다는 점에서 DELETE 메소드와 가장 흡사하나, 의미가 너무 동떨어지므로 적합하지 않다고 생각한다
     */
    @PostMapping("user/{id}/delete")
    public ResponseEntity<Link> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);

        return ResponseEntity.ok().body(linkTo(methodOn(UserController.class).findUserAll()).withRel("users"));
    }
}
