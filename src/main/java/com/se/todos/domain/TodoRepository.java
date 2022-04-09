package com.se.todos.domain;

import java.util.List;
import java.util.function.Consumer;

public interface TodoRepository {

    public List<Todo> getTodos();
    public List<Todo> getTodos(String description);
    public void saveTask(Todo todo);
    public void update(String id, Consumer<Todo> UpdateTodoFun);
    public void delete(String id);
}
