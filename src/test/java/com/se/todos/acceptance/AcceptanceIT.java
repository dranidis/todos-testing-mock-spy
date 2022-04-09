package com.se.todos.acceptance;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.se.todos.acceptance.CLI.TodoAppSUTCLI;
import com.se.todos.acceptance.console.TodoAppConsoleUISUT;

@RunWith(value = Parameterized.class)
public class AcceptanceIT {
    private TodoAppSUT todoAppSUT;

    public AcceptanceIT(TodoAppSUT todoAppSUT) {
        this.todoAppSUT = todoAppSUT;
    }

    @Parameters(name = "{index}: ui({0})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new TodoAppSUTCLI() },
                { new TodoAppConsoleUISUT() }
        });
    }

    @Before
    public void emptyRepository() {
        todoAppSUT.setUp();
    }

    @After
    public void cleanUp() {
        todoAppSUT.cleanUp();
    }

    @Test
    public void addsANewTask() {
        // When
        todoAppSUT.startApplication();
        todoAppSUT.addTaskWithDescription("A new task");
        todoAppSUT.endApplication();

        // Then
        todoAppSUT.assertThatTaskIsAdded("A new task");
    }

    // @Test
    // public void showsAllTasks() {
    //     // Given
    //     todoAppSUT.fillRepositoryWithTodos(Arrays.asList("Task 1", "Task 2", "Task 3"));

    //     // When
    //     todoAppSUT.startApplication();
    //     todoAppSUT.listTasks();
    //     todoAppSUT.endApplication();

    //     // Then
    //     todoAppSUT.assertThatAllTasksAreListed(Arrays.asList("Task 1", "Task 2", "Task 3"));
    // }

    @Test
    public void completeSecondTask() {
        // Given
        todoAppSUT.fillRepositoryWithTodos(Arrays.asList("Task 1", "Task 2", "Task 3"));

        // When
        todoAppSUT.startApplication();
        todoAppSUT.completeSecondTask("Task 2");
        todoAppSUT.endApplication();

        // Then
        todoAppSUT.assertThatTaskIscompleted("Task 2");
        todoAppSUT.assertThatTasksAreNotcompleted(Arrays.asList("Task 1", "Task 3"));
    }
    
    @Test
    public void deleteATask() {
        // Given
        todoAppSUT.fillRepositoryWithTodos(Arrays.asList("Task 1", "Task 2", "Task 3"));

        // When
        todoAppSUT.startApplication();
        todoAppSUT.deleteSecondTask("Task 2");
        todoAppSUT.endApplication();

        // Then
        todoAppSUT.assertThatTaskIsDeleted("Task 2");
    }

    @Test
    public void editATask() {
        // Given
        todoAppSUT.fillRepositoryWithTodos(Arrays.asList("Task 1", "Task 2", "Task 3"));

        // When
        todoAppSUT.startApplication();
        todoAppSUT.editSecondTask("Task 2", "Task 2 edited");
        todoAppSUT.endApplication();

        // Then
        todoAppSUT.assertThatTaskHasChanged("Task 2", "Task 2 edited");
    }



    // @Test
    // public void deleteATask_SameDescriptions() {
    //     // Given
    //     todoAppSUT.fillRepositoryWithTodos(Arrays.asList("Task 1", "Task 1", "Task 1"));

    //     // When
    //     todoAppSUT.startApplication();
        
    //     todoAppSUT.searchTasks("Task 1");
    //     todoAppSUT.deleteSecondTaskFromList("Task 1");

    //     // Then
    //     todoAppSUT.assertThatTaskSecondTaskIsDeleted("Task 1");
    // }

}
