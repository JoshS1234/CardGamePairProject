package org.example.CardSetup;

import org.example.utils.SortMethods;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private String[] suites = new String[] {"♣", "♦", "❤", "♠" };
    private String[] symbols = new String[]  {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private Integer[] values = new Integer[]  {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    private Card testCard;
    private ArrayList<Card> deck = new ArrayList<>();

    @BeforeEach
    void initialSetup() {
        for (String s : suites) {
            for (String symbol : symbols) {
                deck.add(new Card(symbol, s));
            }
        }
    }

    @Test
    @DisplayName("Testing if a card is correctly dealt")
    void dealCard_validDeck_dealCorrectCard() {
        testCard = deck.get(0);
        deck.remove(0);

        String str = testCard.getSymbol();

        testCard = deck.get(0);
        deck.remove(0);

        String str2 = testCard.getSymbol();

        assertNotEquals(str2, str);
    }


    @Test
    @DisplayName("Testing the deck is correctly sorted")
    void sortDeck_validDeck_sortByValue() {

        deck.sort((a,b) -> a.getValue() - b.getValue());

        testCard = deck.get(0);
        deck.remove(0);

        String str = testCard.getSymbol();
        String str2 = testCard.getSuite();

        testCard = deck.get(0);

        String str3 = testCard.getSymbol();
        String str4 = testCard.getSuite();

        assertEquals("2", str);
        assertEquals("♣", str2);
        assertEquals("2", str3);
        assertEquals("♦", str4);
    }

    @Test
    @DisplayName("Testing the deck is correctly sorted by passed in SortMethod")
    void sortDeck_validDeck_sortBySymbol() {

        SortMethods sortMethod = SortMethods.suite;

        switch (sortMethod){
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

        testCard = deck.get(0);
        deck.remove(0);

        String str = testCard.getSymbol();
        String str2 = testCard.getSuite();

        testCard = deck.get(0);

        String str3 = testCard.getSymbol();
        String str4 = testCard.getSuite();

        assertEquals("2", str);
        assertEquals("♠", str2);
        assertEquals("3", str3);
        assertEquals("♠", str4);
    }

    @Test
    @DisplayName("Testing the deck correctly resets")
    void resetDeck_validDeck_deckResetsToInitialState() {

        testCard = deck.get(0);
        deck.remove(0);

        String str = testCard.getSymbol();
        String str2 = testCard.getSuite();

        ArrayList<Card> freshDeck = new ArrayList<>();
        for (String s : suites) {
            for (int i=0; i<symbols.length; i++) {
                freshDeck.add(new Card(symbols[i], s));
            }
        }
        deck = freshDeck;

        testCard = deck.get(0);

        String str3 = testCard.getSymbol();
        String str4 = testCard.getSuite();

        assertEquals(str, str3);
        assertEquals(str2, str4);
    }

    @Test
    @DisplayName("Testing the deck has the correct size of 52")
    void getDeckSize_validDeck_deckSizeEqualsFiftyEight() {
        int deckSize = deck.size();

        assertEquals(52, deckSize);
    }

}