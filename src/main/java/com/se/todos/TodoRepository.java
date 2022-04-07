package com.se.todos;

import java.util.List;

import com.se.todos.domain.Todo;

public interface TodoRepository {

    public List<Todo> getTodos();
    public void saveTask(Todo todo);
    public void update(String todoDescription, TodoUpdater UpdateTodo);
}
