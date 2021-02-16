package com.example.analitycal.tool;

import static com.example.analitycal.tool.Contstants.DD_MM_YYYY;
import static com.example.analitycal.tool.Contstants.EMPTY_CHAR;
import static com.example.analitycal.tool.Contstants.ONE;
import static com.example.analitycal.tool.Contstants.REGEX_DASH;
import static com.example.analitycal.tool.Contstants.REGEX_EMPTY_SPACE;
import static com.example.analitycal.tool.Contstants.REGEX_P;
import static com.example.analitycal.tool.Contstants.REGEX_PN;
import static com.example.analitycal.tool.Contstants.REGEX_POINT;
import static com.example.analitycal.tool.Contstants.STAR_STRING;
import static com.example.analitycal.tool.Contstants.TWO;
import static com.example.analitycal.tool.Contstants.ZERO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Analyzer {
    Logger logger = Logger.getLogger(Analyzer.class.getName());

    public List<String> checkQuery(SortedLinesLists sortedLinesLists) {
        logger.info("Starting to read lines");
        List<String> result = new ArrayList<>();

        for (String string : sortedLinesLists.getRequestLines()) {
            int count = 0;
            int sum = 0;
            String[] splitedD = string.split(REGEX_P);

            for (String responceLines : sortedLinesLists.getResponceLines()) {
                String[] splitedResponce = responceLines.split(REGEX_PN);
                if (checkIds(splitedD[ZERO], splitedResponce[ZERO])
                        && checkDate(splitedD[ONE], splitedResponce[ONE])) {
                    count++;
                    sum += Integer.parseInt(
                            responceLines.substring(responceLines.lastIndexOf(EMPTY_CHAR)).trim()
                    );
                }
            }
            result.add(count == 0 ? "-" : String.valueOf(sum / count));
        }
        return result;
    }

    private boolean checkIds(String requestIds, String responceIds) {
        if (requestIds == null || responceIds == null) {
            return false;
        }
        String[] splitedRequestIds = requestIds.split(REGEX_EMPTY_SPACE);
        String[] splitedResponceIds = responceIds.split(REGEX_EMPTY_SPACE);
        if (splitedRequestIds.length < 2 || splitedResponceIds.length < 2) {
            return false;
        }
        return (STAR_STRING.equals(splitedRequestIds[ONE])
                || checkVersion(splitedRequestIds[ONE], splitedResponceIds[ONE]))
                && (STAR_STRING.equals(splitedRequestIds[TWO])
                || checkVersion(splitedRequestIds[TWO], splitedResponceIds[TWO]));
    }

    private boolean checkVersion(String requestVersion, String responceVersion) {
        if (requestVersion == null || responceVersion == null) {
            return false;
        }
        String[] splitRequestVersion = requestVersion.split(REGEX_POINT);
        String[] splitResponceVersion = responceVersion.split(REGEX_POINT);
        int length = Math.min(splitRequestVersion.length, splitResponceVersion.length);
        for (int i = 0; i < length; i++) {
            if (splitRequestVersion[i] == null
                    || !splitRequestVersion[i].equals(splitResponceVersion[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDate(String requestDate, String responceDate) {
        if (requestDate == null || responceDate == null) {
            return false;
        }
        String[] splitedRequestDates = requestDate.trim().split(REGEX_DASH);
        String[] spitedResponceDates = responceDate.trim().split(REGEX_EMPTY_SPACE);
        return splitedRequestDates.length == ONE
                ? checkDay(splitedRequestDates[ZERO], spitedResponceDates[ZERO])
                : checkPeriod(splitedRequestDates, spitedResponceDates[ZERO]);
    }

    private boolean checkPeriod(String[] splitedRequestDates, String spitedResponceDate) {
        if (splitedRequestDates[ZERO] == null
                || splitedRequestDates[ONE] == null
                || spitedResponceDate == null) {
            return false;
        }
        LocalDate startOfDate = getParsedDate(splitedRequestDates[ZERO]);
        LocalDate endOfDate = getParsedDate(splitedRequestDates[ONE]);
        LocalDate checkedDate = getParsedDate(spitedResponceDate);
        return checkedDate.isAfter(startOfDate.minusDays(ONE))
                && checkedDate.isBefore(endOfDate.plusDays(ONE));
    }

    private boolean checkDay(String splitedRequestDate, String spitedResponceDate) {
        return splitedRequestDate != null && splitedRequestDate.equals(spitedResponceDate);
    }

    private LocalDate getParsedDate(String stringDate) {
        return LocalDate.parse(stringDate, DateTimeFormatter.ofPattern(DD_MM_YYYY));
    }
}
