package com.se.todos.controller;

import java.util.List;

import com.se.todos.domain.Todo;
import com.se.todos.domain.TodoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoAppController {

    @Autowired
    TodoApp todoApp;

    @RequestMapping("/todos")
    public List<Todo> listTasks() {
        return todoApp.getTasks();
    }
}
