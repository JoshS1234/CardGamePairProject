package org.example.GameManagement;

import org.example.GameManagement.Poker.Poker;
import org.example.GameManagement.Snap.Snap;
import org.example.utils.UserMessages;

public class GameSelector implements ChooseGame {

    String gameToPlay;


    @Override
    public void printGames() {
        System.out.println("poker");
        System.out.println("snap");
    }

    public boolean startGameSelector() {
        String play = "";

        while (!play.equalsIgnoreCase("y") && !play.equalsIgnoreCase("n")) {
            play = UserMessages.getUserTextResponse("Would you like to play a game? (y/n)");
        }


        switch (play) {
            case "y":
                return true;
            case "n":
                return false;
        }
        return false;
    }

    @Override
    public Game chooseGame() {
        String game = "";

        while (!game.equalsIgnoreCase("poker") && !game.equalsIgnoreCase("snap")) {
            System.out.println("Options for games are:");
            printGames();
            game = UserMessages.getUserTextResponse("Which game would you like to play? ");
        }

        Game chosenGame = new Snap("Snap", "Be quickest to match cards");

        switch (game) {
            case "snap":
                chosenGame = new Snap("Snap", "Be quickest to match cards");
                break;
            case "poker":
                chosenGame = new Poker("Poker", "Keep your chips!");
                break;
        }

        return chosenGame;
    }

    public void launchGame(Game gameToPlay) {
        gameToPlay.play();
    }
}
