package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.utils.UserMessages;

import java.util.ArrayList;
import java.util.HashMap;

import static org.example.ASCIIArt.DrawCards.drawManyCards;

public class Poker extends Game {

    ArrayList<PokerPlayer> players = new ArrayList<>();
    ArrayList<PokerPlayer> playersInTheRound;
    int positionOnTable = 0;
    int startingPositionOnTable = 0;
    int currentPot = 0;
    Deck deck = new Deck();
    ArrayList<Card> centralCards = new ArrayList<>();
    ArrayList<PokerPlayer> currentWinners = new ArrayList<>();
    String currentRoundReasonForWin;
    boolean finalTurn=false;


    public Poker(String title, String rules) {
        super(title, rules);
        deck.shuffleDeck();
    }

    public void resetRound() {
        currentWinners = new ArrayList<>();
        deck = new Deck();
        deck.shuffleDeck();
        centralCards = new ArrayList<>();
        playersInTheRound = new ArrayList<>();
        playersInTheRound.addAll(players);
        currentPot = 0;
        startingPositionOnTable = (startingPositionOnTable + 1) % players.size();
        for (PokerPlayer player : players) {
            player.pokerhand = new PokerHand();
        }
        dealCardsToPlayers();
    }

    public void resetWholeGame() {
        currentWinners = new ArrayList<>();
        deck = new Deck();
        deck.shuffleDeck();
        centralCards = new ArrayList<>();
        players = new ArrayList<>();
        playersInTheRound = new ArrayList<>();
        currentPot = 0;
        positionOnTable = 0;
        startingPositionOnTable = 0;
    }

    @Override
    public void play() {
        players = PokerUserMessages.gameSetup();
        playersInTheRound = new ArrayList<>();
        playersInTheRound.addAll(players);
        dealCardsToPlayers();

        boolean hasGameWinner = false;
        boolean hasRoundWinner;

        while (!hasGameWinner) {
            hasRoundWinner = false;
            while (!hasRoundWinner) {
                if (!playRound().isEmpty()) {
                    hasRoundWinner = true;
                } else {
                    System.out.println("something went wrong");
                }
            }
            roundSummary();
            eliminatePlayers();
            resetRound();
            if (players.size() == 1) {
                hasGameWinner = true;
            } else {
                UserMessages.getUserPressEnterResponse("Press enter for next round");
            }

        }
        PokerUserMessages.finalWinnerAnnouncement(players.get(0));
        if (playAgain()) {
            resetWholeGame();
            play();
        }

    }

    public void dealCardsToPlayers() {
        for (PokerPlayer player : players) {
            player.pokerhand.add(deck.dealCard());
            player.pokerhand.add(deck.dealCard());
        }
    }

    public boolean playBettingRound(boolean first) {

        if (first) {
            playersInTheRound = new ArrayList<>();
            playersInTheRound.addAll(players);
        }

        positionOnTable = startingPositionOnTable;
        int poorestPlayerChips = 901;
        for (PokerPlayer player : players) {
            if (player.getChips() < poorestPlayerChips) {
                poorestPlayerChips = player.getChips();
            }
        }

        int currentBet = 0;
        boolean allBet = false;

        for(PokerPlayer player : playersInTheRound) {
            player.setHasBetThisRound(false);
        }

        while (!allBet) {
            if(onePlayerLeftCheck()){
                currentWinners.add(playersInTheRound.get(0));
                return true;
            }

            HashMap<String, Object> playerChoice = playersInTheRound.get(positionOnTable).playerTurn(currentBet, centralCards, poorestPlayerChips);
            if (playerChoice.get("choice") == "fold") {
                playersInTheRound.remove(positionOnTable);
                positionOnTable=positionOnTable%playersInTheRound.size();
            } else if (playerChoice.get("choice") == "call") {
                currentPot += playersInTheRound.get(positionOnTable).call(currentBet);
                playersInTheRound.get(positionOnTable).setHasBetThisRound(true);
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else if (playerChoice.get("choice") == "raise") {
                int raiseAmount = playersInTheRound.get(positionOnTable).raise(currentBet, poorestPlayerChips);
                currentPot += raiseAmount;
                currentBet = raiseAmount;
                for(PokerPlayer player : playersInTheRound) {
                    player.setHasBetThisRound(false);
                }
                playersInTheRound.get(positionOnTable).setHasBetThisRound(true);
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else {
                System.out.println("Something went wrong");
            }

            //checks if next player has bet already, if so, ends the betting round
            if (playersInTheRound.get(positionOnTable).isHasBetThisRound()) {
                allBet=true;
            }
        }

        if(onePlayerLeftCheck()){
            currentWinners.add(playersInTheRound.get(0));
            return true;
        }
        return false;
    }

    public ArrayList<PokerPlayer> playRound() {

        finalTurn=false;

        //first betting round
        PokerUserMessages.firstRoundAnnouncement();
        if (playBettingRound(true)) {
            return currentWinners;
        }
        resetBets();

        //add 3 cards, then second round of betting
        centralCards.add(deck.dealCard());
        centralCards.add(deck.dealCard());
        centralCards.add(deck.dealCard());
        PokerUserMessages.bettingRoundAnnouncement(centralCards);
        if (playBettingRound(false)) {
            return currentWinners;
        }
        resetBets();


        //add 1 card, then third round of betting
        centralCards.add(deck.dealCard());
        PokerUserMessages.bettingRoundAnnouncement(centralCards);
        if (playBettingRound(false)) {
            return currentWinners;
        }
        resetBets();


        //add 1 card, then final round of betting
        centralCards.add(deck.dealCard());
        PokerUserMessages.bettingRoundAnnouncement(centralCards);
        if (playBettingRound(false)) {
            return currentWinners;
        }
        resetBets();


        //decide the winner (if there has not been one already)
        finalTurn=true;
        return decideWinner();

    }

    public void resetBets() {
        for (PokerPlayer player : players) {
            player.setPlayerCurrentBet(0);
        }
    }

    public void roundSummary() {
        System.out.println("------------------");
        System.out.println("Round is finished");
        System.out.println("Winner(s) were: ");
        int winnings = (currentPot / currentWinners.size());
        if (finalTurn) {
            for (PokerPlayer winner : currentWinners) {
                System.out.println(winner.getName() + " with: " + currentRoundReasonForWin);
                if (winner.totalHandWithCentralCards.pokerHand.isEmpty()) {
                    drawManyCards(winner.pokerhand.pokerHand);
                } else{
                    drawManyCards(winner.totalHandWithCentralCards.pokerHand);
                }
            }
        } else {
            for (PokerPlayer winner : currentWinners) {
                System.out.println(winner.getName());
            }
        }
        System.out.println("They won " + winnings);
        for (PokerPlayer player : currentWinners) {
            player.setChips(player.getChips() + winnings);
        }
    }

    public void eliminatePlayers() {
        ArrayList<PokerPlayer> remainingPlayers = new ArrayList<>();
        for (PokerPlayer player : players) {
            if (!(player.getChips() == 0)) {
                remainingPlayers.add(player);
            }
        }
        players = remainingPlayers;
    }

    public ArrayList<PokerPlayer> decideWinner() {
        for (PokerPlayer player : playersInTheRound) {
            ArrayList<Card> totalHand = centralCards;
            player.totalHandWithCentralCards = new PokerHand(totalHand);
            player.totalHandWithCentralCards.add(player.pokerhand.removeCard(0));
            player.totalHandWithCentralCards.add(player.pokerhand.removeCard(0));
        }

        currentWinners = new ArrayList<>();
        currentWinners.add(playersInTheRound.get(0));
        for (int i = 1; i < playersInTheRound.size(); i++) {
            int resultOfComparison = (int) HandComparer.compareTwoHands(currentWinners.get(0).totalHandWithCentralCards, playersInTheRound.get(i).totalHandWithCentralCards).get("result");
            String reasonOfComparison = (String) HandComparer.compareTwoHands(currentWinners.get(0).totalHandWithCentralCards, playersInTheRound.get(i).totalHandWithCentralCards).get("reason");

            if (resultOfComparison == 0) {
                currentWinners.add(playersInTheRound.get(i));
                currentRoundReasonForWin=reasonOfComparison;
            } else if (resultOfComparison == 1) {
                System.out.println(" ");
            } else if (resultOfComparison == 2) {
                currentWinners = new ArrayList<>();
                currentWinners.add(playersInTheRound.get(i));
                currentRoundReasonForWin=reasonOfComparison;
            }
        }



        return currentWinners;
    }


    @Override
    public boolean playAgain() {
        return PokerUserMessages.replay();
    }

    public boolean onePlayerLeftCheck() {
        return playersInTheRound.size() == 1;
    }


}
