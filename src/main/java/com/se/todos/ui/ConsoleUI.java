package com.se.todos.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.se.todos.domain.Todo;
import com.se.todos.domain.TodoApp;

public class ConsoleUI {
    private TodoApp todoApp;
    private List<Todo> todos;
    private Scanner scanner;
    private Map<Integer, MenuChoice> menuChoices;

    public static final int ADD_TODO = 1;
    public static final int COMPLETE_TODO = 2;
    public static final int DELETE_TODO = 3;
    public static final int EXIT_APP = 0;
    public static final int EDIT_TODO = 4;

    public ConsoleUI(TodoApp todoApp) {
        this.todoApp = todoApp;
        todos = todoApp.getTasks();
        scanner = new Scanner(System.in);
        initMenuChoices();
    }

    public void uiLoop() {
        int option = 9;
        while (option != EXIT_APP) {
            System.out.println(showTasks());
            showMenu();
            System.out.print("Enter your choice : ");
            try {
                option = Integer.parseInt(scanner.nextLine());
                System.out.println("Choice: " + option);
                MenuChoice selected = menuChoices.get(option);
                if (selected != null) {
                    selected.command.execute();
                } else {
                    System.out.println("Not a menu choice!");
                }
            } catch (Exception ex) {
                System.out.println("error: " + ex.getMessage());
                System.out.println("Please enter a number between 0 and " + menuChoices.size());
            }
        }
    }

    private void initMenuChoices() {
        menuChoices = new HashMap<>();
        addMenuChoice(new MenuChoice(ADD_TODO, "Add a todo", () -> addTodo()));
        addMenuChoice(new MenuChoice(COMPLETE_TODO, "Complete a todo", () -> completeTodo()));
        addMenuChoice(new MenuChoice(DELETE_TODO, "Delete a todo", () -> deleteTodo()));
        addMenuChoice(new MenuChoice(EDIT_TODO, "Edit a todo", () -> editTodo()));
        addMenuChoice(new MenuChoice(EXIT_APP, "Exit", () -> {
        }));
    }

    private void addMenuChoice(MenuChoice menuChoice) {
        menuChoices.put(menuChoice.number, menuChoice);
    }

    private void showMenu() {
        System.out.println("\nMENU\n----");
        menuChoices.values().forEach(m -> System.out.println(m.number + " " + m.display));
    }

    private void processExistingTodo(String command, TodoAppWorker todoAppWorker) {
        System.out.print(command + " the task with nr: ");
        try {
            int taskNr = Integer.parseInt(scanner.nextLine());
            System.out.println("Task to " + command + ": " + taskNr);

            todoAppWorker.process(todos.get(taskNr - 1).description);

            todos = todoApp.getTasks();
        } catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
            System.out.println("Please enter a number between 1 and " + todos.size());
        }
    }

    private void deleteTodo() {
        processExistingTodo("Delete", n -> todoApp.deleteTask(n));
    }

    private void completeTodo() {
        processExistingTodo("Complete", n -> todoApp.completeTask(n));
    }

    private void editTodo() {
        processExistingTodo("Edit", n -> todoApp.editTask(n, enterDescription()));
    }

    private void addTodo() {
        todoApp.createTask(enterDescription());
        todos = todoApp.getTasks();
    }

    private String enterDescription() {
        System.out.print("Enter a description: ");

        String todoDescription = scanner.nextLine();
        System.out.println("Description: " + todoDescription);
        return todoDescription;
    }

    private String showTasks() {
        StringBuilder sb = new StringBuilder();

        int i = 1;
        for (Todo todo : todos) {
            sb.append("" + (i++) + ": " + todo.toString() + "\n");
        }
        return sb.toString();
    }

}
