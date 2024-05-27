package org.example.GameManagement.Snap;

import org.example.utils.SortMethods;
import org.example.utils.UserMessages;

public class SnapModeSelector {

    public static SortMethods snapModeSelector() {
        System.out.println("Available modes are: ");
        System.out.println("Match by suite");
        System.out.println("Match by symbol");
        String userResponse = UserMessages.getUserTextResponse("Which mode would you like to play? Type Suite for option one and Symbol for option 2: ");

        while (!userResponse.equalsIgnoreCase("Suite") && !userResponse.equalsIgnoreCase("Symbol")) {
            System.out.println("Sorry I didn't catch that.");
            userResponse = UserMessages.getUserTextResponse("Which mode would you like to play? Type Suite for option one and Symbol for option 2: ");
        }

        switch (userResponse) {
            case "suite" :
                return SortMethods.suite;
            case "symbol" :
                return SortMethods.symbol;
        }
        return null;
    }
}
