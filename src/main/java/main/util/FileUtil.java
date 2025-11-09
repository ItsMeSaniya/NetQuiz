package main.util;

import java.io.*;
import java.nio.file.Files;

/**
 * File utility class for file operations
 */
public class FileUtil {
    
    /**
     * Read file into byte array
     */
    public static byte[] readFileToBytes(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }
    
    /**
     * Write byte array to file
     */
    public static void writeBytesToFile(byte[] data, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        }
    }
    
    /**
     * Get file extension
     */
    public static String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot > 0 && lastDot < fileName.length() - 1) {
            return fileName.substring(lastDot + 1);
        }
        return "";
    }
    
    /**
     * Format file size
     */
    public static String formatFileSize(long size) {
        if (size < 1024) {
            return size + " B";
        } else if (size < 1024 * 1024) {
            return String.format("%.2f KB", size / 1024.0);
        } else if (size < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", size / (1024.0 * 1024.0));
        } else {
            return String.format("%.2f GB", size / (1024.0 * 1024.0 * 1024.0));
        }
    }
}
