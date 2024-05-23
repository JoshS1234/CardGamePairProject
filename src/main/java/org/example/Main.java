package org.example;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Poker.PokerHand;
import org.example.GameManagement.Poker.PokerHandChecker;
import org.example.GameManagement.Snap.Snap;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Card card = new Card("2", "heart");
//        Card cardTwo = new Card("2", "diamond");
//        Card cardThree = new Card("2", "heart");
//        Card cardFour = new Card("4", "heart");
//        Card cardFive = new Card("2", "heart");
//        Card cardSix = new Card("4", "diamond");
//        //System.out.println(card);
//
//        Deck deck = new Deck();
//        deck.sortDeck();
//        deck.sortDeck(SortMethods.symbol);
//        deck.sortDeck(SortMethods.suite);
//        deck.shuffleDeck();
//        deck.resetDeck();
//        deck.printDeck();
//
//        System.out.println("Compare symbols: " + CompareCards.compareCards(card, cardTwo));
//        System.out.println("Compare suites: " + CompareCards.compareCards(cardThree, cardFour));
//        System.out.println("No comparison: " + CompareCards.compareCards(cardFive, cardSix));


        PokerHand testPokerHand = new PokerHand();
        testPokerHand.add(new Card("A", "heart"));
        testPokerHand.add(new Card("A", "spade"));
        testPokerHand.add(new Card("2", "heart"));
        testPokerHand.add(new Card("2", "club"));
        testPokerHand.add(new Card("3", "heart"));
        testPokerHand.add(new Card("3", "club"));
        testPokerHand.add(new Card("K", "heart"));
        System.out.println(testPokerHand.pokerHand);
        System.out.println(PokerHandChecker.TwoPairCheck(testPokerHand));





//        //Needs to be moved to some kind of command runner
//        Snap snap = new Snap("Snap", "Be quickest to match cards");
//        snap.play();
    }
}