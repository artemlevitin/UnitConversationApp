package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {

    }

    static public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }

    static public void parser (List<String> input) {
    }

}