package org.example.GameManagement.Snap;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DealCards {

    //Deal card to player
    public static Card dealPlayerCard(Deck deck) throws InterruptedException {

        //Create player scanner and variable to hold player card
        Scanner playerScanner = new Scanner(System.in);
        Card playerCard;

        //Wait a second for better match flow
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Press enter to take a card");

        //Wait for player to hit enter before drawing a card
        playerScanner.nextLine();
        playerCard = deck.dealCard();

        //Show card drawn
        System.out.println("Player card: " + playerCard);

        return playerCard;
    }

    //Deal card to computer
    public static Card dealComputerCard (Deck deck) throws InterruptedException {

        //Create variable to hold computer card
        Card computerCard;

        //Wait a second for better match flow
        TimeUnit.SECONDS.sleep(1);

        //Deal the computer a card
        computerCard = deck.dealCard();

        //Show card drawn
        System.out.println("Computer card: " + computerCard);

        return computerCard;
    }
}
