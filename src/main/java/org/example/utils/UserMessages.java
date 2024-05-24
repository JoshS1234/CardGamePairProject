package org.example.utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserMessages {

    public static String getUserTextResponse(String promptForUser) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println(promptForUser);
        String userResponse = myScanner.nextLine();
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");

        while (!pattern.matcher(userResponse.toLowerCase()).matches()) {
            System.out.println("Must be a alphabet response with no spaces");
            System.out.println(promptForUser);
            userResponse = myScanner.nextLine();
        }

        return userResponse;
    }


    public static Integer getUserIntegerResponse(String promptForUser) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println(promptForUser);
        String userResponse = myScanner.nextLine();
        Pattern pattern = Pattern.compile("^[0-9]+$");

        while (!pattern.matcher(userResponse.toLowerCase()).matches()) {
            System.out.println("Must be an integer response");
            System.out.println(promptForUser);
            userResponse = myScanner.nextLine();
        }

        return Integer.parseInt(userResponse);
    }




}
