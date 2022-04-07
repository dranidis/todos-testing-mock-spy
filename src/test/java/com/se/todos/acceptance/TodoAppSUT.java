package com.se.todos.acceptance;

import java.util.List;

public interface TodoAppSUT {

    void startApplication();

    void setUp();

    void assertThatTaskIsAdded(String string);

    void assertThatAllTasksAreListed(List<String> asList);

    void assertThatTaskIscompleted(String string);

    void cleanUp();

    void addTaskWithDescription(String string);

    void fillRepositoryWithTodos(List<String> asList);

    void listTasks();

    void completeSecondTask(String string);

    void assertThatTasksAreNotcompleted(List<String> asList);

}
