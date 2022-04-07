package com.se.todos;

import java.nio.file.Paths;
import java.util.Scanner;

import com.se.todos.ui.CLI;
import com.se.todos.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("ARG " + i + ": " + args[i]);
        }
        if (args.length == 0) {

            // Scanner s = new Scanner(System.in);
            // // while (s.hasNext()) {
            // //     System.out.println(s.nextLine());
            // // }
            // System.out.println(Integer.parseInt(s.nextLine()));
            

            // System.out.flush();

            // s = new Scanner(System.in);

            // System.out.println(s.nextLine());
            // System.out.flush();

            // s = new Scanner(System.in);
            // System.out.println(Integer.parseInt(s.nextLine()));
            // System.out.flush();

            TodoRepository todoRepository = new JSONRepository(Paths.get("resources", "todos.json").toString());
            TodoApp todoApp = new TodoApp(todoRepository);
            ConsoleUI ui = new ConsoleUI(todoApp);
            ui.uiLoop();

        } else {
            CLI.execute(args);
        }
    }

}
