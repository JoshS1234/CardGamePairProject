package org.example.CardSetup;

import org.example.utils.SortMethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    private String[] suites = new String[] {"♣", "♦", "❤", "♠" };
    private String[] symbols = new String[]  {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private Integer[] values = new Integer[]  {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        for (String s : suites) {
            for (int i=0; i<symbols.length; i++) {
                deck.add(new Card(symbols[i], s));
            }
        }
    }

    public Card dealCard() {
        Card dealtCard = deck.get(0);
        deck.remove(0);
        return dealtCard;
    }

    public void sortDeck() {
        System.out.println("Sorted by value: ");
        deck.sort((a,b) -> a.getValue() - b.getValue());
        System.out.println(deck);
    }

    public void sortDeck(SortMethods sortMethods) {
        switch (sortMethods){
            case suite:
                System.out.println("Sorted by suite: ");
                Collections.sort(deck, (a,b) -> {
                    if(a.getSuite().compareTo(b.getSuite()) == 0) {
                        return a.getValue() - b.getValue();
                    }
                    return a.getSuite().compareTo(b.getSuite());
                });
                break;
            case symbol:
                System.out.println("Sorted by symbol: ");
                Collections.sort(deck, (a,b) -> {
                    if(a.getSymbol().compareTo(b.getSymbol()) == 0) {
                        return a.getValue() - b.getValue();
                    }
                    return a.getSymbol().compareTo(b.getSymbol());
                });
                break;
        }
        System.out.println(deck);
    }

    public void shuffleDeck() {
        ArrayList<Card> shuffleDeck = new ArrayList<>();
        while (!deck.isEmpty()) {
            int selectIndex = (int) Math.floor(Math.random()* deck.size());
            shuffleDeck.add(deck.get(selectIndex));
            deck.remove(selectIndex);
        }
        deck = shuffleDeck;
//        System.out.println("Random sort: ");
//        System.out.println(deck);
//        System.out.println(deck.size());
    }

    public void resetDeck() {
        ArrayList<Card> freshDeck = new ArrayList<>();
        for (String s : suites) {
            for (int i=0; i<symbols.length; i++) {
                freshDeck.add(new Card(symbols[i], s));
            }
        }
        deck = freshDeck;
        System.out.println(deck);
    }

    public void printDeck() {
        for (Card card : deck) {
            System.out.println(card);
        }
    }

    public int getDeckSize() {
        return deck.size();
    }
}
