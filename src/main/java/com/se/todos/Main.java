package com.se.todos;

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
            todoRepository = new JSONRepository();
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
