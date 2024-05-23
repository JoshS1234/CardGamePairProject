package org.example.GameManagement.Snap;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;

public class Snap extends Game {

    //Create instance of deck
    private Deck deck = new Deck();

    //Constructor
    public Snap(String title, String rules) {
        super(title, rules);
    }

    //Overrides from game class
    @Override
    public void play() {
        dealCards();
    }

    @Override
    public boolean playAgain() {
        return false;
    }

    //Shuffle deck and deal cards
    public void dealCards() {
        deck.shuffleDeck();
        Card cardOne = deck.dealCard();
        Card cardTwo = deck.dealCard();

        //Sout for testing
        System.out.println("Card one: " + cardOne + ". Card two: " + cardTwo + ". Cards suite match = " + CompareCards.compareCards(cardOne, cardTwo, SortMethods.suite));
    }

    //Need a method to check if current card and last card match - NOT YET IMPLEMENTED
    //Store dealt card as current card
    //If no match store as last card and deal new card
    //Repeat

    //Need a method to listen for player input on match - User Interaction - NOT YET IMPLEMENTED

}
