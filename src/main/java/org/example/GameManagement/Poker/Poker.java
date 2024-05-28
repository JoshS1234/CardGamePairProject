package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.GameManagement.GameSelector;
import org.example.utils.UserMessages;

import java.util.ArrayList;
import java.util.HashMap;

public class Poker extends Game {

    ArrayList<PokerPlayer> players = new ArrayList<>();
    ArrayList<PokerPlayer> playersInTheRound;
    int positionOnTable = 0;
    int startingPositionOnTable = 0;
    int currentPot = 0;
    Deck deck = new Deck();
    ArrayList<Card> centralCards = new ArrayList<>();
    ArrayList<PokerPlayer> currentWinners = new ArrayList<>();


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
//        int count = 0;

        for(PokerPlayer player : playersInTheRound) {
            System.out.println(player);
            System.out.println(player.hasBetThisRound);
            player.hasBetThisRound=false;
        }

        while (!allBet) {
            if(onePlayerLeftCheck()){
                currentWinners.add(playersInTheRound.get(0));
                return true;
            }
//            if (playersInTheRound.get(positionOnTable).hasBetThisRound==false) {
//                return false;
//            }

            HashMap<String, Object> playerChoice = playersInTheRound.get(positionOnTable).playerTurn(currentBet, centralCards, poorestPlayerChips);
            if (playerChoice.get("choice") == "fold") {
                playersInTheRound.remove(positionOnTable);
                positionOnTable=positionOnTable%playersInTheRound.size();
//                if(playersInTheRound.get(positionOnTable).playerCurrentBet==currentBet) {
//                    allBet=true;
//                }
            } else if (playerChoice.get("choice") == "call") {
                currentPot += playersInTheRound.get(positionOnTable).call(currentBet);
//                count++;
//                if (count == playersInTheRound.size()) {
//                    allBet = true;
//                }
                playersInTheRound.get(positionOnTable).hasBetThisRound=true;
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else if (playerChoice.get("choice") == "raise") {
                //If someone raises, then someone else re-raises, it is broken currently
                int raiseAmount = playersInTheRound.get(positionOnTable).raise(currentBet, poorestPlayerChips);
                currentPot += raiseAmount;
                currentBet = raiseAmount;
//                count = 1;
                for(PokerPlayer player : playersInTheRound) {
                    player.hasBetThisRound=false;
                }
                playersInTheRound.get(positionOnTable).hasBetThisRound=true;
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else {
                System.out.println("Argh");
            }

            if (playersInTheRound.get(positionOnTable).hasBetThisRound) {
                allBet=true;
            }
        }

        if(onePlayerLeftCheck()){
            currentWinners.add(playersInTheRound.get(0));
            return true;
        }
        return false;
    }


//    public boolean playBettingRound() {
//        positionOnTable = startingPositionOnTable;
//        int poorestPlayerChips = 901;
//        for (PokerPlayer player : players) {
//            if (player.getChips() < poorestPlayerChips) {
//                poorestPlayerChips = player.getChips();
//            }
//        }
//
//        int currentBet = 0;
//        boolean allBet = false;
////        int count = 0;
//
//        while (!allBet) {
//            if(onePlayerLeftCheck()){
//                currentWinners.add(playersInTheRound.get(0));
//                return true;
//            }
//
//            HashMap<String, Object> playerChoice = playersInTheRound.get(positionOnTable).playerTurn(currentBet, centralCards, poorestPlayerChips);
//            if (playerChoice.get("choice") == "fold") {
//                playersInTheRound.remove(positionOnTable);
//                positionOnTable=positionOnTable%playersInTheRound.size();
////                if(playersInTheRound.get(positionOnTable).playerCurrentBet==currentBet) {
////                    allBet=true;
////                }
//            } else if (playerChoice.get("choice") == "call") {
//                currentPot += playersInTheRound.get(positionOnTable).call(currentBet);
////                count++;
////                if (count == playersInTheRound.size()) {
////                    allBet = true;
////                }
//                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
//                playersInTheRound.get(positionOnTable).hasBetThisRound=true;
//            } else if (playerChoice.get("choice") == "raise") {
//                //If someone raises, then someone else re-raises, it is broken currently
//                int raiseAmount = playersInTheRound.get(positionOnTable).raise(currentBet, poorestPlayerChips);
//                currentPot += raiseAmount;
//                currentBet = raiseAmount;
////                count = 1;
//                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
//                for(PokerPlayer player : playersInTheRound) {
//                    player.hasBetThisRound=false;
//                }
//                playersInTheRound.get(positionOnTable).hasBetThisRound=true;
//            } else {
//                System.out.println("Argh");
//            }
//
//            if (playersInTheRound.stream().filter(player-> player.hasBetThisRound).count()==playersInTheRound.size()) {
//                allBet=true;
//            }
//        }
//
//        if(onePlayerLeftCheck()){
//            currentWinners.add(playersInTheRound.get(0));
//            return true;
//        }
//        return false;
//    }

    public ArrayList<PokerPlayer> playRound() {
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

        return decideWinner();

    }

    public void resetBets() {
        for (PokerPlayer player : players) {
            player.playerCurrentBet = 0;
            System.out.println(player.getChips());
        }
    }

    public void roundSummary() {
        System.out.println("Round is finished");
        System.out.println("Winners were: ");
        for (PokerPlayer winner : currentWinners) {
            System.out.println(winner.getName());
        }
        int winnings = (currentPot / currentWinners.size());
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
            System.out.println(player.getName());
            player.totalHandWithCentralCards.printHand();
            System.out.println("__");
        }

        currentWinners = new ArrayList<>();
        currentWinners.add(playersInTheRound.get(0));
        for (int i = 1; i < playersInTheRound.size(); i++) {
            System.out.println("comparison: " + i);
            int resultOfComparison = (int) HandComparer.compareTwoHands(currentWinners.get(0).totalHandWithCentralCards, playersInTheRound.get(i).totalHandWithCentralCards).get("result");
            System.out.println("result: " + resultOfComparison);
            String reasonOfComparison = (String) HandComparer.compareTwoHands(currentWinners.get(0).totalHandWithCentralCards, playersInTheRound.get(i).totalHandWithCentralCards).get("reason");
            System.out.println("reason: " + reasonOfComparison);

            if (resultOfComparison == 0) {
                currentWinners.add(playersInTheRound.get(i));
            } else if (resultOfComparison == 1) {
                System.out.println("Current winner is unchanged");
            } else if (resultOfComparison == 2) {
                currentWinners = new ArrayList<>();
                currentWinners.add(playersInTheRound.get(i));
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
