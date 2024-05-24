package org.example.GameManagement.Poker;

import org.example.GameManagement.Game;
import org.example.utils.UserMessages;

import java.util.ArrayList;

public class Poker extends Game {

    int chips;
    ArrayList<PokerPlayer> players = new ArrayList<>();

    public Poker(String title, String rules) {
        super(title, rules);
    }

    @Override
    public void play() {
        System.out.println("play game");
        System.out.println(UserMessages.getUserIntegerResponse("hello"));
    }

    @Override
    public boolean playAgain() {
        return false;
    }


}
