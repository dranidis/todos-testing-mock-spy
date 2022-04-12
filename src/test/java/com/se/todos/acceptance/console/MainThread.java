package com.se.todos.acceptance.console;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.function.Consumer;
import java.io.IOException;
import java.io.InputStream;

public class MainThread {
    private static final InputStream DEFAULT_STDIN = System.in;

    private PipedOutputStream pipedOutputStream;
    private Thread mainThread;

    public void start(Consumer<String []> mainObject, String[] args) {
        try {
            pipedOutputStream = new PipedOutputStream();
            System.setIn(new PipedInputStream(pipedOutputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainThread = new Thread(new Runnable() {
            public void run() {
                mainObject.accept(args);
            }
        });

        mainThread.start();
    }

    public void writeToInputStream(String input) {
        try {
            pipedOutputStream.write(input.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitTermination() {
        try {
            mainThread.join();
            System.setIn(DEFAULT_STDIN);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
