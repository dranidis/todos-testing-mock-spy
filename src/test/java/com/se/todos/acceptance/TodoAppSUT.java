package com.se.todos.acceptance;

import java.util.List;

public interface TodoAppSUT {

    void startApplication();

    void setUp();

    void assertThatTaskIsAdded(String description);

    void assertThatAllTasksAreListed(List<String> asList);

    void assertThatTaskIscompleted(String description);

    void cleanUp();

    void addTaskWithDescription(String description);

    void fillRepositoryWithTodos(List<String> asList);

    void listTasks();

    void completeSecondTask(String description);

    void assertThatTasksAreNotcompleted(List<String> asList);

    void deleteSecondTask(String description);

    void assertThatTaskIsDeleted(String description);

    void editSecondTask(String oldDescription, String newDescription);

    void assertThatTaskHasChanged(String oldDescription, String newDescription);

    void searchTasks(String string);

    void assertThatTaskSecondTaskIsDeleted(String string);

    void deleteSecondTaskFromList(String string);

}
