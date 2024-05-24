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
        //Check if game has been played at least once before
        if (!hasPlayed) {
            System.out.println("Playing snap.");
            hasPlayed = true;
            return true;
        } else {
            //Check if player won
            if(!hasLost) {
                //Capture winning enter input
                scanner.nextLine();
            }
            System.out.println("Want to play again? Enter y for yes or n for no: ");
            String userInput = scanner.nextLine();

            //Loop while input isn't y or n
            while (!userInput.equalsIgnoreCase("y") && !userInput.equalsIgnoreCase("n"))
            {
                System.out.println("Sorry I didn't catch that. Please select y or n: ");
                userInput = scanner.nextLine();
            }

            //Return whether input is y or not
            return userInput.equalsIgnoreCase("y");
        }
    }

    //Shuffle deck and deal cards
    public void handleGameLoop() throws InterruptedException {

        //Loop game while playAgain is true
        while(playAgain()) {
            //Setup deck
            deck.resetDeck();
            deck.shuffleDeck();

            //Deal initial two cards
            dealPlayerCard();
            dealComputerCard();

            //Loop while no match is found
            while (!CompareCards.compareCards(userCard, computerCard, SortMethods.suite))
            {
                //Reset deck if it runs out of cards
                if(deck.getDeckSize() == 0) {
                    System.out.println("Resetting deck");
                    deck.resetDeck();
                    deck.shuffleDeck();
                }

                //Deal the player a card
                dealPlayerCard();

                //Check if match found after player draw
                if(CompareCards.compareCards(userCard, computerCard, SortMethods.suite)) {
                    break;
                }

                //Deal the computer a card
                dealComputerCard();

            }

            //Inform player of match
            System.out.println("CARDS MATCH! PRESS ENTER QUICKLY!");

            //Give player x seconds to enter input
            try {
                if (UserInteraction.userCall(2)) {
                    //Player wins if input is entered
                    System.out.println("You win.");
                    hasLost = false;
                } else {
                    //Player loses if input is entered
                    System.out.println("You lose.");
                    hasLost = true;
                }
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }

    //Deal card to player
    public void dealPlayerCard () throws InterruptedException {
        //Wait a second for better match flow
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Press enter to take a card");

        //Wait for player to hit enter before drawing a card
        scanner.nextLine();
        userCard = deck.dealCard();

        //Show card drawn
        System.out.println("User card: " + userCard);
    }

    //Deal card to computer
    public void dealComputerCard () throws InterruptedException {
        //Wait a second for better match flow
        TimeUnit.SECONDS.sleep(1);

        //Deal the computer a card
        computerCard = deck.dealCard();

        //Show card drawn
        System.out.println("Computer card: " + computerCard);
    }
}
