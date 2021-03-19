package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testHome() throws Exception {
        String msg = "<h1>Hello World!</h1>";

        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(msg));
    }

    @Test
    public void testCreateUser() throws Exception {
        User newuser = new User(1, "테스트", "개발이 취미인 수학자");

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(newuser)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testSelectUser() throws Exception {
        mockMvc.perform(get("/user/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
