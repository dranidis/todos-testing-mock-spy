package com.se.todos;

import com.se.todos.domain.Todo;

@FunctionalInterface
public interface TodoUpdater {
    public void update(Todo todo);
}
