package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class PokerHandChecker {

    String bestHand;
    HashMap<Integer, ArrayList<Card>> handSplitByValue;
    HashMap<String, ArrayList<Card>> handSplitBySuite;

    public static HashMap<Integer, ArrayList<Card>> splitByValue(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = new HashMap<>();
        for (Card card : hand.pokerHand) {
            ArrayList<Card> currentArr = valueMap.get(card.getValue());
            if (currentArr == null) {
                valueMap.put(card.getValue(), new ArrayList<>());
                currentArr = valueMap.get(card.getValue());
            }
            currentArr.add(card);
            valueMap.put(card.getValue(), currentArr);
        }
        return valueMap;
    }

    public static HashMap<String, ArrayList<Card>> splitBySuite(PokerHand hand) {
        HashMap<String, ArrayList<Card>> suiteMap = new HashMap<>();
        for (Card card : hand.pokerHand) {
            ArrayList<Card> currentArr = suiteMap.get(card.getSuite());
            if (currentArr == null) {
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

    public static boolean FourOfAKindCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 4) {
                System.out.println(valueMap.get(key).get(0).getValue());
                return true;
            }
        }
        return false;
    }

    public static boolean FullHouseCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        int topValue1 = 0;
        int topValue2 = 0;
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;

        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 3) {
                hasThreeOfAKind = true;
                if (valueMap.get(key).get(0).getValue() > topValue1) {
                    topValue1 = valueMap.get(key).get(0).getValue();
                    System.out.println("curr value is " + topValue1);
                }

            }
        }
        for (Integer key : valueMap.keySet()) {
            if (key!=topValue1 && valueMap.get(key).size() >= 2) {
                hasPair = true;
                if (valueMap.get(key).get(0).getValue() > topValue2) {
                    topValue2 = valueMap.get(key).get(0).getValue();
                    System.out.println(topValue2);
                }

            }
        }
        System.out.println("3 of " + topValue1 + " and 2 of " + topValue2);
        return hasThreeOfAKind && hasPair;
    }

    public static boolean FlushCheck(PokerHand hand) {
        HashMap<String, ArrayList<Card>> suiteMap = splitBySuite(hand);
        System.out.println(suiteMap);
        for (String key : suiteMap.keySet()) {
            if (suiteMap.get(key).size() >= 5) {
                return true;
            }
        }
        return false;
    }

    public static boolean StraightCheck(PokerHand hand) {
        int baseValue = 0;
        boolean hasStraight = false;

        for (int i=0; i<hand.pokerHand.size(); i++) {
            int currBase = hand.pokerHand.get(i).getValue();
            int count=1;
            for (int j=1; j<5; j++) {
                int finalJ = j;
                if (hand.pokerHand.stream().anyMatch(card -> card.getValue() == currBase + finalJ)) {
                    count++;
                }
            }
            if (count==5) {
                if (currBase>baseValue) {
                    System.out.println(currBase);
                    hasStraight=true;
                    baseValue=currBase;
                }
            }
        }
        if (hasStraight) {
            System.out.println("Straight starting at: " + baseValue);
        }
        return hasStraight;
    }

    public static boolean ThreeOfAKindCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        int topValue = 0;
        boolean hasThreeOfAKind = false;
        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 3) {
                hasThreeOfAKind = true;
                if (valueMap.get(key).get(0).getValue() > topValue) {
                    topValue = valueMap.get(key).get(0).getValue();
                    System.out.println(topValue);
                }

            }
        }
        return hasThreeOfAKind;
    }

    public static boolean TwoPairCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        int topValue1 = 0;
        int topValue2 = 0;
        boolean hasOnePair = false;
        boolean hasTwoPair = false;
        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 2) {
                if (hasOnePair) {
                    hasTwoPair=true;
                }
                hasOnePair = true;
                if (valueMap.get(key).get(0).getValue() > topValue1) {
                    topValue2=topValue1;
                    topValue1 = valueMap.get(key).get(0).getValue();
                    System.out.println("Values are: " + topValue1 + " and " + topValue2);
                } else if (valueMap.get(key).get(0).getValue() > topValue2) {
                    topValue2 = valueMap.get(key).get(0).getValue();
                    System.out.println("Values are: " + topValue1 + " and " + topValue2);
                }
            }
        }
        return hasTwoPair;
    }

    public static boolean PairCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        int topValue = 0;
        boolean hasPair = false;
        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 2) {
                hasPair = true;
                if (valueMap.get(key).get(0).getValue() > topValue) {
                    topValue = valueMap.get(key).get(0).getValue();
                    System.out.println(topValue);
                }

            }
        }
        return hasPair;
    }

    public static Card HighCardCheck(PokerHand hand) {
        hand.sortHand();
        return hand.pokerHand.get(hand.pokerHand.size()-1);
    }


}
