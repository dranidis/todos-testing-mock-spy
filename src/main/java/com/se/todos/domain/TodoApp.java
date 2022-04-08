package com.se.todos.domain;

import java.util.List;
import java.util.UUID;

public class TodoApp {

    private TodoRepository todoRepository;

    public TodoApp(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public boolean isValidDescription(String description) {
        return !description.isEmpty();
    }

    public void createTask(String todoDescription) {
        if (!isValidDescription(todoDescription))
            return;
        todoRepository.saveTask(new Todo(UUID.randomUUID().toString(), todoDescription));
    }

    public List<Todo> getTasks() {
        return todoRepository.getTodos();
    }

    public String showTasks() {
        List<Todo> todos = todoRepository.getTodos();
        StringBuilder sb = new StringBuilder();

        for (Todo todo : todos) {
            sb.append(todo.toString() + "\n");
        }
        return sb.toString();
    }

    public void completeTask(String todoDescription) {
        todoRepository.update(todoDescription, todo -> todo.setCompleted(true));
    }

    public void deleteTask(String deleteTodoDescription) {
        todoRepository.delete(deleteTodoDescription);
    }

    public void editTask(String description, String newTodoDescription) {
        todoRepository.update(description, todo -> todo.description = newTodoDescription);
    }

}
