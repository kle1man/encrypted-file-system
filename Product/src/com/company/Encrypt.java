package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Encrypt {

    static void encode(String startPath, String endPath) throws IOException {

        Path filePath = Path.of(startPath);
        String content = Files.readString(filePath);
        String[] splitContent = content.split("");
        int forLength = (int) Math.ceil(Math.sqrt(content.length()));
        String[][] originalArray = new String[forLength][forLength];
        String[][] scrambledArray = new String[forLength][forLength];

        int position = 0;
        for (int i = 0; i < forLength; i++) {

            for (int j = 0; j < forLength; j++) {

                if (position >= content.length()) {

                    originalArray[i][j] = " ";

                } else {

                    originalArray[i][j] = splitContent[position];
                    position++;

                }

            }

        }

        position = 0;
        for (int i = 0; i < forLength; i++) {

            for (int j = 0; j < forLength; j++) {

                scrambledArray[i][j] = originalArray[j][i];
                position++;

            }

        }

        String output = "";
        for (int i = 0; i < forLength; i++) {

            for (int j = 0; j < forLength; j++) {

                output = output + scrambledArray[i][j];

            }

        }

        try (PrintWriter out = new PrintWriter(endPath, StandardCharsets.UTF_8)) {

            out.write(output);

        }

    }

    static void decode(String startPath, String endPath) throws IOException {

        Path filePath = Path.of(startPath);
        String content = Files.readString(filePath);
        String[] splitContent = content.split("");
        int forLength = (int) Math.ceil(Math.sqrt(content.length()));
        String[][] scrambledArray = new String[forLength][forLength];
        String[][] originalArray = new String[forLength][forLength];

        int position = 0;
        for (int i = 0; i < forLength; i++) {

            for (int j = 0; j < forLength; j++) {

                if (position >= content.length()) {
                    scrambledArray[i][j] = " ";

                } else {

                    scrambledArray[i][j] = splitContent[position];
                    position++;

                }

            }

        }

        position = 0;
        for (int i = 0; i < forLength; i++) {

            for (int j = 0; j < forLength; j++) {

                originalArray[i][j] = scrambledArray[j][i];
                position++;

            }

        }

        String output = "";
        for (int i = 0; i < forLength; i++) {

            for (int j = 0; j < forLength; j++) {

                output = output + originalArray[i][j];

            }

        }


        try (PrintWriter out = new PrintWriter(endPath, StandardCharsets.UTF_8)) {

            out.write(output);

        }

    }

}