package org.example.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class UserInteraction {

    private static String userInput;
    private static boolean hasPlayerSnapped = true;

    //Check if player inputs something within a timeframe
    static TimerTask userHitsEnter = new TimerTask() {
        @Override
        public void run() {
            try {
                if (!userInput.isEmpty()) {
                    System.out.println("Wah wah.");
                }
            } catch (Exception e) {
                hasPlayerSnapped = false;
            }
        }
    };

    public static void getInput() throws Exception {
        Timer timer = new Timer();
        timer.schedule(userHitsEnter, 1000);

        System.out.println("Quick hit enter!");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        userInput = in.readLine();

        timer.cancel();
        System.out.println(hasPlayerSnapped);
    }


//    public boolean playerSnaps(Scanner scanner) {
//        TimerTask userHitsEnter = new TimerTask() {
//            @Override
//            public void run() {
//                String readString = scanner.nextLine();
//                if(readString.isEmpty()) {
//                    System.out.println("Oh no!");
//                    hasPlayerSnapped = false;
//                }
//            }
//        };
//
//        public void getInput() {
//
//        };
//
//        return hasPlayerSnapped;
//    }
}
