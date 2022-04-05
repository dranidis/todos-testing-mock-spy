package com.se.todos;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import com.se.todos.util.JSONFile;

public class TodoAppSUT {

    private JSONFile jsonFile = new JSONFile();
    private final String fileName = "resources/todos.json";

    public void startApplication() {
        //
        // empty the todos
        //
        jsonFile.writeJsonFile(fileName, new ArrayList<>());
    }

    public void addTaskWithDescription(String description) {
        String a[] = { description };
        Main.main(a);
    }

    public void assertThatTaskIsAdded(String description) {
        List<Todo> todos = jsonFile.readJsonFile(fileName);

        boolean exists = todos.stream().anyMatch(t -> t.description.equals(description));
        if (!exists) {
            fail("No task found with description: '" + description + "'' in file: '" + fileName + "'");
        }

    }

}
