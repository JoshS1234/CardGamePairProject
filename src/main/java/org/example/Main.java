package org.example;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Card card = new Card("2", "heart", 2);
        System.out.println(card);

        Deck deck = new Deck();
    }
}