package org.example;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Poker.PokerHandChecker;
import org.example.utils.SortMethods;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Card card = new Card("2", "heart", 2);
        //System.out.println(card);

        Deck deck = new Deck();
        deck.sortDeck();
        deck.sortDeck(SortMethods.symbol);
        deck.sortDeck(SortMethods.suite);
        deck.shuffleDeck();
        deck.resetDeck();
        deck.printDeck();



        deck.shuffleDeck();
        ArrayList<Card> testPokerHand = new ArrayList<>();
        for (int i=0; i<7; i++) {
            testPokerHand.add(deck.dealCard());
        }
        System.out.println(testPokerHand);
        System.out.println(PokerHandChecker.splitByValue(testPokerHand));
        System.out.println(PokerHandChecker.splitBySuite(testPokerHand));




    }
}