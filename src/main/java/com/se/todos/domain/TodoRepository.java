package com.se.todos.domain;

import java.util.List;

public interface TodoRepository {

    public List<Todo> getTodos();
    public List<Todo> getTodos(String description);
    public void saveTask(Todo todo);
    public void update(String id, TodoUpdater UpdateTodo);
    public void delete(String id);
}
