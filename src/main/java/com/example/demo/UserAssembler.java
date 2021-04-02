package com.example.demo;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/* RepresentationModelAssembler란?
 * RepresentationModel(엔티티모델, 컬렉션모델)을 사용할 때 생기는 중복 코드를 방지하기 위한 인터페이스이다.
 * 해당 인터페이스를 구현한 클래스를 Bean 등록하면, toModel()이란 메소드로 중복 코드를 깔끔하게 작성할 수 있다.
 */
@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User user) {
        EntityModel<User> newuser = EntityModel.of(user
                , linkTo(methodOn(UserController.class).findUser(user.getId())).withSelfRel()
                , linkTo(UserController.class).withRel("create-user")
                , linkTo(UserController.class).slash(user.getId()).withRel("update-user")
                , linkTo(methodOn(UserController.class).deleteUser(user.getId())).withRel("delete-user")
                , linkTo(UserController.class).withRel("users"));
        return newuser;
    }
}
