package com.lnrs.clamav.prototype;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FileToBase64Encoder {

    public static String base64encode(String filePath) {

        try {
            // Read the file as a byte array
            Path path = Paths.get(filePath);
            byte[] fileBytes = Files.readAllBytes(path);

            // Encode the byte array to Base64
            String encodedString = Base64.getEncoder().encodeToString(fileBytes);

            // Output the encoded string
            System.out.println("Encoded Base64 String:");
            System.out.println(encodedString);

            return encodedString;

        } catch (IOException e) {
            System.err.println("Error reading or encoding the file: " + e.getMessage());
        }

        return null;
    }
}
