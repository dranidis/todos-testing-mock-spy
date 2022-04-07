package com.se.todos.acceptance.CLI;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

import com.se.todos.Main;
import com.se.todos.acceptance.TodoAppSUT;
import com.se.todos.domain.Todo;
import com.se.todos.util.JSONFile;

public class TodoAppSUTCLI implements TodoAppSUT {

    private final String fileName = Paths.get("src", "test", "todos.json").toString();
    private JSONFile jsonFile = new JSONFile();
    private String mainOut;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;

    public void setUp() {
        System.out.println("SETUP");
        emptyRepository();
        captureSystemOutput();
    }

    public void cleanUp() {
        restoreSystemOutput();
    }

    public void startApplication() {
    }

    // ADD

    public void addTaskWithDescription(String description) {
        String a[] = { fileName, "add", description };
        Main.main(a);
    }

    public void assertThatTaskIsAdded(String description) {
        List<Todo> todos = jsonFile.readJsonFile(fileName);

        boolean exists = todos.stream().anyMatch(t -> t.description.equals(description));
        if (!exists) {
            fail("No task found with description: '" + description + "'' in file: '" + fileName + "'");
        }
    }

    
    // LIST ALL TASKS

    public void listTasks() {
        String a[] = { fileName, "list" };
        Main.main(a);
    }

    public void assertThatAllTasksAreListed(List<String> list) {
        mainOut = outputStream.toString();

        list.stream().forEach(s -> assertTrue(mainOut.contains(s)));
    }

    public void emptyRepository() {
        jsonFile.writeJsonFile(fileName, new ArrayList<>());
    }

    public void fillRepositoryWithTodos(List<String> list) {
        jsonFile.writeJsonFile(fileName, list.stream().map(s -> new Todo(s)).collect(toList()));
    }

    // COMPLETE

    public void completeSecondTask(String description) {
        String a[] = { fileName, "complete", description};
        Main.main(a);
    }

    public void assertThatTaskIscompleted(String string) {
        assertTrue("Todo is completed", isCompleted(string));
    }

    public void assertThatTasksAreNotcompleted(List<String> descriptions) {
        descriptions.forEach(t -> assertFalse(isCompleted(t)));
    }

    private boolean isCompleted(String description) {
        List<Todo> todos = jsonFile.readJsonFile(fileName);
        Optional<Todo> todo = todos.stream().filter(t -> t.description.equals(description)).findFirst();
        return todo.get().isCompleted();
    }

    /**
     * private
     */

    private void captureSystemOutput() {
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        // Remember the old System.out
        originalSystemOut = System.out;
        // Tell Java to use your print stream
        System.setOut(ps);
    }

    private void restoreSystemOutput() {
        System.out.flush();
        System.setOut(originalSystemOut);
        System.out.println(outputStream.toString());

    }
}
