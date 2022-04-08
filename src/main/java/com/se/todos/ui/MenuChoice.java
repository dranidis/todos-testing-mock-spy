package com.se.todos.ui;

public class MenuChoice {

    public final int number;
    public final String display;
    public final MenuCommand command;

    public MenuChoice(int number, String display, MenuCommand command) {
        this.number = number;
        this.display = display;
        this.command = command;
    }

}
