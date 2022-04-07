package com.se.todos.domain;

import java.util.List;

public interface TodoRepository {

    public List<Todo> getTodos();
    public void saveTask(Todo todo);
    public void update(String todoDescription, TodoUpdater UpdateTodo);
}
