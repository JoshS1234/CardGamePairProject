package org.example.GameManagement.Snap;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.utils.CompareCards;
import org.example.utils.SortMethods;

public class SnapModes {

    public static void playMode(Card playerCard, Card computerCard, Deck deck, SortMethods sortMethod) throws InterruptedException {

        while (!CompareCards.compareCards(playerCard, computerCard, sortMethod))
        {
            //Reset deck if it runs out of cards
            if(deck.getDeckSize() == 0) {
                System.out.println("Resetting deck");
                deck.resetDeck();
                deck.shuffleDeck();
            }

            //Deal the player a card
            playerCard = DealCards.dealPlayerCard(deck);

            //Check if match found after player draw
            if(CompareCards.compareCards(playerCard, computerCard, sortMethod)) {
                break;
            }

            //Deal the computer a card
            computerCard = DealCards.dealComputerCard(deck);

        }
    }
}
