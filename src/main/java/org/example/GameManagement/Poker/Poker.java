package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;
import org.example.CardSetup.Deck;
import org.example.GameManagement.Game;
import org.example.utils.UserMessages;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Poker extends Game {

    int chips;
    ArrayList<PokerPlayer> players = new ArrayList<>();
    ArrayList<PokerPlayer> playersInTheRound;
    int positionOnTable = 0;
    int startingPositionOnTable = 0;

    int currentPot = 0;
    Deck deck = new Deck();
    ArrayList<Card> centralCards = new ArrayList<>();
    ArrayList<PokerPlayer> currentWinners=new ArrayList<>();



    public Poker(String title, String rules) {
        super(title, rules);
        deck.shuffleDeck();
    }

    public void resetRound() {
        currentWinners=new ArrayList<PokerPlayer>();
        deck = new Deck();
        deck.shuffleDeck();
        centralCards = new ArrayList<Card>();
        playersInTheRound = new ArrayList<PokerPlayer>();
        playersInTheRound.addAll(players);
        currentPot=0;
        startingPositionOnTable=(startingPositionOnTable+1)%players.size();
        for(PokerPlayer player : players) {
            player.pokerhand = new PokerHand();
        }
        dealCardsToPlayers();
    }

    @Override
    public void play() {
        System.out.println("play game");
        players = PokerUserMessages.gameSetup();
        playersInTheRound = new ArrayList<PokerPlayer>();
        playersInTheRound.addAll(players);
        dealCardsToPlayers();

        boolean hasRoundWinner=false;


        while (true) {
            hasRoundWinner=false;
            while (!hasRoundWinner) {
                playRound();
//                System.out.println(currentWinners);
                if (!currentWinners.isEmpty()) {
                    hasRoundWinner=true;
                } else {
                    System.out.println("something went wrong");
                }
            }
            roundSummary();
            resetRound();
            //give chips to winner(s)
            //check eliminate players
            //round reset
            //if there is more than one player continue, otherwise announce winner
        }
    }

    public void dealCardsToPlayers() {
        for (PokerPlayer player : players) {
            player.pokerhand.add(deck.dealCard());
            player.pokerhand.add(deck.dealCard());
        }
    }


    public boolean playBettingRound() {
        positionOnTable=startingPositionOnTable;
        playersInTheRound = new ArrayList<PokerPlayer>();
        playersInTheRound.addAll(players);
        Integer poorestPlayerChips = 601;
        for (PokerPlayer player : players) {
            if (player.getChips() < poorestPlayerChips) {
                poorestPlayerChips = (Integer) player.getChips();
            }
        }

        Integer currentBet = 0;
        boolean allBet = false;
        int count = 0;

        while (!allBet) {
            if (playersInTheRound.size() == 1) {
                System.out.println("end of round, only one player left");
                System.out.println(playersInTheRound);
                System.out.println(players);
                currentWinners.add(playersInTheRound.get(0));
                return true;
            }

            HashMap<String, Object> playerChoice = playersInTheRound.get(positionOnTable).playerTurn(currentBet, centralCards);
            if (playerChoice.get("choice")=="fold") {
                playersInTheRound.remove(positionOnTable);
                System.out.println(playersInTheRound);
                System.out.println(players);
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
        }
        return false;
    }

    public ArrayList<PokerPlayer> playRound() {
        //first betting round
        PokerUserMessages.firstRoundAnnouncement();
        if (playBettingRound()) {
            return currentWinners;
        }

        //add 3 cards, then second round of betting
        centralCards.add(deck.dealCard());
        centralCards.add(deck.dealCard());
        centralCards.add(deck.dealCard());
        PokerUserMessages.bettingRoundAnnouncement(centralCards);
        if (playBettingRound()) {
            return currentWinners;
        }


        //add 1 card, then third round of betting
        centralCards.add(deck.dealCard());
        PokerUserMessages.bettingRoundAnnouncement(centralCards);
        if (playBettingRound()) {
            return currentWinners;
        }


        //add 1 card, then final round of betting
        centralCards.add(deck.dealCard());
        PokerUserMessages.bettingRoundAnnouncement(centralCards);
        if (playBettingRound()) {
            return currentWinners;
        }


        //decide the winner (if there has not been one already)
        decideWinner();
        return currentWinners;

    }

    public void roundSummary() {
        System.out.println("Round is finished");
        System.out.println("Winners were: ");
        for (PokerPlayer winner : currentWinners) {
            System.out.println(winner.getName());
        }
        UserMessages.getUserPressEnterResponse("Press enter for next round");
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
        currentWinners=new ArrayList<>();

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

        return currentWinners;
    }


    @Override
    public boolean playAgain() {
        return false;
    }


}
