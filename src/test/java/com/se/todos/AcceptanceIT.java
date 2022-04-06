package com.se.todos;

import org.junit.Before;
import org.junit.Test;

public class AcceptanceIT {

    private TodoAppSUT todoApp = new TodoAppSUT();

    @Before
    public void emptyJSONFile() {
        todoApp.emptyJSONFile();
    }

    @Test
    public void AddTaskTest() {
        todoApp.startApplication();
        todoApp.addTaskWithDescription("A new task");
        todoApp.assertThatTaskIsAdded("A new task");
    }
    
}
