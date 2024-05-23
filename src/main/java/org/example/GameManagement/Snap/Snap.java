package org.example.GameManagement.Snap;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Snap extends Game {

    //Create instance of deck
    private Deck deck = new Deck();
    private Card userCard;
    private Card computerCard;
    private Scanner scanner = new Scanner(System.in);

    //Constructor
    public Snap(String title, String rules) {
        super(title, rules);
    }

    //Overrides from game class
    @Override
    public void play() {
        try {
            handleGameLoop();
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
    }

    @Override
    public boolean playAgain() {
        return false;
    }

    //Shuffle deck and deal cards
    public void handleGameLoop() throws InterruptedException {

        deck.shuffleDeck();
        dealPlayerCard();
        dealComputerCard();

        while (!CompareCards.compareCards(userCard, computerCard, SortMethods.suite))
        {
            dealPlayerCard();
            if(CompareCards.compareCards(userCard, computerCard, SortMethods.suite)) {
                break;
            }
            dealComputerCard();
        }
        System.out.println("CARDS MATCH!");
        long start = System.currentTimeMillis();
        TimeUnit.MILLISECONDS.sleep(500);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        //Sout for testing
        //If(playerSnaps()) {
        //  sout("Snap! You win!")
        //}
        //else {
        // sout("Computer says snap!)
        //}
        System.out.println("User Card: " + userCard + ". Computer card: " + computerCard + ". Cards suite match = " + CompareCards.compareCards(userCard, computerCard, SortMethods.suite));
    }

    //Need a method to listen for player input on match - User Interaction - NOT YET IMPLEMENTED


    //Deal card to player
    public void dealPlayerCard () throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Press enter to take a card");
        scanner.nextLine();
        userCard = deck.dealCard();
        System.out.println("User card: " + userCard);
    }

    //Deal card to computer
    public void dealComputerCard () throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        computerCard = deck.dealCard();
        System.out.println("Computer card: " + computerCard);
    }

}
