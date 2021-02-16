package com.example.analitycal.tool;

import static com.example.analitycal.tool.Constants.PATH_TO_FILE;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        MyFileReader fileReader = new MyFileReader();
        SortedLinesLists sortedLinesLists = fileReader.readLines(PATH_TO_FILE);
        Analyzer analyzer = new Analyzer();
        List<String> results = analyzer.checkQuery(sortedLinesLists);
        results.forEach(System.out::println);
    }
}
