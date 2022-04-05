package com.se.todos;

import org.junit.Test;

public class AcceptanceIT {

    private TodoAppSUT todoApp = new TodoAppSUT();

    @Test
    public void AddTaskTest() {
        todoApp.startApplication();
        todoApp.addTaskWithDescription("A new task");
        todoApp.assertThatTaskIsAdded("A new task");
    }
    
}
