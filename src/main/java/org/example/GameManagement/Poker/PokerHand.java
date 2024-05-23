package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;
import org.example.utils.SortMethods;

import java.util.ArrayList;
import java.util.Collections;

public class PokerHand {

    ArrayList<Card> pokerHand = new ArrayList<Card>();

    public PokerHand(ArrayList<Card> cards) {
        pokerHand.addAll(cards);
        System.out.println(pokerHand);
    }

    public void sortHand() {
        System.out.println("Sorted by value: ");
        pokerHand.sort((a,b) -> a.getValue() - b.getValue());
        System.out.println(pokerHand);
    }

    public void sortHand(SortMethods sortMethods) {
        switch (sortMethods){
            case suite:
                System.out.println("Sorted by suite: ");
                Collections.sort(pokerHand, (a,b) -> {
                    if(a.getSuite().compareTo(b.getSuite()) == 0) {
                        return a.getValue() - b.getValue();
                    }
                    return a.getSuite().compareTo(b.getSuite());
                });
                break;
            case symbol:
                System.out.println("Sorted by symbol: ");
                Collections.sort(pokerHand, (a,b) -> {
                    if(a.getSymbol().compareTo(b.getSymbol()) == 0) {
                        return a.getValue() - b.getValue();
                    }
                    return a.getSymbol().compareTo(b.getSymbol());
                });
                break;
        }
        System.out.println(pokerHand);
    }

    public void shuffleHand() {
        ArrayList<Card> shuffleDeck = new ArrayList<>();
        while (!pokerHand.isEmpty()) {
            int selectIndex = (int) Math.floor(Math.random()* pokerHand.size());
            shuffleDeck.add(pokerHand.get(selectIndex));
            pokerHand.remove(selectIndex);
        }
        pokerHand = shuffleDeck;
        System.out.println("Random sort: ");
        System.out.println(pokerHand);
        System.out.println(pokerHand.size());
    }



    public void printHand() {
        for (Card card : pokerHand) {
            System.out.println(card);
        }
    }

}
