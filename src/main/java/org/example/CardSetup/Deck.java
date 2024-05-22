package org.example.CardSetup;

import org.example.utils.SortMethods;

import java.util.ArrayList;
import java.util.Collections;

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


}
