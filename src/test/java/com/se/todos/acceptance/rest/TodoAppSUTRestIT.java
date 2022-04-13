package com.se.todos.acceptance.rest; 

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.se.todos.acceptance.RepositoryHelper;
import com.se.todos.controller.TodoAppController;




import static org.hamcrest.Matchers.*;


@WebMvcTest
public class TodoAppSUTRestIT {
    
    // @Autowired
    // private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;


    private final String fileName = Paths.get("src", "test", "todos.json").toString();

    private RepositoryHelper repositoryHelper = new RepositoryHelper(fileName);

    @Before
    public void setUp() {
        System.out.println("SETUP");
        repositoryHelper.emptyRepository();

        List<String> list = Arrays.asList("Task 1", "Task 2", "Task 3");

        repositoryHelper.fillRepositoryWithTodos(list);

    }

    @Test
    void listTasks() throws Exception {
        // TodoAppController welcomeController = new TodoAppController();
        mockMvc.perform(get("/todos")).andDo(print()).andExpect(status().isOk());
        // assertEquals("Welcome John!", welcomeController.welcome("John"));
    }

    // @BeforeEach
    // public void setup() throws Exception {
    //     this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    // }

    // @Test
    // void shouldWelcomeRest() throws Exception {
    //     // WelcomeController welcomeController = new WelcomeController();
    //     // assertEquals("Welcome John!", welcomeController.welcome("John"));
    //     mockMvc.perform(get("/welcome")).andDo(print()).andExpect(status().isOk())
    //             .andExpect(content().string(equalTo("Welcome Jim! asf")));

    // }



}
