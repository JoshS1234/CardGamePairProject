package org.example.GameManagement.Snap;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;
import org.example.utils.UserInteraction;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Snap extends Game {

    //Create instance of deck
    private Deck deck = new Deck();
    private Card userCard;
    private Card computerCard;
    private Scanner scanner = new Scanner(System.in);
    private boolean hasPlayed = false;
    private boolean hasLost;

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
    public boolean playAgain(){
        if (!hasPlayed) {
            System.out.println("Playing game.");
            hasPlayed = true;
            return true;
        } else {
            if(!hasLost) scanner.nextLine();
            System.out.println("Want to play again?");
            String userInput = "";
            userInput = scanner.nextLine();
            while (!userInput.equalsIgnoreCase("y") && !userInput.equalsIgnoreCase("n"))
            {
                System.out.println("Sorry I didn't catch that. Please select y or n: ");
                userInput = scanner.nextLine();
            }

            return userInput.equalsIgnoreCase("y");
        }
    }

    //Shuffle deck and deal cards
    public void handleGameLoop() throws InterruptedException {

        while(playAgain()) {
            deck.shuffleDeck();
            dealPlayerCard();
            dealComputerCard();

            while (!CompareCards.compareCards(userCard, computerCard, SortMethods.suite))
            {
                System.out.println();
                dealPlayerCard();
                if(CompareCards.compareCards(userCard, computerCard, SortMethods.suite)) {
                    break;
                }
                dealComputerCard();
            }

            System.out.println("CARDS MATCH! PRESS ENTER QUICKLY!");

            try {
                if (UserInteraction.userCall(2)) {
                    System.out.println("You win.");
                    hasLost = false;
                } else {
                    System.out.println("You lose.");
                    hasLost = true;
                }
            } catch (Exception e) {
                System.out.println("Error");
            }

            System.out.println("User Card: " + userCard + ". Computer card: " + computerCard + ". Cards suite match = " + CompareCards.compareCards(userCard, computerCard, SortMethods.suite));
        }
    }

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
