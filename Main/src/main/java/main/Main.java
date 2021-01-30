package main;

import entities.Bus;

import java.util.List;

import static handlers.FileHandler.readFromFile;
import static handlers.FileHandler.writeToFile;
import static handlers.TimetableHandler.effectiveBuses;

public class Main {
    public static void main(String[] args) {

        final String inputFileName = "input.txt";
        final String outputFileName = "output.txt";

        List<Bus> buses = readFromFile(inputFileName);

        buses = effectiveBuses(buses);

        writeToFile(outputFileName, buses);
    }
}
