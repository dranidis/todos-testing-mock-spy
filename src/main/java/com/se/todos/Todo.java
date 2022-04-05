package com.se.todos;

public class Todo {

    public final String description;

    public Todo(String description) {
        this.description = description;
    }

    public Todo() {
        this.description = "";
    }

    // public String getDescription() {
    //     return description;
    // }

    public String toString() {
        return "Task: " + description;
    }
 
}
