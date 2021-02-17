package com.example.analitycal.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static com.example.analitycal.tool.Constants.CHAR_C;
import static com.example.analitycal.tool.Constants.CHAR_D;

public class MyFileReader {
    private final Logger logger = Logger.getLogger(MyFileReader.class.getName());
    private final SortedLinesLists sortedLinesLists = new SortedLinesLists();

    public SortedLinesLists readLines(String path) {
        logger.info("Starting to read lines");
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.charAt(0) == CHAR_C) {
                    sortedLinesLists.getResponceLines().add(line);
                } else if (line.charAt(0) == CHAR_D) {
                    sortedLinesLists.getRequestLines().add(line);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found! " + path);
        }
        logger.info("Finished to read lines");
        return sortedLinesLists;
    }
}
