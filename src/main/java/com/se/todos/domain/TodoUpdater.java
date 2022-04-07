package com.se.todos.domain;

@FunctionalInterface
public interface TodoUpdater {
    public void update(Todo todo);
}
