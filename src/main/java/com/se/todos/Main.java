package com.se.todos;

import com.se.todos.ui.CLI;
import com.se.todos.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
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

        if (args.length > 1) {
            (new CLI()).execute(args);
        } else {
        TodoApp todoApp = new TodoApp(todoRepository);
            ConsoleUI ui = new ConsoleUI(todoApp);
            ui.uiLoop();
          
        }
    }

}
