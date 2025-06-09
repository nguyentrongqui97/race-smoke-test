package utils;

import java.io.*;
import java.nio.file.*;
import static constants.ConstantGlobal.*;

public class FileManagerUtils {
//    private static final String BASE_DIR = DATA_FILE_DIR;

    static {
        new File(TEMP_DATA_FILE_DIR).mkdirs();
    }

    public static String getFilePath(String prefix, String extension) {
        String threadId = String.valueOf(Thread.currentThread().getId());
        return TEMP_DATA_FILE_DIR + prefix + "_" + threadId + extension;
    }

    public static void write(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to " + filePath, e);
        }
    }

    public static String read(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath))).trim();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from " + filePath, e);
        }
    }

    public static void delete(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Failed to delete file: " + filePath);
        }
    }

    public static void deleteAllThreadFiles(String... prefixes) {
        for (String prefix : prefixes) {
            delete(getFilePath(prefix, ".txt"));
        }
    }
}

