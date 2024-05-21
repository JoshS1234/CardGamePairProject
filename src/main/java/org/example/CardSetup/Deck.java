package org.example.CardSetup;

import java.util.ArrayList;

public class Deck {
    private String[] suites = new String[] {"♣", "♦", "❤", "♠" };
    private String[] symbols = new String[]  {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private Integer[] values = new Integer[]  {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        for (String s : suites) {
            for (int i=0; i<symbols.length; i++) {
                deck.add(new Card(symbols[i], s, values[i]));
            }
        }
        System.out.println(deck);
    }
}
