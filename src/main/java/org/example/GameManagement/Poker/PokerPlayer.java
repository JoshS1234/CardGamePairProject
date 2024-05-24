package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;
import org.example.utils.UserMessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PokerPlayer {

    private String name;
    private int chips;
    private int position;
    public PokerHand pokerhand = new PokerHand();
    public PokerHand totalHandWithCentralCards = new PokerHand();

    public String getName() {
        return name;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public int getPosition() {
        return position;
    }

    public PokerPlayer(String name, int chips, int position) {
        this.name = name;
        this.chips = chips;
        this.position = position;
    }

    public HashMap<String, Object> playerTurn(int currentBet) {
        HashMap<String, Object> returnHashMap = new HashMap<>();
        System.out.println("Current bet is: " + currentBet);
        int userChoice=0;
        lookAtCards();
        System.out.println("There are no cards on the table yet");


        if (currentBet<this.chips) {

            while (!(userChoice==1 || userChoice==2 || userChoice==3)) {
                userChoice = UserMessages.getUserIntegerResponse("What would you like to do?\n1. Fold\n2. Call\n3. Raise");
            }


        } else if (currentBet==this.chips) {
            while (!(userChoice==1 || userChoice==2)) {
                userChoice = UserMessages.getUserIntegerResponse("What would you like to do?\n1. Fold\n2. Call");
            }
        }

        switch (userChoice) {
            case 1:
                System.out.println("Fold");
                returnHashMap.put("choice", "fold");
                break;
            case 2:
                System.out.println("Call");
                returnHashMap.put("choice", "call");
                break;
            case 3:
                System.out.println("Raise");
                returnHashMap.put("choice", "raise");
                break;
            default:
                System.out.println("Something broke");
                returnHashMap.put("choice", "something broke");
        }

        return returnHashMap;


    }

    public HashMap<String, Object> playerTurn(int currentBet, ArrayList<Card> cardsOnTable) {
        HashMap<String, Object> returnHashMap = new HashMap<>();
        System.out.println("Current bet is: " + currentBet);
        int userChoice=0;
        lookAtCards();
        System.out.println("Cards on the table are: ");
        for (Card card : cardsOnTable) {
            System.out.println(card);
        }

        if (currentBet<this.chips) {

            while (!(userChoice==1 || userChoice==2 || userChoice==3)) {
                userChoice = UserMessages.getUserIntegerResponse("What would you like to do?\n1. Fold\n2. Call\n3.Raise");
            }


        } else if (currentBet==this.chips) {
            while (!(userChoice==1 || userChoice==2)) {
                userChoice = UserMessages.getUserIntegerResponse("What would you like to do?\n1. Fold\n2. Call");
            }
        }

        switch (userChoice) {
            case 1:
                System.out.println("Fold");
                returnHashMap.put("choice", "fold");
            break;
            case 2:
                System.out.println("Call");
                returnHashMap.put("choice", "call");
            break;
            case 3:
                System.out.println("Raise");
                returnHashMap.put("choice", "raise");
                break;
            default:
                System.out.println("Something broke");
                returnHashMap.put("choice", "something broke");
        }

        return returnHashMap;


    }


//    public boolean isFolding() {
//        boolean isFolding;
//        String fold="";
//        while (!Objects.equals(fold, "y") && !Objects.equals(fold, "n")) {
//            lookAtCards();
//            fold = UserMessages.getUserTextResponse("Would you like to fold? (y/n)");
//        }
//        if (fold.equals("y")) {
//            isFolding = true;
//        } else {
//            isFolding = false;
//        }
//        return isFolding;
//    }
//
//    public boolean isFolding(ArrayList<Card> cardsOnTable) {
//        boolean isFolding;
//        String fold="";
//        while (!Objects.equals(fold, "y") && !Objects.equals(fold, "n")) {
//            lookAtCards();
//            System.out.println("Cards on the table are: ");
//            for (Card card : cardsOnTable) {
//                System.out.println(card);
//            }
//            fold = UserMessages.getUserTextResponse("Would you like to fold? (y/n)");
//        }
//        if (fold.equals("y")) {
//            isFolding = true;
//        } else {
//            isFolding = false;
//        }
//        return isFolding;
//    }



    public Integer bet(Integer currentBet, Integer poorestPlayerChips) {
        Integer bet = 0;
        boolean isValidBet = false;

        while (!isValidBet) {
            System.out.println(this.name + "'s turn to bet:");
            bet = UserMessages.getUserIntegerResponse("How much would you like to bet? (you have " + this.chips + " chips, bet cannot be over "+ poorestPlayerChips + ")");
            if (bet>=currentBet && bet<=poorestPlayerChips && bet<this.chips) {
                isValidBet=true;
            }
            if (bet>this.chips) {
                System.out.println("You don't have enough chips");
            }
            if(bet<currentBet) {
                System.out.println("This bet is lower than the current bet");
            }
            if (bet>poorestPlayerChips) {
                System.out.println("This is too much, there is a player who cannot afford this");
            }
        }
        this.chips-=bet;
        return bet;
    }

    public void lookAtCards() {
        System.out.println("----------------------------");
        System.out.println("Hi " + this.name + "!");
        System.out.println("Cards in your hand are: ");
        this.pokerhand.printHand();
    }

}
