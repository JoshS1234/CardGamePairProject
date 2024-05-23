package org.example;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Poker.PokerHandChecker;
import org.example.GameManagement.Snap.Snap;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Card card = new Card("2", "heart", 2);
        Card cardTwo = new Card("2", "diamond", 2);
        Card cardThree = new Card("2", "heart", 2);
        Card cardFour = new Card("4", "heart", 2);
        Card cardFive = new Card("2", "heart", 2);
        Card cardSix = new Card("4", "diamond", 2);
        //System.out.println(card);

        Deck deck = new Deck();
        deck.sortDeck();
        deck.sortDeck(SortMethods.symbol);
        deck.sortDeck(SortMethods.suite);
        deck.shuffleDeck();
        deck.resetDeck();
        deck.printDeck();

        System.out.println("Compare symbols: " + CompareCards.compareCards(card, cardTwo));
        System.out.println("Compare suites: " + CompareCards.compareCards(cardThree, cardFour));
        System.out.println("No comparison: " + CompareCards.compareCards(cardFive, cardSix));


        deck.shuffleDeck();
        ArrayList<Card> testPokerHand = new ArrayList<>();
        for (int i=0; i<7; i++) {
            testPokerHand.add(deck.dealCard());
        }
        System.out.println(testPokerHand);
        System.out.println(PokerHandChecker.splitByValue(testPokerHand));
        System.out.println(PokerHandChecker.splitBySuite(testPokerHand));




        //Needs to be moved to some kind of command runner
        Snap snap = new Snap("Snap", "Be quickest to match cards");
        snap.play();
    }
}