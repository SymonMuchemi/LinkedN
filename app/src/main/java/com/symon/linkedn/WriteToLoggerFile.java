package com.symon.linkedn;
import static android.os.Build.*;

import android.os.Build;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteToLoggerFile {

    private Path logFilePath;

    public WriteToLoggerFile(String logFileName) {
        // Define the path to the log file
        if (VERSION.SDK_INT >= VERSION_CODES.UPSIDE_DOWN_CAKE) {
            this.logFilePath = Path.of(logFileName);
        }
    }

    public void writeToFile(String message) {
        // Get the current date and time
        LocalDateTime currentDateTime = null;
        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            currentDateTime = LocalDateTime.now();
        }
        // Format the date and time
        String formattedDateTime = null;
        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        // Create the log entry with date, time, and the provided message
        String logEntry = formattedDateTime + " - " + message + "\n";

        try {
            // Append the log entry to the file or create the file if it doesn't exist
            if (VERSION.SDK_INT >= VERSION_CODES.O) {
                Files.write(logFilePath, logEntry.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
            System.out.println("Message logged successfully.");
        } catch (IOException e) {
            // Handle any IOException that might occur during the file write operation
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
