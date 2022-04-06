package com.se.todos;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

import com.se.todos.util.JSONFile;

public class TodoAppSUT {

    private final String fileName = Paths.get("src", "test", "todos.json").toString();
    private JSONFile jsonFile = new JSONFile();
    private String mainOut;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;

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

    public void listTasks() {
        String a[] = { fileName };
        Main.main(a);
    }

    public void assertThatAllTasksAreListed(List<String> list) {
        mainOut = outputStream.toString();

        list.stream().forEach(s -> assertTrue(mainOut.contains(s)));
    }

    public void emptyJSONFile() {
        jsonFile.writeJsonFile(fileName, new ArrayList<>());
    }

    public void fillRepositoryWithSomeTasks(List<String> list) {
        jsonFile.writeJsonFile(fileName, list.stream().map(s -> new Todo(s)).collect(toList()));
    }

    public void captureSystemOutput() {
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        // Remember the old System.out
        originalSystemOut = System.out;
        // Tell Java to use your print stream
        System.setOut(ps);
    }

    public void restoreSystemOutput() {
        System.out.flush();
        System.setOut(originalSystemOut);
    }

}
