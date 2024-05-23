package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;
import org.example.utils.SortMethods;

import java.util.ArrayList;
import java.util.Collections;

public class PokerHand {

    public ArrayList<Card> pokerHand = new ArrayList<Card>();

    public PokerHand() {
    }

    public PokerHand(ArrayList<Card> cards) {
        this.pokerHand.addAll(cards);
    }

    public void add(Card card) {
        this.pokerHand.add(card);
    }

    public Card removeCard(int index) {
        Card dealtCard = pokerHand.get(index);
        this.pokerHand.remove(index);
        return dealtCard;
    }

    public void sortHand() {
        Collections.sort(this.pokerHand,(a,b) -> a.getValue() - b.getValue());
    }

    public void sortHand(SortMethods sortMethods) {
        switch (sortMethods){
            case suite:
                System.out.println("Sorted by suite: ");
                Collections.sort(this.pokerHand, (a,b) -> {
                    if(a.getSuite().compareTo(b.getSuite()) == 0) {
                        return a.getValue() - b.getValue();
                    }
                    return a.getSuite().compareTo(b.getSuite());
                });
                break;
            case symbol:
                System.out.println("Sorted by symbol: ");
                Collections.sort(this.pokerHand, (a,b) -> {
                    if(a.getSymbol().compareTo(b.getSymbol()) == 0) {
                        return a.getValue() - b.getValue();
                    }
                    return a.getSymbol().compareTo(b.getSymbol());
                });
                break;
        }
        System.out.println(this.pokerHand);
    }

    public void shuffleHand() {
        ArrayList<Card> shuffleDeck = new ArrayList<>();
        while (!this.pokerHand.isEmpty()) {
            int selectIndex = (int) Math.floor(Math.random()* this.pokerHand.size());
            shuffleDeck.add(this.pokerHand.get(selectIndex));
            this.pokerHand.remove(selectIndex);
        }
        this.pokerHand = shuffleDeck;
        System.out.println("Random sort: ");
        System.out.println(this.pokerHand);
        System.out.println(this.pokerHand.size());
    }



    public void printHand() {
        for (Card card : pokerHand) {
            System.out.println(card);
        }
    }

}
