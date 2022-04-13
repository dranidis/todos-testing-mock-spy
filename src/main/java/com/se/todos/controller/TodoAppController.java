package com.se.todos.controller;

import java.util.List;

import com.se.todos.domain.Todo;
import com.se.todos.domain.TodoApp;
import com.se.todos.domain.TodoRepository;
import com.se.todos.persistence.JSONRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoAppController {

    TodoRepository todoRepository = new JSONRepository();
    TodoApp todoApp = new TodoApp(todoRepository);

    @RequestMapping("/todos")
    public List<Todo> listTasks() {
        return todoApp.getTasks();
    }
}
