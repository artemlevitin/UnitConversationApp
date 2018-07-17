package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<ConverList> base = new ArrayList<ConverList>();
    }

    static public byte[] readFile(String filePath) {
        try {
            byte[] buff = Files.readAllBytes(Paths.get(filePath));
            return buff;
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }

    static public void parser (byte [] input){


     }

}