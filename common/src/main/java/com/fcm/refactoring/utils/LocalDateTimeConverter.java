package com.fcm.refactoring.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String convert(LocalDateTime localDateTime) {
        return FORMATTER.format(localDateTime);
    }

}
