package org.example.ASCIIArt;

import org.example.CardSetup.Card;

public class DrawCards {

    public static void drawCard(Card card) {

        System.out.println(" ----------- ");
        System.out.println("| " + card.getSymbol() + "         |");
        System.out.println("| " + card.getSuite() + "         |");
        System.out.println("|           |");
        System.out.println("|     " + card.getSuite() + "     |");
        System.out.println("|           |");
        System.out.println("|         " + card.getSuite() + " |");
        System.out.println("|         " + card.getSymbol() + " |");
        System.out.println(" ----------- ");
    }
}
