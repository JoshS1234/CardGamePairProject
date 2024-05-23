package org.example.GameManagement.Snap;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.utils.CompareCards;

public class Snap extends Game {

    private Card cardOne;
    private Card cardTwo;
    private Deck deck = new Deck();

    public Snap(String title, String rules) {
        super(title, rules);
    }

    @Override
    public void play() {
        dealCards();
    }

    @Override
    public boolean playAgain() {
        return false;
    }

    //Need a player and a computer

    //Need to use deal card method to grab cards
    public void dealCards() {
        cardOne = deck.dealCard();
        cardTwo = deck.dealCard();
        System.out.println("Cards match = " + CompareCards.compareCards(cardOne, cardTwo));
    }

    //Need a method to check if current card and last card match

    //Need a method to listen for player input on match

}
