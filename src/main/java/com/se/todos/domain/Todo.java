package com.se.todos.domain;

public class Todo {

    public final String id;
    public String description;
    private boolean completed = false;

    public Todo(String id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }

    public Todo(String id) {
        this.id = id;
        this.description = "";
        this.completed = false;
    }

    public Todo() {
        this.id = "";
        this.description = "";
    }

    // public String getDescription() {
    // return description;
    // }

    public String toString() {
        String completedString = completed ? "[x] " : "[ ] ";
        return completedString + "Task: " + description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean b) {
        // System.out.println("setCompleted : " + b + this);
        completed = b;
    }

}
