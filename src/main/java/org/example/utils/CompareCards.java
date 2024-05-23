package org.example.utils;

import org.example.CardSetup.Card;

public class CompareCards {

    public static boolean compareCards(Card cardOne, Card cardTwo) {
        return cardOne.getSuite().equals(cardTwo.getSuite()) || cardOne.getSymbol().equals(cardTwo.getSymbol());
    }
}
