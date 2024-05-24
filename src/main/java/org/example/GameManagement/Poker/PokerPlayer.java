package org.example.GameManagement.Poker;

import org.example.utils.UserMessages;

public class PokerPlayer {

    String name;
    int chips;
    int position;

    public PokerPlayer(String name, int position, int chips) {
        this.name = name;
        this.chips = chips;
        this.position = position;
    }

    public Integer bet() {
        return UserMessages.getUserIntegerResponse("How much would you like to bet? (you have " + this.chips + " chips)");
    }

    public Integer bet(Integer currentBet) {
        Integer bet = currentBet;
        while (bet<currentBet || bet>this.chips) {
            bet = UserMessages.getUserIntegerResponse("How much would you like to bet? (you have " + this.chips + " chips)");
        }
        return bet;
    }

}
