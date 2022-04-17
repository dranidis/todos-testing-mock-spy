package com.se.todos.acceptance.rest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Paths;
import java.util.List;

import com.se.todos.acceptance.RepositoryHelper;
import com.se.todos.acceptance.TodoAppSUT;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// @RunWith(SpringJUnit4ClassRunner.class)
public class TodoAppRestSUT implements TodoAppSUT {

    // @Value("${jsonFileName}") 
    // private String fileName;
    private final String fileName = Paths.get("src", "test", "todos.json").toString();

    
    private RepositoryHelper repositoryHelper = new RepositoryHelper(fileName);

    @Autowired
	private MockMvc mockMvc;

    MvcResult mvcResult;

    @Override
    public void setUp() {
        System.out.println("SETUP REST");
        repositoryHelper.emptyRepository();
    }

    @Override
    public void startApplication() {
        // TODO Auto-generated method stub
    }

    // list tasks

    @Override
    public void listTasks() {
        try {
            mvcResult = mockMvc.perform(get("/todos")).andDo(print()).andExpect(status().isOk()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Override
    public void assertThatAllTasksAreListed(List<String> asList) {
        fail("test json returned");
    }

    @Override
    public void assertThatTaskIsAdded(String description) {
        repositoryHelper.assertThatTaskIsAdded(description);
    }

    @Override
    public void assertThatTaskIscompleted(String description) {
        boolean actual = repositoryHelper.isCompleted(description);
        assertTrue("Todo is completed:" + description, actual);
    }

    @Override
    public void cleanUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void addTaskWithDescription(String description) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fillRepositoryWithTodos(List<String> list) {
        repositoryHelper.fillRepositoryWithTodos(list);
    }

    @Override
    public void completeSecondTask(String description) {
        // TODO Auto-generated method stub

    }

    @Override
    public void assertThatTasksAreNotcompleted(List<String> descriptions) {
        descriptions.forEach(t -> assertFalse(repositoryHelper.isCompleted(t)));
    }

    @Override
    public void deleteSecondTask(String description) {
        // TODO Auto-generated method stub

    }

    @Override
    public void assertThatTaskIsDeleted(String description) {
        repositoryHelper.assertThatTaskIsDeleted(description);
    }

    @Override
    public void editSecondTask(String oldDescription, String newDescription) {
        // TODO Auto-generated method stub

    }

    @Override
    public void assertThatTaskHasChanged(String oldDescription, String newDescription) {
        repositoryHelper.assertThatTaskHasChanged(oldDescription, newDescription);
    }

    @Override
    public void searchTasks(String string) {
        // TODO Auto-generated method stub

    }

    @Override
    public void endApplication() {
        // TODO Auto-generated method stub

    }

    @Override
    public void assertThatTaskWithIdIsDeleted(int id) {
        repositoryHelper.assertThatTaskWithIdIsDeleted(String.valueOf(id));
    }

    @Override
    public void deleteSecondTaskFromList() {
        // TODO Auto-generated method stub

    }

}
