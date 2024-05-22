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

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.symbol + " of " + this.suite + " has value of " + this.value;
    }

}
