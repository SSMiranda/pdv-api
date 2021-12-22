package com.mirandasidney.pdv.api.util;

import com.mirandasidney.pdv.api.domain.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Util {

    public static String formatDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();

        return timestamp.format(formatter);
    }
}
