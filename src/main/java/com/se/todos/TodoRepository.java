package com.se.todos;

import java.util.List;

public interface TodoRepository {

    public List<Todo> getTodos();

    public void saveTask(Todo todo);

}
