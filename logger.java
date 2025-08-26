package logger;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class logger {
    public void write(String journal) {
        try{
            FileWriter writer = new FileWriter("Logger.txt",true);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
            writer.write(now.format(format) + System.lineSeparator()+" Dear Diary,"+System.lineSeparator());
            writer.write("    "+journal + System.lineSeparator());
            writer.flush();
            writer.close();
        } catch(IOException e) {
            System.out.println("Error saving the session"+e.getMessage());
        }
    }

    public void viewlog() {
        File file = new File("Logger.txt");  // <-- Change path if needed
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

        try (Scanner reader = new Scanner(file)) {
            System.out.println("Your Logs:");
            int count = 0;
            StringBuilder entry = new StringBuilder();
            boolean insideEntry = false;

            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();

                try {
                    // Try parsing whole line as datetime
                    LocalDateTime dateTime = LocalDateTime.parse(line, format);

                    // If an old entry exists, print and count it
                    if (entry.length() > 0) {
                        count++;
                        System.out.println(count + "." + entry.toString().trim());
                        System.out.println();
                        entry.setLength(0);
                    }

                    // Start new entry with datetime line
                    entry.append(line).append("\n");
                    insideEntry = true;

                } catch (DateTimeParseException e) {
                    // Not a datetime → part of entry
                    if (insideEntry) {
                        entry.append(line).append("\n");
                    }
                }
            }

            // Print the last entry if exists
            if (entry.length() > 0) {
                count++;
                System.out.println(count + ".\n" + entry.toString().trim());
                System.out.println();
            }

            System.out.println("You have completed " + count + " sessions so far.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
