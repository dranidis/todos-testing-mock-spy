package com.se.todos;

import static org.junit.Assert.fail;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.se.todos.util.JSONFile;

public class TodoAppSUT {

    private final String fileName = Paths.get("src", "test", "todos.json").toString();
    private JSONFile jsonFile = new JSONFile();

    public void startApplication() {
    }

    public void addTaskWithDescription(String description) {
        String a[] = { fileName, description };
        Main.main(a);
    }

    public void assertThatTaskIsAdded(String description) {
        List<Todo> todos = jsonFile.readJsonFile(fileName);

        boolean exists = todos.stream().anyMatch(t -> t.description.equals(description));
        if (!exists) {
            fail("No task found with description: '" + description + "'' in file: '" + fileName + "'");
        }

    }


    public void emptyJSONFile() {
        //
        // empty the todos
        //
        jsonFile.writeJsonFile(fileName, new ArrayList<>());

    }

}
