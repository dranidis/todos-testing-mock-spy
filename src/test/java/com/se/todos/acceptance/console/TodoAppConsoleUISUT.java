package com.se.todos.acceptance.console;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.List;

import com.se.todos.Main;
import com.se.todos.acceptance.RepositoryHelper;
import com.se.todos.acceptance.TodoAppSUT;

import com.se.todos.ui.ConsoleUI;

public class TodoAppConsoleUISUT implements TodoAppSUT {

    private final String fileName = Paths.get("src", "test", "todos.json").toString();
    private String mainOut;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;
    private RepositoryHelper repositoryHelper = new RepositoryHelper(fileName);
    private static final InputStream DEFAULT_STDIN = System.in;

    @Override
    public void setUp() {
        System.out.println("SETUP");
        repositoryHelper.emptyRepository();
        captureSystemOutput();
    }

    @Override
    public void cleanUp() {
        restoreSystemOutput();
        System.setIn(DEFAULT_STDIN);
    }

    @Override
    public void startApplication() {
    }

    @Override
    public void addTaskWithDescription(String description) {
        String input = "" + ConsoleUI.ADD_TODO + "\n" + description + "\n" + ConsoleUI.EXIT_APP + "\n";
        System.setIn(new ByteArrayInputStream((input).getBytes()));
        String a[] = { fileName };
        Main.main(a);
    }

    @Override
    public void assertThatTaskIsAdded(String description) {
        repositoryHelper.assertThatTaskIsAdded(description);
    }

    @Override
    public void listTasks() {
        String input = "" + ConsoleUI.EXIT_APP + "\n";
        System.setIn(new ByteArrayInputStream((input).getBytes()));
        String a[] = { fileName };
        Main.main(a);
    }

    @Override
    public void assertThatAllTasksAreListed(List<String> list) {
        mainOut = outputStream.toString();

        list.stream().forEach(s -> assertTrue(mainOut.contains(s)));
    }

    @Override
    public void fillRepositoryWithTodos(List<String> list) {
        repositoryHelper.fillRepositoryWithTodos(list);
    }

    @Override
    public void completeSecondTask(String description) {
        String input = "" + ConsoleUI.COMPLETE_TODO + "\n" + 2 + "\n" + ConsoleUI.EXIT_APP + "\n";
        System.setIn(new ByteArrayInputStream((input).getBytes()));
        String a[] = { fileName };
        Main.main(a);
    }

    @Override
    public void assertThatTaskIscompleted(String string) {
        assertTrue("Todo is completed", repositoryHelper.isCompleted(string));
    }

    @Override
    public void assertThatTasksAreNotcompleted(List<String> descriptions) {
        descriptions.forEach(t -> assertFalse(repositoryHelper.isCompleted(t)));
    }

    // DELETE

    @Override
    public void deleteSecondTask(String string) {
        String input = "" + ConsoleUI.DELETE_TODO + "\n" + 2 + "\n" + ConsoleUI.EXIT_APP + "\n";
        System.setIn(new ByteArrayInputStream((input).getBytes()));
        String a[] = { fileName };
        Main.main(a);
    }

    @Override
    public void assertThatTaskIsDeleted(String description) {
        repositoryHelper.assertThatTaskIsDeleted(description);

    }

    // EDIT

    @Override
    public void editSecondTask(String oldDescription, String newDescription) {
        String input = "" + ConsoleUI.EDIT_TODO + "\n" + 2 + "\n" +
                newDescription + "\n" + ConsoleUI.EXIT_APP + "\n";
        System.setIn(new ByteArrayInputStream((input).getBytes()));
        String a[] = { fileName };
        Main.main(a);
    }

    @Override
    public void assertThatTaskHasChanged(String oldDescription, String newDescription) {
        repositoryHelper.assertThatTaskHasChanged(oldDescription, newDescription);
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
