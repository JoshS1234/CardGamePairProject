package org.example.utils;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
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


    public static int getUserIntegerResponse(String promptForUser) {
        Scanner myScanner = new Scanner(System.in);
        boolean hasAnswered = false;
        int userResponse=0;


        while (!hasAnswered) {
            try {
                System.out.println(promptForUser);
                userResponse = myScanner.nextInt();
                hasAnswered = true;
            } catch (Exception e) {
                System.out.println("Must be an integer response");
                System.out.println(promptForUser);
                userResponse = myScanner.nextInt();
            }
        }
        return userResponse;
    }


}
