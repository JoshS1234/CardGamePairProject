package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;
import org.example.utils.UserMessages;

import java.util.ArrayList;
import java.util.HashMap;

import static org.example.ASCIIArt.DrawCards.drawManyCards;

public class PokerPlayer {

    private String name;
    private int chips;
    private int position;
    public PokerHand pokerhand = new PokerHand();
    public PokerHand totalHandWithCentralCards = new PokerHand();
    public int playerCurrentBet = 0;
    public boolean hasBetThisRound=false;

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

    public HashMap<String, Object> playerTurn(int currentBet, ArrayList<Card> cardsOnTable, int poorestPlayerChips) {
        HashMap<String, Object> returnHashMap = new HashMap<>();
        int userChoice = 0;
        lookAtCards();
        if (cardsOnTable.isEmpty()) {
            System.out.println("There are no cards on the table yet");
        } else {
            System.out.println("Cards on the table are: ");
            drawManyCards(cardsOnTable);
        }
        System.out.println("You have: " + this.chips + " chips" + " (+ " + this.playerCurrentBet + " chips already staked)");
        System.out.println("Current bet is: " + currentBet);


        if (currentBet < this.chips + this.playerCurrentBet && poorestPlayerChips > currentBet) {
            while (!(userChoice == 1 || userChoice == 2 || userChoice == 3)) {
                userChoice = UserMessages.getUserIntegerResponse("What would you like to do?\n1. Fold\n2. Call/Check\n3. Raise");
            }
        } else {
            while (!(userChoice == 1 || userChoice == 2 || userChoice == 3)) {
                userChoice = UserMessages.getUserIntegerResponse("What would you like to do?\n1. Fold\n2. Call/Check");
            }
        }
//        else if (currentBet < this.chips + this.playerCurrentBet && poorestPlayerChips == 0) {
//            while (!(userChoice == 1 || userChoice == 2 || userChoice == 3)) {
//                userChoice = UserMessages.getUserIntegerResponse("What would you like to do?\n1. Fold\n2. Call/Check");
//            }
//        } else if (currentBet == this.chips + this.playerCurrentBet) {
//            while (!(userChoice == 1 || userChoice == 2)) {
//                userChoice = UserMessages.getUserIntegerResponse("What would you like to do?\n1. Fold\n2. Call/Check");
//            }
//        } else {
//            System.out.println("Error");
//        }

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


    public Integer call(Integer currentBet) {
        this.chips -= currentBet - this.playerCurrentBet;
        int extraChips = currentBet - this.playerCurrentBet;
        this.playerCurrentBet = currentBet;
        return extraChips;
    }


    public Integer raise(Integer currentBet, Integer poorestPlayerChips) {
        Integer bet = 0;
        boolean isValidBet = false;

        while (!isValidBet) {
            System.out.println("current bet is at " + currentBet);
            bet = UserMessages.getUserIntegerResponse("What would you like to raise the current bet to? (your current bet is " + this.playerCurrentBet+ ", you have " + this.chips + " chips, bet cannot be over " + poorestPlayerChips + ")");
            if (bet > currentBet && bet <= poorestPlayerChips && bet <= this.chips + this.playerCurrentBet) {
                isValidBet = true;
            }
            if (bet > this.chips + this.playerCurrentBet) {
                System.out.println("You don't have enough chips");
            }
            if (bet < currentBet) {
                System.out.println("This bet is lower than the current bet");
            }
            if (bet > poorestPlayerChips) {
                System.out.println("This is too much, there is a player who cannot afford this");
            }
        }
        this.chips -= bet - this.playerCurrentBet;
        int extraChips = bet - this.playerCurrentBet;
        this.playerCurrentBet = bet;
        return extraChips;
    }

    public void lookAtCards() {
        System.out.println("----------------------------");
        System.out.println("Hi " + this.name + "!");
        System.out.println("Cards in your hand are: ");
//        this.pokerhand.printHand();
        drawManyCards(this.pokerhand.pokerHand);
    }


}
