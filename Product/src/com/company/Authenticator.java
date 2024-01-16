package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Authenticator {

    static boolean checkUsername(String usernameInput) throws IOException {

        Path filePath = Path.of("Resources/usernames.txt");
        String users = Files.readString(filePath);
        String[] usersSplit = users.split("\n");

        for (int i = 0; i < usersSplit.length; i++) {
            if (usersSplit[i].equals(usernameInput)) {

                return true;

            }

        }
        return false;

    }

    static boolean checkPassword(String passwordInput) throws IOException {

        Path filePath = Path.of("Resources/passwords.txt");
        String passes = Files.readString(filePath);
        String[] passesSplit = passes.split("\n");

        for (int i = 0; i < passesSplit.length; i++) {
            if (passesSplit[i].equals(passwordInput)) {

                return true;

            }

        }
        return false;

    }

    static boolean checkLogin(String usernameInput, String passwordInput) throws IOException {

        return checkUsername(usernameInput) && checkPassword(passwordInput);

    }

}
