package org.example;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.utils.SortMethods;

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

    }
}