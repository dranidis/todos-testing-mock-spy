package com.se.todos;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("ARG " + i + ": " + args[i]);
        }
        String fileName = args[0];

        TodoRepository todoRepository = new JSONRepository(fileName);
        TodoApp todoApp = new TodoApp(todoRepository);

        if (args.length > 1) {
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
