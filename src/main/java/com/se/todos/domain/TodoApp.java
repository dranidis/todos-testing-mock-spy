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

    public List<Todo> getTasks(String description) {
        return todoRepository.getTodos(description);
    }

    public String showTasks() {
        List<Todo> todos = todoRepository.getTodos();
        StringBuilder sb = new StringBuilder();

        for (Todo todo : todos) {
            sb.append(todo.toString() + "\n");
        }
        return sb.toString();
    }

    public void completeTask(String id) {
        todoRepository.update(id, todo -> todo.setCompleted(true));
    }

    public void deleteTask(String id) {
        todoRepository.delete(id);
    }

    public void editTask(String id, String newTodoDescription) {
        todoRepository.update(id, todo -> todo.description = newTodoDescription);
    }



}
