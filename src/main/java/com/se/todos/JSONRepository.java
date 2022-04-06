package com.se.todos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.se.todos.util.JSONFile;

public class JSONRepository implements TodoRepository {
    private final String fileName;
    private final JSONFile jsonFile = new JSONFile();

    private List<Todo> todos;

    public JSONRepository() {
        this.fileName = Paths.get("resources", "todos.json").toString();
        createJSONRepositoryFile();
        todos = jsonFile.readJsonFile(fileName);
    }

    public JSONRepository(String fileName2) {
        this.fileName = fileName2;
        createJSONRepositoryFile();
        todos = jsonFile.readJsonFile(fileName);
    }

    private void saveToFile() {
        jsonFile.writeJsonFile(fileName, todos);
    }

    private void createJSONRepositoryFile() {
        try {
            File myFile = new File(fileName);

            if (myFile.createNewFile()) {
                System.out.println(fileName + " repository file is created!");
            } else {
                System.out.println(fileName + " repository file already exists.");
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

    @Override
    public void update(String todoDescription, TodoUpdater todoUpdater) {
        Optional<Todo> todo = todos.stream().filter(t -> t.description.equals(todoDescription)).findFirst();
        if (todo.isPresent()) {
            todoUpdater.update(todo.get());
            saveToFile();
        } else {
            System.err.println("update: Todo not found: " + todoDescription);
        }
    }
}
