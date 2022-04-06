package com.se.todos;

public class Main {
    public static void main(String[] args) {
        for(int i=0;i<args.length;i++) {
            System.out.println("ARG " + i + ": " + args[i]);
        }
        String fileName = args[0];
        String todoDescription = args[1];

        TodoRepository todoRepository = new JSONRepository(fileName);
        TodoApp todoApp = new TodoApp(todoRepository);

        todoApp.createTask(todoDescription);
    }
}
