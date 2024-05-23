package org.example.utils;

import org.example.CardSetup.Card;

public class CompareCards {

    public static boolean compareCards(Card cardOne, Card cardTwo) {
        return cardOne.getSuite().equals(cardTwo.getSuite()) || cardOne.getSymbol().equals(cardTwo.getSymbol());
    }

    public static boolean compareCards(Card cardOne, Card cardTwo, SortMethods sortMethod) {
        switch (sortMethod) {
            case suite:
                return cardOne.getSuite().equals(cardTwo.getSuite());
            case symbol:
                return cardOne.getSymbol().equals(cardTwo.getSymbol());
        }
        return false;
    }
}
