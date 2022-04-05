package com.se.todos;

public class Main {
    public static void main(String[] args) {
        TodoRepository todoRepository = new JSONRepository();
        TodoApp todoApp = new TodoApp(todoRepository);

        for(int i=0;i<args.length;i++) {
            System.out.println("ARG " + i + ": " + args[i]);
        }

        String todoDescription = args[0];
        todoApp.createTask(todoDescription);
    }
}
