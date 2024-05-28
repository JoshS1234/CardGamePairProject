package org.example.ASCIIArt;

import org.example.CardSetup.Card;

import java.util.ArrayList;
import java.util.Objects;

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
             l1+=" ----------- " + "  ";
             if (Objects.equals(card.getSymbol(), "10")){
                 l2+="| " + card.getSymbol() + "        |" + "  ";
             } else {
                 l2+="| " + card.getSymbol() + "         |" + "  ";
             }
             l3+="| " + card.getSuite() + "         |" + "  ";
             l4+="|           |" + "  ";
             l5+="|     " + card.getSuite() + "     |"+"  ";
             l6+="|           |"+"  ";
             l7+="|         " + card.getSuite() + " |"+"  ";
            if (Objects.equals(card.getSymbol(), "10")){
                l8+="|        " + card.getSymbol() + " |" + "  ";
            } else {
                l8+="|         " + card.getSymbol() + " |" + "  ";
            }
             l9+=" ----------- "+"  ";
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
}
