package com.se.todos;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AcceptanceIT {

    // private TodoAppSUT todoApp = new TodoAppSUTCLI();
    private TodoAppSUT todoApp = new TodoAppConsoleUISUT();

    @Before
    public void emptyRepository() {
        todoApp.setUp();
    }

    @After
    public void cleanUp() {
        todoApp.cleanUp();
    }

    @Test
    public void addsANewTask() {
        // When
        todoApp.startApplication();
        todoApp.addTaskWithDescription("A new task");

        // Then
        todoApp.assertThatTaskIsAdded("A new task");
    }

    @Test
    public void showsAllTasks() {
        // Given
        todoApp.fillRepositoryWithTodos(Arrays.asList("Task 1", "Task 2", "Task 3"));

        // When
        todoApp.startApplication();
        todoApp.listTasks();

        // Then
        todoApp.assertThatAllTasksAreListed(Arrays.asList("Task 1", "Task 2", "Task 3"));
    }

    @Test
    public void completeATodo() {
        // Given
        todoApp.fillRepositoryWithTodos(Arrays.asList("Task 1", "Task 2", "Task 3"));

        // When
        todoApp.startApplication();
        todoApp.completeTask("Task 2");

        // Then
        todoApp.assertThatTaskIscompleted("Task 2");
        todoApp.assertThatTasksAreNotcompleted(Arrays.asList("Task 1", "Task 3"));
    }

}
