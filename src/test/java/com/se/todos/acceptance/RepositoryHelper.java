package com.se.todos.acceptance;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.se.todos.domain.Todo;
import com.se.todos.util.JSONFile;

public class RepositoryHelper {

    private final String fileName;
    private JSONFile jsonFile = new JSONFile();

    public RepositoryHelper(String fileName) {
        this.fileName = fileName;
    }

    public void assertThatTaskIsAdded(String description) {
        if (!taskExists(t -> t.description.equals(description))) {
            fail("No task found with description: '" + description + "'' in file: '" + fileName + "'");
        }
    }

    public void assertThatTaskIsDeleted(String description) {
        if (taskExists(t -> t.description.equals(description))) {
            fail("A task was found with description: '" + description + "'' in file: '" + fileName + "'");
        }
    }

    public void fillRepositoryWithTodos(List<String> list) {
        List<Todo> todos = new ArrayList<>();
        int i = 1;
        for (String description : list) {
            todos.add(new Todo("" + i++, description));
        }
        jsonFile.writeJsonFile(fileName, todos);
    }

    public boolean isCompleted(String description) {
        print();
        List<Todo> todos = jsonFile.readJsonFile(fileName);
        Optional<Todo> todo = todos.stream().filter(t -> t.description.equals(description)).findFirst();
        return todo.get().isCompleted();
    }

    public void emptyRepository() {
        jsonFile.writeJsonFile(fileName, new ArrayList<>());
    }

    public void assertThatTaskHasChanged(String oldDescription, String newDescription) {
        if (taskExists(t -> t.description.equals(oldDescription))) {
            fail("Old task found with description: '" + oldDescription + "'' in file: '" + fileName + "'");
        }
        if (!taskExists(t -> t.description.equals(newDescription))) {
            fail("No task found with description: '" + newDescription + "'' in file: '" + fileName + "'");
        }
    }

    public void print() {
        List<Todo> todos = jsonFile.readJsonFile(fileName);
        for (Todo todo : todos) {
            System.err.println(todo);
        }
    }

    public void assertThatTaskWithIdIsDeleted(String i) {
        if (taskExists(t -> t.id.equals(i))) {
            fail("Task with id found: " + i);
        }
    }

    public boolean taskExists(Predicate<Todo> predicate) {
        List<Todo> todos = jsonFile.readJsonFile(fileName);
        return todos.stream().anyMatch(predicate);
    }

}
