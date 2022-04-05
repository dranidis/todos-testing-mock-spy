package com.se.todos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
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
    TodoRepository mockTodoRepository;
    TodoApp todoApp;

    @Before
    public void setup() {
        mockTodoRepository = Mockito.mock(TodoRepository.class);
        todoApp = new TodoApp(mockTodoRepository);
    }

    @Test
    public void showTasks_OneTask() {
        String taskDescription = "Do something";
        List<Todo> todos = new ArrayList<Todo>(Arrays.asList(new Todo(taskDescription)));
        Mockito.when(mockTodoRepository.getTodos()).thenReturn(todos);

        String output = todoApp.showTasks();

        assertTrue(output.contains(taskDescription));
    }

    @Test
    public void showTasks_Two_Tasks() {
        List<String> descriptionsOfTasksStored = new ArrayList<String>(Arrays.asList("Buy groceries", "Study SE"));
        List<Todo> todos = descriptionsOfTasksStored.stream().map(d -> new Todo(d)).collect(Collectors.toList());
        Mockito.when(mockTodoRepository.getTodos()).thenReturn(todos);

        String output = todoApp.showTasks();

        for(String description: descriptionsOfTasksStored) {
            assertTrue(output.contains(description));
        }
    }

    @Test
    public void createTask_EmptyString() {
        TodoApp spyTodoApp = Mockito.spy(todoApp);
        // different syntax for spies
        Mockito.doReturn(false).when(spyTodoApp).isValidDescription("");

        spyTodoApp.createTask("");

        Mockito.verifyNoInteractions(mockTodoRepository);
    }

    @Test
    public void createTask() {
        String taskDescription = "I have to do";
        TodoApp spyTodoApp = Mockito.spy(todoApp);
        // different syntax for spies
        Mockito.doReturn(true).when(spyTodoApp).isValidDescription(taskDescription);

        spyTodoApp.createTask(taskDescription);

        // saveTask is called
        // the argument is a Todo object
        ArgumentCaptor<Todo> argumentCaptor = ArgumentCaptor.forClass(Todo.class);
        Mockito.verify(mockTodoRepository).saveTask(argumentCaptor.capture());
        // and the description of the Todo object is correct
        Todo capturedArgument = argumentCaptor.getValue();
        assertEquals(taskDescription, capturedArgument.getDescription());
    }

}
