package com.se.todos;

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

    void completeTask(String string);

    void assertThatTasksAreNotcompleted(List<String> asList);

}
