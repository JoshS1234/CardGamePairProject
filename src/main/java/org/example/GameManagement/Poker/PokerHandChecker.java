package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class PokerHandChecker {

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

    public static boolean RoyalFlushCheck(PokerHand hand) {
        int baseValue = 0;
        boolean hasStraightFlush = false;
        boolean hasRoyalFlush;
        String outputSuite="";

        for (int i=0; i<hand.pokerHand.size(); i++) {
            int cardValue = hand.pokerHand.get(i).getValue();
            int currBase;
            if (cardValue==14) {
                currBase=1;
            } else {
                currBase = cardValue;
            }
            String currSuite = hand.pokerHand.get(i).getSuite();
            int count=1;
            for (int j=1; j<5; j++) {
                int finalJ = j;
                if (hand.pokerHand.stream().anyMatch(card -> (card.getValue() == currBase + finalJ && card.getSuite().equals(currSuite)))) {
                    count++;
                }
            }
            if (count==5) {
                if (currBase>baseValue) {
                    hasStraightFlush=true;
                    outputSuite=currSuite;
                    baseValue=currBase;
                }
            }
        }

        hasRoyalFlush=hasStraightFlush && baseValue==10;
        return hasRoyalFlush;
    }

    public static boolean StraightFlushCheck(PokerHand hand) {
        int baseValue = 0;
        boolean hasStraightFlush = false;

        for (int i=0; i<hand.pokerHand.size(); i++) {
            int cardValue = hand.pokerHand.get(i).getValue();
            int currBase;
            if (cardValue==14) {
                currBase=1;
            } else {
                currBase = cardValue;
            }
            String currSuite = hand.pokerHand.get(i).getSuite();
            int count=1;
            for (int j=1; j<5; j++) {
                int finalJ = j;
                if (hand.pokerHand.stream().anyMatch(card -> (card.getValue() == currBase + finalJ && card.getSuite().equals(currSuite)))) {
                    count++;
                }
            }
            if (count==5) {
                if (currBase>baseValue) {
                    hasStraightFlush=true;
                    baseValue=currBase;
                }
            }
        }
        return hasStraightFlush;
    }

    public static boolean FourOfAKindCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 4) {
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
                }

            }
        }
        for (Integer key : valueMap.keySet()) {
            if (key!=topValue1 && valueMap.get(key).size() >= 2) {
                hasPair = true;
                if (valueMap.get(key).get(0).getValue() > topValue2) {
                    topValue2 = valueMap.get(key).get(0).getValue();
                }

            }
        }
        return hasThreeOfAKind && hasPair;
    }

    public static boolean FlushCheck(PokerHand hand) {
        HashMap<String, ArrayList<Card>> suiteMap = splitBySuite(hand);
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
            int cardValue = hand.pokerHand.get(i).getValue();
            int currBase;
            if (cardValue==14) {
                currBase=1;
            } else {
                currBase = cardValue;
            }
            int count=1;
            for (int j=1; j<5; j++) {
                int finalJ = j;
                if (hand.pokerHand.stream().anyMatch(card -> card.getValue() == currBase + finalJ)) {
                    count++;
                }
            }
            if (count==5) {
                if (currBase>baseValue) {
                    hasStraight=true;
                    baseValue=currBase;
                }
            }
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
                } else if (valueMap.get(key).get(0).getValue() > topValue2) {
                    topValue2 = valueMap.get(key).get(0).getValue();
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
