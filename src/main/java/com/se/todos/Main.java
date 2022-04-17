package com.se.todos;

import java.nio.file.Paths;

import com.se.todos.domain.TodoApp;
import com.se.todos.domain.TodoRepository;
import com.se.todos.persistence.JSONRepository;
import com.se.todos.ui.CLI;
import com.se.todos.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        System.out.println("MAIN");
        for (int i = 0; i < args.length; i++) {
            System.out.println("ARG " + i + ": " + args[i]);
        }

        TodoRepository todoRepository;
        if (args.length == 0) {
            String fileName = Paths.get("resources", "todos.json").toString();
            todoRepository = new JSONRepository(fileName);
        } else {
            String fileName = args[0];
            todoRepository = new JSONRepository(fileName);
        }

        TodoApp todoApp = new TodoApp(todoRepository);

        if (args.length > 1) {
            (new CLI(todoApp)).execute(args);
        } else {
            (new ConsoleUI(todoApp)).uiLoop();
        }
    }

}
