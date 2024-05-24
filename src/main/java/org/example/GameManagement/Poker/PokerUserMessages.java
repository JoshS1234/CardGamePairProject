package org.example.GameManagement.Poker;

import org.example.utils.UserMessages;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PokerUserMessages extends UserMessages {

    public static Integer getNumberOfPlayers() {
        Integer numberOfPlayers=0;
        while (numberOfPlayers<2 || numberOfPlayers>4) {
            numberOfPlayers = UserMessages.getUserIntegerResponse("Howdy, welcome to Texas Hold'em! How many players will be playing? (must be between 2 and 4)");
        }
        return numberOfPlayers;
    }

    public static ArrayList<String> getPlayerNames(Integer playerNumber) {
        ArrayList<String> playerNames = new ArrayList<>();
        for (Integer i=0; i<playerNumber; i++) {
            playerNames.add(UserMessages.getUserTextResponse("What is the name of player " + (i+1) + "?"));
        }
        return playerNames;
    }

    public static ArrayList<PokerPlayer> gameSetup() {
        Integer playerNum = getNumberOfPlayers();
        ArrayList<String> playerNames =getPlayerNames(playerNum);
        Integer numberOfChips = 0;
        while (numberOfChips<50 || numberOfChips>150) {
            numberOfChips = UserMessages.getUserIntegerResponse("How many chips would you like to play with? (must be between 50 and 150)");
        }

        ArrayList<PokerPlayer> pokerPlayers = new ArrayList<>();
        for (Integer i=0; i<playerNum; i++) {
            PokerPlayer player = new PokerPlayer(playerNames.get(i), numberOfChips, i);
            pokerPlayers.add(player);
        }
        return pokerPlayers;
    }

}
