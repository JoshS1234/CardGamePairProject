package org.example.CardSetup;

public class Card {

    private String suite;
    private String symbol;
    private Integer value;

    public Card(String symbol, String suite) {
        this.suite = suite;
        this.symbol = symbol;
        this.value=assignValues(symbol);
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

    private int assignValues(String symbol) {
        symbol=symbol.toUpperCase();
        switch(symbol) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return this.symbol + " of " + this.suite;
    }

}
