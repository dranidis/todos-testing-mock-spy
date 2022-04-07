package com.se.todos;

import java.util.List;

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
        todoRepository.saveTask(new Todo(todoDescription));
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

}
