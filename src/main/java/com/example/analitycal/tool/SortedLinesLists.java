package com.example.analitycal.tool;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SortedLinesLists {
    private List<String> requestLines = new ArrayList<>();
    private List<String> responceLines = new ArrayList<>();
}
