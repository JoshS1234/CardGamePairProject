package org.example.GameManagement.Snap;

import org.example.ASCIIArt.DrawCards;
import org.example.ASCIIArt.DrawDealer;
import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;
import org.example.utils.UserInteraction;

import java.util.Scanner;

public class Snap extends Game {

    //Create instance of deck
    private final Deck deck = new Deck();
    private final Scanner scanner = new Scanner(System.in);
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
            System.out.println("Want to play again? (y/n)");
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

        DrawDealer.drawDealer();

        //Loop game while playAgain is true
        while(playAgain()) {

            //Select game mode to play
            SortMethods gameMode = SnapModeSelector.snapModeSelector();

            //Setup deck
            deck.resetDeck();
            deck.shuffleDeck();

            //Deal initial two cards
            Card playerCard = DealCards.dealPlayerCard(deck);
            DrawCards.drawCard(playerCard);
            Card computerCard = DealCards.dealComputerCard(deck);

            //Check that the gameMode will not be null
            assert gameMode != null;

            //Run loop method if cards don't match
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
                    System.out.println("You win!!!!!!!!");
                    hasLost = false;
                } else {
                    //Player loses if input is entered
                    System.out.println("You lose.");
                    hasLost = true;
                }
            } catch (Exception e) {
                System.out.println("Error with userCall.");
            }
        }
    }
}
