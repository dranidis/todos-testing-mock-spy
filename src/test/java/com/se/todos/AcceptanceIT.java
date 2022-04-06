package com.se.todos;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AcceptanceIT {

    private TodoAppSUT todoApp = new TodoAppSUT();

    @Before
    public void emptyJSONFile() {
        todoApp.emptyJSONFile();
        todoApp.captureSystemOutput();
    }

    @Test
    public void addsANewTask() {
        todoApp.startApplication();
        todoApp.addTaskWithDescription("A new task");
        todoApp.assertThatTaskIsAdded("A new task");
    }

    
    @Test
    public void showsAllTasks() {
        todoApp.fillRepositoryWithSomeTasks(Arrays.asList("Task 1", "Task 2", "Task 3"));

        todoApp.startApplication();
        todoApp.listTasks();
        todoApp.assertThatAllTasksAreListed(Arrays.asList("[ ] Task 1", "[ ] Task 2", "[ ] Task 3"));
    }

    @After
    public void cleanUp() {
        todoApp.restoreSystemOutput();
    }
}
