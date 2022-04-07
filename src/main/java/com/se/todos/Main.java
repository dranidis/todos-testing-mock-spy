package com.se.todos;

import com.se.todos.ui.CLI;
import com.se.todos.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("ARG " + i + ": " + args[i]);
        }
        if (args.length == 0) {

            TodoRepository todoRepository = new JSONRepository();
            TodoApp todoApp = new TodoApp(todoRepository);
            ConsoleUI ui = new ConsoleUI(todoApp);
            ui.uiLoop();

        } else {
            CLI.execute(args);
        }
    }

}
