package com.se.todos.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.se.todos.domain.Todo;

public class JSONFile {

    public List<Todo> readJsonFile(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        List<Todo> todoDataList = new ArrayList<>();
        try {
            todoDataList = mapper.readValue(new File(fileName), new TypeReference<List<Todo>>() {
            });
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return todoDataList;
        }
        return todoDataList;
    }

    public void writeJsonFile(String fileName, List<Todo> todos) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(fileName), todos);
        } catch (StreamWriteException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
