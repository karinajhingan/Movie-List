package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new MovieListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        }
    }
}

