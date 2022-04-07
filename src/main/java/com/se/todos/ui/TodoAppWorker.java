package com.se.todos.ui;

@FunctionalInterface
public interface TodoAppWorker {
    public void process(String description);

}
