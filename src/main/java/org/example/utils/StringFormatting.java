package org.example.utils;

public class StringFormatting {

    public static String formatSymbolASCII(String symbol) {
        String newLine = "| " + symbol + "         |";

        if (symbol.length() > 1) {
            newLine = "| " + symbol + "        |";
        }
        return newLine;
    }
}
