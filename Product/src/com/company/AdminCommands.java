package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AdminCommands {

    public static List<String> filenames = new ArrayList<String>();

    static void setUpFilenames() throws IOException {

        filenames.add("assignment1.txt");
        filenames.add("assignment2.txt");

    }

    static void printFiles() throws IOException {

        String fileString = filenames.toString();
        fileString = fileString.replaceAll("\\[", "");
        fileString = fileString.replaceAll("]", "");
        fileString = fileString.replaceAll(", ", "\n");
        System.out.println(fileString);

    }

    static void addFiles(String fileName) throws IOException {

        filenames.add(fileName);

    }

    static void printContent(String fileName) throws IOException {

        Path filePath = Path.of("Resources/" + fileName);
        Encrypt.decode("Resources/" + fileName, "Resources/" + fileName);
        String content = Files.readString(filePath);
        Encrypt.encode("Resources/" + fileName, "Resources/" + fileName);
        System.out.println(content);

    }

    static void writeFile(String fileName, String content) throws IOException {

        try (PrintWriter out = new PrintWriter("Resources/" + fileName, StandardCharsets.UTF_8)) {
            out.write(content);
        }

        Encrypt.encode("Resources/" + fileName, "Resources/" + fileName);

    }

    static void createFile(String fileName, String content) throws IOException {

        File file = new File("Resources/" + fileName);

        try (PrintWriter out = new PrintWriter("Resources/" + fileName, StandardCharsets.UTF_8)) {
            out.write(content);
        }

        Encrypt.encode("Resources/" + fileName, "Resources/" + fileName);

        filenames.add(fileName);

    }

    static void deleteFile(String fileName) throws IOException {

        Path filePath = Path.of("Resources/" + fileName);
        Files.delete(filePath);

    }

    static void encodeAll() throws IOException {

        Encrypt.encode("Resources/usernames.txt", "Resources/usernames.txt");
        Encrypt.encode("Resources/passwords.txt", "Resources/passwords.txt");

    }

    static void decodeAll() throws IOException {

        Encrypt.decode("Resources/usernames.txt", "Resources/usernames.txt");
        Encrypt.decode("Resources/passwords.txt", "Resources/passwords.txt");

    }

    static void printUsernames() throws IOException {

        Path filePath = Path.of("Resources/usernames.txt");
        Encrypt.decode("Resources/usernames.txt", "Resources/usernames.txt");
        String users = Files.readString(filePath);
        Encrypt.encode("Resources/usernames.txt", "Resources/usernames.txt");
        System.out.println(users);

    }

    static void printPasswords() throws IOException {

        Path filePath = Path.of("Resources/passwords.txt");
        Encrypt.decode("Resources/passwords.txt", "Resources/passwords.txt");
        String passes = Files.readString(filePath);
        Encrypt.encode("Resources/passwords.txt", "Resources/passwords.txt");
        System.out.println(passes);

    }

    static void changeUsernames(String changeUsernamesInput) throws IOException {

        File file = new File("Resources/usernames.txt");

        String changedUsernames = changeUsernamesInput.replaceAll(" ", "\n");

        try (PrintWriter out = new PrintWriter(file, StandardCharsets.UTF_8)) {
            out.write(changedUsernames);
        }

        Encrypt.encode("Resources/usernames.txt", "Resources/usernames.txt");

    }

    static void changePasswords(String changePasswordsInput) throws IOException {

        File file = new File("Resources/passwords.txt");

        String changedPasswords = changePasswordsInput.replaceAll(" ", "\n");

        try (PrintWriter out = new PrintWriter(file, StandardCharsets.UTF_8)) {
            out.write(changedPasswords);
        }

        Encrypt.encode("Resources/passwords.txt", "Resources/passwords.txt");

    }

}
