package org.example.ASCIIArt;

import org.example.CardSetup.Card;
import org.example.utils.StringFormatting;

public class DrawCards {

    public static final String colourReset = "\u001b[0m";

    public static final String black = "\u001B[30m";
    public static final String whiteBG = "\u001B[47m";

    public static void drawCard(Card card) {

        System.out.println(black + whiteBG + " ----------- " + colourReset);
        System.out.println(black + whiteBG + StringFormatting.formatSymbolASCII(card.getSymbol()) + colourReset);
        System.out.println(black + whiteBG + "| " + card.getSuite() + "         |" + colourReset);
        System.out.println(black + whiteBG + "|           |" + colourReset);
        System.out.println(black + whiteBG + "|     " + card.getSuite() + "     |" + colourReset);
        System.out.println(black + whiteBG + "|           |" + colourReset);
        System.out.println(black + whiteBG + "|         " + card.getSuite() + " |" + colourReset);
        System.out.println(black + whiteBG + StringFormatting.formatSymbolASCII(card.getSymbol()) + colourReset);
        System.out.println(black + whiteBG + " ----------- " + colourReset);
    }
}
