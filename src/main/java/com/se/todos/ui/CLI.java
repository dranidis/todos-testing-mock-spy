package com.se.todos.ui;

import com.se.todos.domain.TodoApp;

public class CLI {
    private TodoApp todoApp;

    public CLI(TodoApp todoApp) {
        this.todoApp = todoApp;
    }

    public void execute(String[] args) {
        switch (args[1]) {
            case "list":
                System.out.println("ALL TASKS");
                String allTasks = todoApp.showTasks();
                System.out.println(allTasks);
                break;
            case "add":
                String todoDescription = args[2];
                System.out.println("ADD TASK");
                System.out.println(todoDescription);

                todoApp.createTask(todoDescription);
                break;
            case "complete":
                String id = args[2];
                System.out.println("COMPLETE TASK");
                System.out.println(id);

                todoApp.completeTask(id);
                break;
            case "edit":
                String editId = args[2];
                String newTodoDescription = args[3];
                System.out.println("EDIT TASK");
                System.out.println(editId);

                todoApp.editTask(editId, newTodoDescription);
                break;
            case "delete":
                String deleteId = args[2];
                System.out.println("DELETE TASK");
                System.out.println(deleteId);

                todoApp.deleteTask(deleteId);
        }
    }
}
