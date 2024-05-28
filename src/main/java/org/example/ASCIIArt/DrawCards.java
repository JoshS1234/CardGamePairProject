package org.example.ASCIIArt;

import org.example.CardSetup.Card;

import java.util.ArrayList;
import java.util.Objects;

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

    public static void drawManyCards(ArrayList<Card> cards) {
        ArrayList<String> cardStringArr = new ArrayList<>();

        String l1="";
        String l2="";
        String l3="";
        String l4="";
        String l5="";
        String l6="";
        String l7="";
        String l8="";
        String l9="";

        for (Card card : cards) {
             l1+=black + whiteBG + " ----------- " + "  "+ colourReset;
             if (Objects.equals(card.getSymbol(), "10")){
                 l2+=black + whiteBG + "| " + card.getSymbol() + "        |" + "  "+ colourReset;
             } else {
                 l2+=black + whiteBG + "| " + card.getSymbol() + "         |" + "  "+ colourReset;
             }
             l3+=black + whiteBG + "| " + card.getSuite() + "         |" + "  "+ colourReset;
             l4+=black + whiteBG + "|           |" + "  "+ colourReset;
             l5+=black + whiteBG + "|     " + card.getSuite() + "     |"+"  "+ colourReset;
             l6+=black + whiteBG + "|           |"+"  "+ colourReset;
             l7+=black + whiteBG + "|         " + card.getSuite() + " |"+"  "+ colourReset;
            if (Objects.equals(card.getSymbol(), "10")){
                l8+=black + whiteBG + "|        " + card.getSymbol() + " |" + "  "+ colourReset;
            } else {
                l8+=black + whiteBG + "|         " + card.getSymbol() + " |" + "  "+ colourReset;
            }
             l9+=black + whiteBG + " ----------- "+"  "+ colourReset;
        }

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println(l4);
        System.out.println(l5);
        System.out.println(l6);
        System.out.println(l7);
        System.out.println(l8);
        System.out.println(l9);
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
