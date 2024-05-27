package org.example.GameManagement.Snap;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;
import org.example.utils.UserInteraction;
import org.example.utils.UserMessages;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Snap extends Game {

    //Create instance of deck
    private Deck deck = new Deck();
    private Card playerCard;
    private Card computerCard;
    private SortMethods gameMode;
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
            System.out.println("Error with play again");
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
    public void handleGameLoop() throws InterruptedException{

        //Loop game while playAgain is true
        while(playAgain()) {

            System.out.println("Available modes are: ");
            System.out.println("Match by suite");
            System.out.println("Match by symbol");
            String userResponse = UserMessages.getUserTextResponse("Which mode would you like to play? Type Suite for option one and Symbol for option 2: ");

            while (!userResponse.equalsIgnoreCase("Suite") && !userResponse.equalsIgnoreCase("Symbol")) {
                System.out.println("Sorry I didn't catch that.");
                userResponse = UserMessages.getUserTextResponse("Which mode would you like to play? Type Suite for option one and Symbol for option 2: ");
            }

            switch (userResponse) {
                case "suite" :
                    gameMode = SortMethods.suite;
                    break;
                case "symbol" :
                    gameMode = SortMethods.symbol;
                    break;
            }

            //Setup deck
            deck.resetDeck();
            deck.shuffleDeck();

            //Deal initial two cards
            playerCard = DealCards.dealPlayerCard(deck);
            computerCard = DealCards.dealComputerCard(deck);

            System.out.println("Card match: " + CompareCards.compareCards(playerCard, computerCard, gameMode));

            //Loop while no match is found
            if (!CompareCards.compareCards(playerCard, computerCard, gameMode))
            {
                SnapModes.playMode(playerCard, computerCard, deck, gameMode);
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
                System.out.println("Error here");
            }
        }
    }


}
