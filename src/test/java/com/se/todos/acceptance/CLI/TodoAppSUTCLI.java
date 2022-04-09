package com.se.todos.acceptance.CLI;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.se.todos.Main;
import com.se.todos.acceptance.RepositoryHelper;
import com.se.todos.acceptance.TodoAppSUT;
import com.se.todos.domain.Todo;
import com.se.todos.util.JSONFile;

public class TodoAppSUTCLI implements TodoAppSUT {

    private final String fileName = Paths.get("src", "test", "todos.json").toString();
    private JSONFile jsonFile = new JSONFile();
    private String mainOut;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;
    private RepositoryHelper repositoryHelper = new RepositoryHelper(fileName);

    @Override
    public void setUp() {
        System.out.println("SETUP");
        repositoryHelper.emptyRepository();
        captureSystemOutput();
    }

    @Override
    public void cleanUp() {
        restoreSystemOutput();
    }

    @Override
    public void startApplication() {
    }

    // ADD

    @Override
    public void addTaskWithDescription(String description) {
        String a[] = { fileName, "add", description };
        Main.main(a);
    }

    @Override
    public void assertThatTaskIsAdded(String description) {
        repositoryHelper.assertThatTaskIsAdded(description);
    }

    // LIST ALL TASKS

    @Override
    public void listTasks() {
        String a[] = { fileName, "list" };
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

    // COMPLETE

    @Override
    public void completeSecondTask(String description) {
        String a[] = { fileName, "complete", description };
        Main.main(a);
    }

    @Override
    public void assertThatTaskIscompleted(String string) {
        assertTrue("Todo is completed", isCompleted(string));
    }

    @Override
    public void assertThatTasksAreNotcompleted(List<String> descriptions) {
        descriptions.forEach(t -> assertFalse(isCompleted(t)));
    }

    private boolean isCompleted(String description) {
        List<Todo> todos = jsonFile.readJsonFile(fileName);
        Optional<Todo> todo = todos.stream().filter(t -> t.description.equals(description)).findFirst();
        return todo.get().isCompleted();
    }

    // DELETE

    @Override
    public void deleteSecondTask(String description) {
        String a[] = { fileName, "delete", description };
        Main.main(a);
    }

    @Override
    public void assertThatTaskIsDeleted(String description) {
        repositoryHelper.assertThatTaskIsDeleted(description);
    }

    // EDIT

    @Override
    public void editSecondTask(String oldDescription, String newDescription) {
        String a[] = { fileName, "edit", oldDescription, newDescription};
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

    @Override
    public void searchTasks(String string) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void assertThatTaskWithIdIsDeleted(int id) {
        fail("Not implemented");
        
    }

    @Override
    public void deleteSecondTaskFromList() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void endApplication() {
    }

}
