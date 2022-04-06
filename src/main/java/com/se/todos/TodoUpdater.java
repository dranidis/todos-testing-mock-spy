package com.se.todos;

@FunctionalInterface
public interface TodoUpdater {
    public void update(Todo todo);
}
