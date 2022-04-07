package com.se.todos.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.se.todos.Todo;
import com.se.todos.TodoApp;

public class ConsoleUI {
    TodoApp todoApp;
    List<Todo> todos;

    public ConsoleUI(TodoApp todoApp) {
        this.todoApp = todoApp;
        todos = todoApp.getTasks();
    }

    List<String> menuChoices = Arrays.asList("1. Add a todo item", "2. Complete a task", "0. Exit");

    public void showMenu() {
        menuChoices.forEach(m -> System.out.println(m + " "));
    }

    public void uiLoop() {
        Scanner scanner = new Scanner(System.in);
        int option = 9;
        while (option != 0) {
            System.out.println(showTasks());
            showMenu();
            try {
                option = scanner.nextInt();
                switch (option) {
                case 1:
                    addTodo();
                    break;
                case 2:
                    completeTodo();
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Please enter a number between 0 and " + menuChoices.size());
                System.out.println("Scanner next: " + scanner.next());
            }

        }
    }

    private void completeTodo() {
        System.out.print("Task Nr: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int taskNr = scanner.nextInt();
            todoApp.completeTask(todos.get(taskNr - 1).description);
            todos = todoApp.getTasks();
        } catch (Exception ex) {
            System.out.println("Please enter a number between 1 and " + todos.size());
            scanner.next();
        }
    }

    private void addTodo() {
        System.out.print("Task: ");
        Scanner scanner = new Scanner(System.in);
        String todoDescription = scanner.nextLine();
        todoApp.createTask(todoDescription);
        todos = todoApp.getTasks();
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
