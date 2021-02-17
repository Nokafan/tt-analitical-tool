package com.example.analitycal.tool;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MyFileReaderTest {
    public static final String STRING_ONE = "C 1.1 8.15.1 P 15.10.2012 83";
    public static final String STRING_TWO = "C 1.1 5.5.1 P 01.11.2012 117";
    public static final String STRING_THREE = "C 3 10.2 N 02.10.2012 100";
    public static final String STRING_FOUR = "D 1.1 8 P 01.01.2012-01.12.2012";
    public static final String STRING_FIVE = "D 1 * P 08.10.2012-20.11.2012";
    public static final String INPUT_TEST_TXT = "src/test/java/resoureces/input_test.txt";
    private final MyFileReader fileReader = new MyFileReader();
    private final List<String> stringsResponceList = new ArrayList<>();
    private final List<String> stringsRequestList = new ArrayList<>();

    @Before
    public void fillStrings() {
        stringsResponceList.add(STRING_ONE);
        stringsResponceList.add(STRING_TWO);
        stringsResponceList.add(STRING_THREE);
        stringsRequestList.add(STRING_FOUR);
        stringsRequestList.add(STRING_FIVE);
    }

    @Test
    public void readLines() {
        SortedLinesLists lists = fileReader.readLines(INPUT_TEST_TXT);
        Assert.assertEquals(lists.getResponceLines(), stringsResponceList);
        Assert.assertEquals(lists.getRequestLines(), stringsRequestList);
    }
}