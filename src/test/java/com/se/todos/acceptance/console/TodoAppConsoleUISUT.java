package com.se.todos.acceptance.console;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.List;

import com.se.todos.acceptance.RepositoryHelper;
import com.se.todos.acceptance.TodoAppSUT;

import com.se.todos.ui.ConsoleUI;
import com.se.todos.Main;

public class TodoAppConsoleUISUT implements TodoAppSUT {

    private final String fileName = Paths.get("src", "test", "todos.json").toString();
    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;
    private RepositoryHelper repositoryHelper = new RepositoryHelper(fileName);

    private final MainThread mainThread = new MainThread();

    @Override
    public void setUp() {
        System.out.println("SETUP Console");
        repositoryHelper.emptyRepository();
        captureSystemOutput();
    }

    @Override
    public void cleanUp() {
        restoreSystemOutput();
    }

    @Override
    public void startApplication() {
        String a[] = { fileName };

        mainThread.start(args -> Main.main(args), a);
    }

    @Override
    public void addTaskWithDescription(String description) {
        String input = "" + ConsoleUI.ADD_TODO + "\n" + description + "\n" + ConsoleUI.EXIT_APP + "\n";
        mainThread.writeToInputStream(input);
    }

    @Override
    public void assertThatTaskIsAdded(String description) {
        repositoryHelper.assertThatTaskIsAdded(description);
    }

    @Override
    public void listTasks() {
        String input = "" + ConsoleUI.EXIT_APP + "\n";
        mainThread.writeToInputStream(input);
    }

    @Override
    public void assertThatAllTasksAreListed(List<String> list) {
        String mainOut = outputStream.toString();
        list.stream().forEach(s -> assertTrue(mainOut.contains(s)));
    }

    @Override
    public void fillRepositoryWithTodos(List<String> list) {
        repositoryHelper.fillRepositoryWithTodos(list);
    }

    @Override
    public void completeSecondTask(String description) {
        String input = "" + ConsoleUI.COMPLETE_TODO + "\n" + 2 + "\n" + ConsoleUI.EXIT_APP + "\n";
        mainThread.writeToInputStream(input);
    }

    @Override
    public void assertThatTaskIscompleted(String string) {
        boolean actual = repositoryHelper.isCompleted(string);
        assertTrue("Todo is completed:" + string, actual);
    }

    @Override
    public void assertThatTasksAreNotcompleted(List<String> descriptions) {
        descriptions.forEach(t -> assertFalse(repositoryHelper.isCompleted(t)));
    }

    // DELETE

    @Override
    public void deleteSecondTask(String string) {
        String input = "" + ConsoleUI.DELETE_TODO + "\n" + 2 + "\n" + ConsoleUI.EXIT_APP + "\n";
        mainThread.writeToInputStream(input);
    }

    @Override
    public void assertThatTaskIsDeleted(String description) {
        repositoryHelper.assertThatTaskIsDeleted(description);
    }

    // EDIT

    @Override
    public void editSecondTask(String oldDescription, String newDescription) {
        String input = "" + ConsoleUI.EDIT_TODO + "\n" + 2 + "\n" + newDescription + "\n" + ConsoleUI.EXIT_APP + "\n";
        mainThread.writeToInputStream(input);
    }

    @Override
    public void assertThatTaskHasChanged(String oldDescription, String newDescription) {
        repositoryHelper.assertThatTaskHasChanged(oldDescription, newDescription);
    }

    @Override
    public void searchTasks(String description) {
        System.err.println("Search");
        String input = "" + ConsoleUI.SEARCH_TODO + "\n" + description + "\n";
        mainThread.writeToInputStream(input);
    }

    @Override
    public void deleteSecondTaskFromList() {
        System.err.println("delete 2");
        String input = "" + ConsoleUI.DELETE_TODO + "\n" + 2 + "\n" + ConsoleUI.SEARCH_TODO + "\n" + "\n"
                + ConsoleUI.EXIT_APP + "\n";
        mainThread.writeToInputStream(input);
    }

    @Override
    public void assertThatTaskWithIdIsDeleted(int id) {
        repositoryHelper.assertThatTaskWithIdIsDeleted(String.valueOf(id));
    }

    public void exit() {
        String input = "" + ConsoleUI.EXIT_APP + "\n";
        mainThread.writeToInputStream(input);
    }

    @Override
    public void endApplication() {
        mainThread.waitTermination();
    }

    /**
     * private
     */

    private void captureSystemOutput() {
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        originalSystemOut = System.out;
        System.setOut(ps);
    }

    private void restoreSystemOutput() {
        System.out.flush();
        System.setOut(originalSystemOut);
        System.out.println("MAIN OUTPUT: " + outputStream.toString());
    }

}
