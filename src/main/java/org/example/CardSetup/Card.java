package org.example.CardSetup;

public class Card {
    private String[] suites = new String[] {"♣", "♦", "❤", "♠" };
    private String[] symbols = new String[]  {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private Integer[] values = new Integer[]  {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    private String suite;
    private String symbol;
    private Integer value;

    public Card(String symbol, String suite, Integer value) {
        this.suite = suite;
        this.symbol = symbol;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.symbol + " of " + this.suite + "s" + " has value of " + this.value;
    }

}
