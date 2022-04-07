package com.se.todos;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class AcceptanceIT {
    private TodoAppSUT todoAppSut;
    // private TodoAppSUT todoAppSut = new TodoAppSUTCLI();
    // private TodoAppSUT todoApp = new TodoAppConsoleUISUT();

    public AcceptanceIT(TodoAppSUT todoAppSUT) {
        this.todoAppSut = todoAppSUT;
    }

    @Parameters(name = "{index}: test({0})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new TodoAppSUTCLI() },
                { new TodoAppConsoleUISUT() }
        });
    }

    @Before
    public void emptyRepository() {
        todoAppSut.setUp();
    }

    @After
    public void cleanUp() {
        todoAppSut.cleanUp();
    }

    @Test
    public void addsANewTask() {
        // When
        todoAppSut.startApplication();
        todoAppSut.addTaskWithDescription("A new task");

        // Then
        todoAppSut.assertThatTaskIsAdded("A new task");
    }

    @Test
    public void showsAllTasks() {
        // Given
        todoAppSut.fillRepositoryWithTodos(Arrays.asList("Task 1", "Task 2", "Task 3"));

        // When
        todoAppSut.startApplication();
        todoAppSut.listTasks();

        // Then
        todoAppSut.assertThatAllTasksAreListed(Arrays.asList("Task 1", "Task 2", "Task 3"));
    }

    @Test
    public void completeSecondTask() {
        // Given
        todoAppSut.fillRepositoryWithTodos(Arrays.asList("Task 1", "Task 2", "Task 3"));

        // When
        todoAppSut.startApplication();
        todoAppSut.completeSecondTask("Task 2");

        // Then
        todoAppSut.assertThatTaskIscompleted("Task 2");
        todoAppSut.assertThatTasksAreNotcompleted(Arrays.asList("Task 1", "Task 3"));
    }

}
