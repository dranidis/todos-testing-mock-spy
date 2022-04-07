package com.se.todos.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.se.todos.TodoApp;
import com.se.todos.domain.Todo;

public class ConsoleUI {
    TodoApp todoApp;
    List<Todo> todos;
    private Scanner scanner;

    public ConsoleUI(TodoApp todoApp) {
        this.todoApp = todoApp;
        todos = todoApp.getTasks();
        scanner = new Scanner(System.in);
    }

    List<String> menuChoices = Arrays.asList("1. Add a todo item", "2. Complete a task", "0. Exit");

    public void showMenu() {
        System.out.println("\nMENU\n----");
        menuChoices.forEach(m -> System.out.println(m + " "));
    }

    public void uiLoop() {
        int option = 9;
        while (option != 0) {
            System.out.println(showTasks());
            showMenu();
            System.out.print("Enter your choice : ");
            try {
                option = Integer.parseInt(scanner.nextLine());
                System.out.println("Choice: " + option);
                switch (option) {
                    case 1:
                        addTodo();
                        break;
                    case 2:
                        completeTodo();
                        break;
                }
            } catch (Exception ex) {
                System.out.println("error: "+ ex.getMessage());
                System.out.println("Please enter a number between 0 and " + menuChoices.size());
                // System.out.println("Scanner next: " + scanner.next());
            }

        }
    }

    private void completeTodo() {
        System.out.print("Complete the task with nr: ");
        try {
            int taskNr = Integer.parseInt(scanner.nextLine());
            System.out.println("Task to complete: " + taskNr);
            todoApp.completeTask(todos.get(taskNr - 1).description);
            todos = todoApp.getTasks();
        } catch (Exception ex) {
            System.out.println("error: "+ ex.getMessage());
            System.out.println("Please enter a number between 1 and " + todos.size());
            // scanner.next();
        }
    }

    private void addTodo() {
        System.out.print("Add a new task with description: ");

        try {
            String todoDescription = scanner.nextLine();
            System.out.println("Task to add: " + todoDescription);

            todoApp.createTask(todoDescription);
            todos = todoApp.getTasks();
        } catch (Exception ex) {
            System.out.println("error: "+ ex.getMessage());
            // scanner.next();
        }

    }

    public String showTasks() {
        StringBuilder sb = new StringBuilder();

        int i = 1;
        for (Todo todo : todos) {
            sb.append("" + (i++) + ": " + todo.toString() + "\n");
        }
        return sb.toString();
    }

}
