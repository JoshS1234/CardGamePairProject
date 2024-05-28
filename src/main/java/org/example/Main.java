package org.example;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.GameManagement.GameSelector;
import org.example.GameManagement.Poker.HandComparer;
import org.example.GameManagement.Poker.Poker;
import org.example.GameManagement.Poker.PokerHand;
import org.example.GameManagement.Poker.PokerHandChecker;
import org.example.GameManagement.Snap.Snap;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;

import static org.example.ASCIIArt.DrawCards.drawManyCards;


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




        Deck deck = new Deck();
        deck.shuffleDeck();
        PokerHand testPokerHand1 = new PokerHand();
        testPokerHand1.add(new Card("8", "club"));
        testPokerHand1.add(new Card("8", "heart"));
        testPokerHand1.add(new Card("9", "club"));
        testPokerHand1.add(new Card("8", "diamond"));
        testPokerHand1.add(new Card("J", "spade"));
        testPokerHand1.add(new Card("6", "heart"));
        testPokerHand1.add(new Card("K", "heart"));
//        for (int i=0; i<7; i++) {
//            Card card = deck.dealCard();
//            testPokerHand1.add(card);
//        }
//        testPokerHand1.printHand();
//        System.out.println("---------------");
        PokerHand testPokerHand2 = new PokerHand();
//        for (int i=0; i<7; i++) {
//            Card card = deck.dealCard();
//            testPokerHand2.add(card);
//        }
//        drawManyCards(testPokerHand2.pokerHand);
//        testPokerHand2.printHand();
        testPokerHand2.add(new Card("8", "club"));
        testPokerHand2.add(new Card("8", "heart"));
        testPokerHand2.add(new Card("9", "club"));
        testPokerHand2.add(new Card("8", "diamond"));
        testPokerHand2.add(new Card("J", "spade"));
        testPokerHand2.add(new Card("6", "heart"));
        testPokerHand2.add(new Card("Q", "heart"));
//
        System.out.println(HandComparer.compareTwoHands(testPokerHand1, testPokerHand2).get("result"));
        System.out.println(HandComparer.compareTwoHands(testPokerHand1, testPokerHand2).get("reason"));


//        Poker poker = new Poker("Poker", "Try not to lose all your chips");
//        poker.play();

        GameSelector gameSelector = new GameSelector();
        while (gameSelector.startGameSelector()) {
            Game game = gameSelector.chooseGame();
            game.play();
        }

//        //Needs to be moved to some kind of command runner
//        Snap snap = new Snap("Snap", "Be quickest to match cards");
//        snap.play();
        //Needs to be moved to some kind of command runner
//        Snap snap = new Snap("Snap", "Be quickest to match cards");
//        snap.play();
    }
}