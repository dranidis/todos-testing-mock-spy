package com.se.todos.ui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.se.todos.domain.Todo;
import com.se.todos.domain.TodoApp;

public class ConsoleUI {
    TodoApp todoApp;
    List<Todo> todos;
    private Scanner scanner;

    public ConsoleUI(TodoApp todoApp) {
        this.todoApp = todoApp;
        todos = todoApp.getTasks();
        scanner = new Scanner(System.in);
        initOptions();
    }

    private List<String> menuChoices = Arrays.asList("1. Add a todo item", "2. Complete a task", "3. Delete a task", "0. Exit");

    private Map<Integer, MenuCommand> options;
    private void initOptions() {
        options = new HashMap<>();
        options.put(1, () -> addTodo());
        options.put(2, () -> completeTodo());
        options.put(3, () -> deleteTodo());
    }

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
                MenuCommand selected = options.get(option);
                if (selected != null) {
                    selected.execute();
                } else {
                    System.out.println("Not a menu choice!");
                }
            } catch (Exception ex) {
                System.out.println("error: " + ex.getMessage());
                System.out.println("Please enter a number between 0 and " + menuChoices.size());
                // System.out.println("Scanner next: " + scanner.next());
            }
        }
    }

    private void processTodo(String command, TodoAppWorker todoAppWorker) {
        System.out.print(command + " the task with nr: ");
        try {
            int taskNr = Integer.parseInt(scanner.nextLine());
            System.out.println("Task to " + command + ": " + taskNr);

            todoAppWorker.process(todos.get(taskNr - 1).description);

            todos = todoApp.getTasks();
        } catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
            System.out.println("Please enter a number between 1 and " + todos.size());
            // scanner.next();
        }
    }

    private void deleteTodo() {
        processTodo("Delete", n -> todoApp.deleteTask(n));
    }

    private void completeTodo() {
        processTodo("Complete", n -> todoApp.completeTask(n));
    }

    private void addTodo() {
        System.out.print("Add a new task with description: ");

        try {
            String todoDescription = scanner.nextLine();
            System.out.println("Task to add: " + todoDescription);

            todoApp.createTask(todoDescription);
            todos = todoApp.getTasks();
        } catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
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
