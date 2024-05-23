package org.example.GameManagement.Poker;
import org.example.CardSetup.Card;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PokerHandChecker {

    String bestHand;
    HashMap<Integer, ArrayList<Card>> handSplitByValue;
    HashMap<String, ArrayList<Card>> handSplitBySuite;

    public static HashMap<Integer, ArrayList<Card>> splitByValue(ArrayList<Card> hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = new HashMap<>();
        for (Card card : hand) {
            ArrayList<Card> currentArr = valueMap.get(card.getValue());
            if (currentArr==null) {
                valueMap.put(card.getValue(), new ArrayList<>());
                currentArr = valueMap.get(card.getValue());
            }
            currentArr.add(card);
            valueMap.put(card.getValue(), currentArr);
        }
        return valueMap;
    }

    public static HashMap<String, ArrayList<Card>> splitBySuite(ArrayList<Card> hand) {
        HashMap<String, ArrayList<Card>> suiteMap = new HashMap<>();
        for (Card card : hand) {
            ArrayList<Card> currentArr = suiteMap.get(card.getSuite());
            if (currentArr==null) {
                suiteMap.put(card.getSuite(), new ArrayList<>());
                currentArr = suiteMap.get(card.getSuite());
            }
            currentArr.add(card);
            suiteMap.put(card.getSuite(), currentArr);
        }
        return suiteMap;
    }

    public boolean RoyalFlushCheck(ArrayList<Card> hand) {
        return true;
    }

    public boolean StraightFlushCheck() {
        return true;
    }

    public static boolean FourOfAKindCheck(ArrayList<Card> hand) {
        int handValue;
        for (int i = 0; i< hand.size(); i++) {
            int finalI = i;
            if (hand.stream().filter(card-> Objects.equals(card.getValue(), hand.get(finalI).getValue())).count() >=4) {
                handValue = hand.get(i).getValue();
                return true;
            }
        }
        return false;
    }

    public boolean FullHouseCheck() {
        return true;
    }

    public boolean FlushCheck() {
        return true;
    }

    public boolean StraightCheck() {
        return true;
    }

    public boolean ThreeOfAKindCheck(ArrayList<Card> hand) {
        int handValue=0;
        boolean hasThreeOfAKind=false;
        for (int i=0; i<hand.size(); i++) {
            int finalI = i;
            if (hand.stream().filter(card-> Objects.equals(card.getValue(), hand.get(finalI).getValue())).count() >=3) {
                if (handValue<hand.get(i).getValue()) {
                    handValue=hand.get(i).getValue();
                    hasThreeOfAKind=true;
                }
            }
        }
        return hasThreeOfAKind;
    }

    public boolean TwoPairCheck() {
        return true;
    }

    public boolean PairCheck(ArrayList<Card> hand) {
        int handValue=0;
        boolean hasPair=false;
        for (int i=0; i<hand.size(); i++) {
            int finalI = i;
            if (hand.stream().filter(card-> Objects.equals(card.getValue(), hand.get(finalI).getValue())).count() >=3) {
                if (handValue<hand.get(i).getValue()) {
                    handValue=hand.get(i).getValue();
                    hasPair=true;
                }
            }
        }
        return hasPair;
    }

    public boolean HighCardCheck() {
        return true;
    }





}
