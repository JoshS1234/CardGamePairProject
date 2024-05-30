package org.example.GameManagement.Snap;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class DealCardsTest {

    private Scanner testScanner = new Scanner(System.in);
    private Card testCard;
    private Deck deck = new Deck();

    @BeforeEach
    void setup() {
        testCard = deck.dealCard();
    }

    @Test
    void dealPlayerCard() {

        String str = testCard.getSymbol();

        testCard = deck.dealCard();

        String str2 = testCard.getSymbol();

        assertNotEquals(str2, str);
    }

}