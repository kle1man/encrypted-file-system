package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        AdminCommands.setUpFilenames();
        System.out.println("Welcome to the encrypted file storage system!");

        while (true) {

            System.out.println("\nPlease input the following information to log in, or type \"end\" to end the program.");

            System.out.println("Username:");
            Scanner usernameScanner = new Scanner(System.in);
            String usernameString = usernameScanner.nextLine();

            if (usernameString.equals("end")) System.exit(0);

            System.out.println("Password:");
            Scanner passwordScanner = new Scanner(System.in);
            String passwordString = passwordScanner.nextLine();

            if (passwordString.equals("end")) System.exit(0);

            AdminCommands.decodeAll();

            if (usernameString.equals("admin") && passwordString.equals("adminpass")) {

                AdminCommands.encodeAll();

                System.out.print("\nAdmin Login Successful.");

                admin:
                {

                    while (true) {

                        while (true) {

                            System.out.println("\nA list of actions you can run is printed below.");
                            System.out.println("\n- Edit User Accounts\n- View/Edit Assignment Files\n");
                            System.out.println("Please type the name of the action you would like to run, or type \"exit\" if you would like to log out.");

                            Scanner adminScanner = new Scanner(System.in);
                            String adminString;

                            while (true) {

                                adminString = adminScanner.nextLine();

                                if (adminString.equals("exit")) {
                                    break admin;
                                }

                                if (adminString.equals("Edit User Accounts") || adminString.equals("View/Edit Assignment Files")) {
                                    break;
                                }

                                System.out.println("This is not a valid action. Once again the list of actions you can run is printed below.");
                                System.out.println("\n- Edit User Accounts\n- View/Edit Assignment Files\n");
                                System.out.println("Please type the name of the action you would like to run, or type \"exit\" if you would like to log out.");

                            }

                            if (adminString.equals("Edit User Accounts")) {

                                System.out.println("\nWould you like to edit the list of usernames or passwords?");

                                while (true) {

                                    adminString = adminScanner.nextLine();

                                    if (adminString.equals("usernames") || adminString.equals("passwords")) {
                                        break;
                                    }

                                    System.out.println("This is not a valid option. Would you like to edit the list of usernames or passwords?");
                                }

                                if (adminString.equals("usernames")) {

                                    while (true) {

                                        System.out.println("\nThe usernames currently saved on this computer are:\n");
                                        AdminCommands.printUsernames();
                                        System.out.println("\nWould you like to edit this list? (Answer with 'y' or 'n')");
                                        adminString = adminScanner.nextLine();

                                        if (adminString.equals("y")) {

                                            System.out.println("\nPlease retype the list of usernames with the changes you'd like, with a space between each username.");
                                            adminString = adminScanner.nextLine();

                                            AdminCommands.changeUsernames(adminString);

                                            System.out.println("\nUsernames successfully changed.");

                                        } else break;

                                    }

                                } else {

                                    while (true) {

                                        System.out.println("\nThe passwords currently saved on this computer are:\n");
                                        AdminCommands.printPasswords();
                                        System.out.println("\nWould you like to edit this list? (Answer with 'y' or 'n')");
                                        adminString = adminScanner.nextLine();

                                        if (adminString.equals("y")) {

                                            System.out.println("\nPlease retype the list of passwords with the changes you'd like, with a space between each password.");
                                            adminString = adminScanner.nextLine();

                                            AdminCommands.changePasswords(adminString);

                                            System.out.println("\nPasswords successfully changed.");

                                        } else break;

                                    }

                                }

                            } else {

                                while (true) {

                                    System.out.println("\nWould you like to view, edit, create, or delete a file? If not, type exit.");
                                    adminString = adminScanner.nextLine();

                                    if (adminString.equals("view")) {

                                        System.out.println("\nThe files currently stored on this machine are:\n");
                                        AdminCommands.printFiles();

                                        System.out.println("\nWhich file would you like to view?");
                                        adminString = adminScanner.nextLine();

                                        System.out.println("\nThe content stored in this file is:\n");
                                        AdminCommands.printContent(adminString);

                                        break;

                                    } else if (adminString.equals("edit")) {

                                        System.out.println("\nThe files currently stored on this machine are:\n");
                                        AdminCommands.printFiles();
                                        System.out.println("\nWhich file would you like to edit?");
                                        adminString = adminScanner.nextLine();

                                        System.out.println("\nThe text currently stored in the selected file is:\n");
                                        AdminCommands.printContent(adminString);

                                        System.out.println("\nPlease enter the new text to be saved to the file:");
                                        String adminString2 = adminScanner.nextLine();
                                        AdminCommands.writeFile(adminString, adminString2);

                                        System.out.println("\nThe new text saved in the file is:\n");
                                        AdminCommands.printContent(adminString);

                                        break;

                                    } else if (adminString.equals("create")) {

                                        System.out.println("\nWhat is the name of the file you want to create? (append the name with \".txt\")");
                                        adminString = adminScanner.nextLine();

                                        System.out.println("\nPlease enter the content of your new file:");
                                        String adminString2 = adminScanner.nextLine();

                                        AdminCommands.createFile(adminString, adminString2);

                                        System.out.println("\nFile created.");

                                        break;

                                    } else if (adminString.equals("delete")) {

                                        System.out.println("\nA list of the files on the system is printed below:\n");
                                        AdminCommands.printFiles();

                                        System.out.println("\nWhich of these files would you like to delete?");
                                        adminString = adminScanner.nextLine();

                                        AdminCommands.deleteFile(adminString);

                                        System.out.println("File deleted.");

                                        break;

                                    } else if (adminString.equals("exit")) {

                                        break;

                                    } else System.out.println("This is not a valid input.");

                                }

                            }

                        }

                    }

                }

            } else {

                boolean login = Authenticator.checkLogin(usernameString, passwordString);

                AdminCommands.encodeAll();

                if (login) {
                    break;
                }

                System.out.println("\nIncorrect login information. Please try again.");
            }

        }

        System.out.println("\nLogin successful.");

        while (true) {

            System.out.println("A list of files that you have access to is printed below.");
            System.out.println("\n- assignment1.txt\n- assignment2.txt\n");
            System.out.println("Please type the name of the file that you wish to view.");

            Scanner fileScanner = new Scanner(System.in);
            String fileString;
            while (true) {

                System.out.println("File:");
                fileString = fileScanner.next();

                if (fileString.equals("assignment1.txt") || fileString.equals("assignment2.txt")) {
                    break;
                }

                System.out.println("\nThis is not a valid filename. Once again a list of files that you have access to is printed below.");
                System.out.println("\n- assignment1.txt\n- assignment2.txt\n");
                System.out.println("Please type the full name of the file that you wish to view.");

            }

            System.out.println("\nYou have selected the file " + fileString);
            System.out.println("The content of this file is:");

            File file = new File("Resources/" + fileString);
            Path filePath = Path.of("Resources/" + fileString);
            String content = Files.readString(filePath);

            System.out.println(content);

            System.out.println("\nThis information is currently encrypted. Would you like to decrypt it? (Answer with 'y' or 'n')");

            Scanner yornScanner = new Scanner(System.in);
            String yornString;

            while (true) {

                yornString = yornScanner.next();

                if (yornString.equals("y")) {
                    break;
                }

                System.out.println("\nWould you like to view another file? (Answer with 'y' or 'n')");
                yornString = yornScanner.next();

                if (yornString.equals("n")) {
                    continue;
                }

                System.out.println("\nThe developer thanks you for using this program. All of the decrypted files are already re-encrypted for security, and you will be logged out upon exiting the program.");
                System.exit(0);
            }

            Encrypt.decode(("Resources/" + fileString), ("Resources/" + fileString));
            System.out.println("\nThe file has been decrypted. Here is the content stored in it:");
            content = Files.readString(filePath);
            System.out.println(content);

            Encrypt.encode(("Resources/" + fileString), ("Resources/" + fileString));

            System.out.println("\nWould you like to view another file? (Answer with 'y' or 'n')");

            while (true) {

                yornString = yornScanner.next();

                if (yornString.equals("y")) {

                    break;

                }

                System.out.println("\nThe developer thanks you for using this program. All of the decrypted files are already re-encrypted for security, and you will be logged out upon exiting the program.");
                System.exit(0);

            }

        }

    }

}




