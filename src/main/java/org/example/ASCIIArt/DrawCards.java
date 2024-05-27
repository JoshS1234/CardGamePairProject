package org.example.ASCIIArt;

public class DrawCards {

    public static void drawCard(String suite, String symbol) {

        System.out.println(" ----------- ");
        System.out.println("| " + symbol + "         |");
        System.out.println("| " + suite + "         |");
        System.out.println("|           |");
        System.out.println("|     " + symbol + "     |");
        System.out.println("|           |");
        System.out.println("|         " + symbol + " |");
        System.out.println("|         " + suite + " |");
        System.out.println(" ----------- ");
    }
}
