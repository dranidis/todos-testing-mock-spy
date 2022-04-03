package com.se.todos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

/**
 * Unit test
 */

// Examples of Mockito syntax

// TodoRepository mockSensor = Mockito.mock(TodoRepository.class);
// Mockito.when(mockTodoRepository.getTodos()).thenReturn(todos);
//
// verify a method is called once
//
// Mockito.verify(mockSensor).getTemperature();
//
// verify no method was called
//
// Mockito.verifyNoInteractions(mockTodoRepository);


// Spies are actual objects
//
// stubbing methods of spy objects
//
// Mockito.doReturn(false).when(spyTodoApp).isValidDescription("");

//
// getting arguments from stub calls
//
// ArgumentCaptor<Todo> argumentCaptor = ArgumentCaptor.forClass(Todo.class);
// Mockito.verify(mockTodoRepository).saveTask(argumentCaptor.capture());
//
// ... and their value
// Todo capturedArgument = argumentCaptor.getValue();

public class TodoAppTest {

    @Test
    public void showTasks_OneTask() {
        TodoRepository mockTodoRepository = Mockito.mock(TodoRepository.class);

        List<Todo> todos = new ArrayList<Todo>(Arrays.asList(new Todo("Buy groceries")));
        Mockito.when(mockTodoRepository.getTodos()).thenReturn(todos);

        TodoApp todoApp = new TodoApp(mockTodoRepository);

        String output = todoApp.showTasks();
        assertTrue(output.contains("Buy groceries"));
    }

    @Test
    public void showTasks_Two_Tasks() {
        TodoRepository mockTodoRepository = Mockito.mock(TodoRepository.class);
        List<Todo> todos = new ArrayList<Todo>(Arrays.asList(new Todo("Buy groceries"), new Todo("Study SE")));
        Mockito.when(mockTodoRepository.getTodos()).thenReturn(todos);

        TodoApp todoApp = new TodoApp(mockTodoRepository);

        String output = todoApp.showTasks();
        assertTrue(output.contains("Buy groceries"));
        assertTrue(output.contains("Study SE"));
    }

    @Test
    public void createTask_EmptyString() {
        TodoRepository mockTodoRepository = Mockito.mock(TodoRepository.class);
        TodoApp spyTodoApp = Mockito.spy(new TodoApp(mockTodoRepository));

        // different syntax for spies
        Mockito.doReturn(false).when(spyTodoApp).isValidDescription("");

        spyTodoApp.createTask("");
        Mockito.verifyNoInteractions(mockTodoRepository);
    }

    @Test
    public void createTask() {
        TodoRepository mockTodoRepository = Mockito.mock(TodoRepository.class);
        TodoApp spyTodoApp = Mockito.spy(new TodoApp(mockTodoRepository));

        // different syntax for spies
        Mockito.doReturn(true).when(spyTodoApp).isValidDescription("I have to do");

        spyTodoApp.createTask("I have to do");

        // saveTask is called
        // the argument is a Todo object
        ArgumentCaptor<Todo> argumentCaptor = ArgumentCaptor.forClass(Todo.class);
        Mockito.verify(mockTodoRepository).saveTask(argumentCaptor.capture());

        // and the description of the Todo object is correct
        Todo capturedArgument = argumentCaptor.getValue();
        assertEquals("I have to do", capturedArgument.getDescription());
    }

}
