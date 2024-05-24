package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Poker extends Game {

    int chips;
    ArrayList<PokerPlayer> players = new ArrayList<>();
    ArrayList<PokerPlayer> playersInTheRound;
    int positionOnTable = 0;
    int currentPot = 0;
    Deck deck = new Deck();
    ArrayList<Card> centralCards = new ArrayList<>();


    public Poker(String title, String rules) {
        super(title, rules);
        deck.shuffleDeck();
    }

    @Override
    public void play() {
        System.out.println("play game");
        players = PokerUserMessages.gameSetup();
        playersInTheRound = players;
        dealCardsToPlayers();
        playFirstBettingRound();
        playFlopBettingRound();
        playRiverBettingRound();
        playTurnBettingRound();
        decideWinner();

    }

    public void dealCardsToPlayers() {
        for (PokerPlayer player : players) {
            player.pokerhand.add(deck.dealCard());
            player.pokerhand.add(deck.dealCard());
        }
    }


    public void playFirstBettingRound() {
        playersInTheRound = players;
        Integer poorestPlayerChips = 601;
        for (PokerPlayer player : players) {
            if (player.getChips() < poorestPlayerChips) {
                poorestPlayerChips = (Integer) player.getChips();
            }
        }
        PokerUserMessages.firstRoundAnnouncement();

        Integer currentBet = 0;
        boolean allBet = false;
        int count = 0;

        while (!allBet) {
            if (playersInTheRound.size() == 1) {
                System.out.println("end of round, only one player left");
                break;
            }

            HashMap<String, Object> playerChoice = playersInTheRound.get(positionOnTable).playerTurn(currentBet);
            if (playerChoice.get("choice")=="fold") {
                playersInTheRound.remove(positionOnTable);
                System.out.println(playersInTheRound);
            } else if (playerChoice.get("choice")=="call") {
                count++;
                if (count==playersInTheRound.size()) {
                    allBet=true;
                }
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else if (playerChoice.get("choice")=="raise") {
                count++;
                if (count==playersInTheRound.size()) {
                    allBet=true;
                }
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else {
                System.out.println("Argh");
            }
//            boolean thisPlayerIsFolding = playersInTheRound.get(positionOnTable).isFolding();
//            if (thisPlayerIsFolding) {
//                playersInTheRound.remove(positionOnTable);
//                System.out.println(playersInTheRound);
//            } else {
//                count++;
//                if (count==playersInTheRound.size()) {
//                    allBet=true;
//                }
//                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
//            }
        }
    }

    public void playFlopBettingRound() {
        Integer poorestPlayerChips = 601;
        for (PokerPlayer player : players) {
            if (player.getChips() < poorestPlayerChips) {
                poorestPlayerChips = (Integer) player.getChips();
            }
        }

        centralCards.add(deck.dealCard());
        centralCards.add(deck.dealCard());
        centralCards.add(deck.dealCard());

        PokerUserMessages.bettingRoundAnnouncement(centralCards);



        Integer currentBet = 0;
        boolean allBet = false;
        int count = 0;

        while (!allBet) {
            if (playersInTheRound.size() == 1) {
                System.out.println("end of round, only one player left");
                break;
            }

            HashMap<String, Object> playerChoice = playersInTheRound.get(positionOnTable).playerTurn(currentBet, centralCards);
            if (playerChoice.get("choice")=="fold") {
                playersInTheRound.remove(positionOnTable);
                System.out.println(playersInTheRound);
            } else if (playerChoice.get("choice")=="call") {
                count++;
                if (count==playersInTheRound.size()) {
                    allBet=true;
                }
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else if (playerChoice.get("choice")=="raise") {
                count++;
                if (count==playersInTheRound.size()) {
                    allBet=true;
                }
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else {
                System.out.println("Argh");
            }
//            boolean thisPlayerIsFolding = playersInTheRound.get(positionOnTable).isFolding(centralCards);
//            if (thisPlayerIsFolding) {
//                playersInTheRound.remove(positionOnTable);
//                System.out.println(playersInTheRound);
//            } else {
//                count++;
//                if (count==playersInTheRound.size()) {
//                    allBet=true;
//                }
//                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
//            }
        }
    }

    public void playRiverBettingRound() {
        Integer poorestPlayerChips = 601;
        for (PokerPlayer player : players) {
            if (player.getChips() < poorestPlayerChips) {
                poorestPlayerChips = (Integer) player.getChips();
            }
        }

        centralCards.add(deck.dealCard());
        PokerUserMessages.bettingRoundAnnouncement(centralCards);


        Integer currentBet = 0;
        boolean allBet = false;
        int count = 0;

        while (!allBet) {
            if (playersInTheRound.size() == 1) {
                System.out.println("end of round, only one player left");
                break;
            }
            HashMap<String, Object> playerChoice = playersInTheRound.get(positionOnTable).playerTurn(currentBet, centralCards);
            if (playerChoice.get("choice")=="fold") {
                playersInTheRound.remove(positionOnTable);
                System.out.println(playersInTheRound);
            } else if (playerChoice.get("choice")=="call") {
                count++;
                if (count==playersInTheRound.size()) {
                    allBet=true;
                }
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else if (playerChoice.get("choice")=="raise") {
                count++;
                if (count==playersInTheRound.size()) {
                    allBet=true;
                }
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else {
                System.out.println("Argh");
            }
//            boolean thisPlayerIsFolding = playersInTheRound.get(positionOnTable).isFolding(centralCards);
//            if (thisPlayerIsFolding) {
//                playersInTheRound.remove(positionOnTable);
//                System.out.println(playersInTheRound);
//            } else {
//                count++;
//                if (count==playersInTheRound.size()) {
//                    allBet=true;
//                }
//                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
//            }
        }
    }

    public void playTurnBettingRound() {
        Integer poorestPlayerChips = 601;
        for (PokerPlayer player : players) {
            if (player.getChips() < poorestPlayerChips) {
                poorestPlayerChips = (Integer) player.getChips();
            }
        }

        centralCards.add(deck.dealCard());
        PokerUserMessages.bettingRoundAnnouncement(centralCards);

        Integer currentBet = 0;
        boolean allBet = false;
        int count = 0;

        while (!allBet) {
            if (playersInTheRound.size() == 1) {
                System.out.println("end of round, only one player left");
                break;
            }
            HashMap<String, Object> playerChoice = playersInTheRound.get(positionOnTable).playerTurn(currentBet, centralCards);
            if (playerChoice.get("choice")=="fold") {
                playersInTheRound.remove(positionOnTable);
                System.out.println(playersInTheRound);
            } else if (playerChoice.get("choice")=="call") {
                count++;
                if (count==playersInTheRound.size()) {
                    allBet=true;
                }
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else if (playerChoice.get("choice")=="raise") {
                count++;
                if (count==playersInTheRound.size()) {
                    allBet=true;
                }
                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
            } else {
                System.out.println("Argh");
            }
//            boolean thisPlayerIsFolding = playersInTheRound.get(positionOnTable).isFolding(centralCards);
//            if (thisPlayerIsFolding) {
//                playersInTheRound.remove(positionOnTable);
//                System.out.println(playersInTheRound);
//            } else {
//                count++;
//                if (count==playersInTheRound.size()) {
//                    allBet=true;
//                }
//                positionOnTable = ((positionOnTable + 1) % (playersInTheRound.size()));
//            }
        }
    }

    public void eliminatePlayers() {

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
        ArrayList<PokerPlayer> currentWinners=new ArrayList<>();

        currentWinners=new ArrayList<>();
        currentWinners.add(playersInTheRound.get(0));
        for (int i=1; i<playersInTheRound.size(); i++) {
            System.out.println("comparison: " + i);
            int resultOfComparison = (int) HandComparer.compareTwoHands(currentWinners.get(0).totalHandWithCentralCards, playersInTheRound.get(i).totalHandWithCentralCards).get("result");
            System.out.println("result: " + resultOfComparison);
            String reasonOfComparison = (String) HandComparer.compareTwoHands(currentWinners.get(0).totalHandWithCentralCards, playersInTheRound.get(i).totalHandWithCentralCards).get("reason");
            System.out.println("reason: " + reasonOfComparison);

            if (resultOfComparison==0) {
                currentWinners.add(playersInTheRound.get(i));
            } else if (resultOfComparison==1) {
                System.out.println("Current winner is unchanged");
            } else if (resultOfComparison==2) {
                currentWinners=new ArrayList<>();
                currentWinners.add(playersInTheRound.get(i));
            }
        }
        for (PokerPlayer winner : currentWinners) {
            System.out.println(winner.getName());
        }
        return currentWinners;
    }


    @Override
    public boolean playAgain() {
        return false;
    }


}
