package com.se.todos;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.se.todos.util.JSONFile;

public class JSONRepository implements TodoRepository {
    private final String fileName = "resources/todos.json";
    private final JSONFile jsonFile = new JSONFile();

    private List<Todo> todos;

    public JSONRepository() {
        createJSONFile();
        todos = jsonFile.readJsonFile(fileName);
    }

    private void saveToFile() {
        jsonFile.writeJsonFile(fileName, todos);
    }

    private void createJSONFile() {
        try {
            File myFile = new File(fileName);

            if (myFile.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Todo> getTodos() {
        return todos;
    }

    @Override
    public void saveTask(Todo todo) {
        todos.add(todo);
        saveToFile();
    }

}
