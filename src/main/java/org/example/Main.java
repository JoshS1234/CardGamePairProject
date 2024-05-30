package org.example;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.GameManagement.GameSelector;
import org.example.GameManagement.Poker.HandComparer;
import org.example.GameManagement.Poker.Poker;
import org.example.GameManagement.Poker.PokerHand;
import org.example.GameManagement.Poker.PokerHandChecker;
import org.example.GameManagement.Snap.Snap;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;

import static org.example.ASCIIArt.DrawCards.drawManyCards;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GameSelector gameSelector = new GameSelector();
        while (gameSelector.startGameSelector()) {
            Game game = gameSelector.chooseGame();
            game.play();
        }

    }
}