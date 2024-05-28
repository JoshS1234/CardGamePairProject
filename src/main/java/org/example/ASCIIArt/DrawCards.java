package org.example.ASCIIArt;

import org.example.CardSetup.Card;

public class DrawCards {

    public static final String colourReset = "\u001b[0m";

    public static final String black = "\u001B[30m";
    public static final String whiteBG = "\u001B[47m";

    public static void drawCard(Card card) {

        System.out.println(black + whiteBG + " ----------- " + colourReset);
        System.out.println(black + whiteBG + formatSymbolASCII(card.getSymbol(), 1) + colourReset);
        System.out.println(black + whiteBG + "| " + card.getSuite() + "         |" + colourReset);
        System.out.println(black + whiteBG + "|           |" + colourReset);
        System.out.println(black + whiteBG + "|     " + card.getSuite() + "     |" + colourReset);
        System.out.println(black + whiteBG + "|           |" + colourReset);
        System.out.println(black + whiteBG + "|         " + card.getSuite() + " |" + colourReset);
        System.out.println(black + whiteBG + formatSymbolASCII(card.getSymbol(), 2) + colourReset);
        System.out.println(black + whiteBG + " ----------- " + colourReset);
    }

    public static String formatSymbolASCII(String symbol, int position) {
        String topLine = "| " + symbol + "         |";
        String bottomLine = "|         " + symbol + " |";

        switch (position) {
            case 1:
                if (symbol.length() > 1) {
                    topLine = "| " + symbol + "        |";
                }
                return topLine;
            case 2:
                if (symbol.length() > 1) {
                    bottomLine = "|        " + symbol + " |";
                }
                return bottomLine;
        }
        return topLine;
    }
}

