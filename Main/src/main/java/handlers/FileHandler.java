package handlers;

import entities.Bus;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FileHandler {

    public static void writeStringToFile(String fileName, String str) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(str);
        } catch (IOException exception) {
            log.info("Can't write to file", exception.fillInStackTrace());
        }
    }

    public static void writeToFile(String fileName, List<Bus> buses) {
        String busesString = buses.stream()
                .map(Bus::toString)
                .collect(Collectors.joining("\n"));
        writeStringToFile(fileName, busesString);
    }

    public static List<String> readStringsFromFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            log.info("Can't read from file", exception.fillInStackTrace());
            return Collections.emptyList();
        }
    }

    public static List<Bus> readFromFile(String fileName) {
        List<String> busStringList = readStringsFromFile(fileName);
        List<Bus> busList = new ArrayList<>(busStringList.size());
        busStringList.forEach(busString -> busList.add(Bus.of(busString)));
        return busList;
    }
}
