package org.example;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Poker.HandComparer;
import org.example.GameManagement.Poker.PokerHand;
import org.example.GameManagement.Poker.PokerHandChecker;
import org.example.GameManagement.Snap.Snap;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Card card = new Card("2", "heart");
        Card cardTwo = new Card("2", "diamond");
        Card cardThree = new Card("2", "heart");
        Card cardFour = new Card("4", "heart");
        Card cardFive = new Card("2", "heart");
        Card cardSix = new Card("4", "diamond");
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


        PokerHand testPokerHand1 = new PokerHand();
        testPokerHand1.add(new Card("3", "spade"));
        testPokerHand1.add(new Card("3", "heart"));
        testPokerHand1.add(new Card("3", "club"));
        testPokerHand1.add(new Card("5", "heart"));
        testPokerHand1.add(new Card("7", "heart"));
        testPokerHand1.add(new Card("10", "club"));
        testPokerHand1.add(new Card("K", "heart"));

        PokerHand testPokerHand2 = new PokerHand();
        testPokerHand2.add(new Card("A", "spade"));
        testPokerHand2.add(new Card("3", "heart"));
        testPokerHand2.add(new Card("3", "heart"));
        testPokerHand2.add(new Card("4", "club"));
        testPokerHand2.add(new Card("5", "heart"));
        testPokerHand2.add(new Card("10", "club"));
        testPokerHand2.add(new Card("K", "heart"));

        HandComparer handComparer = new HandComparer(testPokerHand1, testPokerHand2);
        System.out.println(handComparer.compareTwoHands());





        //Needs to be moved to some kind of command runner
        Snap snap = new Snap("Snap", "Be quickest to match cards");
        snap.play();
    }
}