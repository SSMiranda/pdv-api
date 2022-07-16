package com.mirandasidney.pdv.api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DateUtils {

    public static String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();

        return timestamp.format(formatter);
    }
}
