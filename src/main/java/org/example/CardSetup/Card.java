package org.example.CardSetup;

public class Card {


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
