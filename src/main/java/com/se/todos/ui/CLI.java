package com.se.todos.ui;

import com.se.todos.JSONRepository;
import com.se.todos.TodoApp;
import com.se.todos.TodoRepository;

public class CLI {
    public static void execute(String[] args) {
        String fileName = args[0];

        TodoRepository todoRepository = new JSONRepository(fileName);
        TodoApp todoApp = new TodoApp(todoRepository);

        if (args.length > 2) {
            String todoDescription = args[1];

            System.out.println("COMPLETE TASK");
            System.out.println("=========");
            System.out.println(todoDescription);

            todoApp.completeTask(todoDescription);
        } else if (args.length > 1) {
            String todoDescription = args[1];

            System.out.println("ADD TASK");
            System.out.println("=========");
            System.out.println(todoDescription);

            todoApp.createTask(todoDescription);
        } else {
            System.out.println("ALL TASKS");
            System.out.println("=========");

            String allTasks = todoApp.showTasks();
            System.out.println(allTasks);
        }
    }
}
