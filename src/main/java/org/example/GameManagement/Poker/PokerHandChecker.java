package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public static HashMap<String, Object> RoyalFlushCheck(PokerHand hand) {
        int baseValue = 0;
        boolean hasStraightFlush = false;
        boolean hasRoyalFlush;
        String outputSuite = "";

        for (int i = 0; i < hand.pokerHand.size(); i++) {
            int cardValue = hand.pokerHand.get(i).getValue();
            int currBase;
            if (cardValue == 14) {
                currBase = 1;
            } else {
                currBase = cardValue;
            }
            String currSuite = hand.pokerHand.get(i).getSuite();
            int count = 1;
            for (int j = 1; j < 5; j++) {
                int finalJ = j;
                if (hand.pokerHand.stream().anyMatch(card -> (card.getValue() == currBase + finalJ && card.getSuite().equals(currSuite)))) {
                    count++;
                }
            }
            if (count == 5) {
                if (currBase > baseValue) {
                    hasStraightFlush = true;
                    outputSuite = currSuite;
                    baseValue = currBase;
                }
            }
        }

        hasRoyalFlush = hasStraightFlush && baseValue == 10;
        HashMap<String, Object> returnHashMap = new HashMap<>();
        returnHashMap.put("hasRoyalFlush", hasRoyalFlush);
        return returnHashMap;

    }

    public static HashMap<String, Object> StraightFlushCheck(PokerHand hand) {
        int startValue = 0;
        boolean hasStraightFlush = false;

        for (int i = 0; i < hand.pokerHand.size(); i++) {
            int cardValue = hand.pokerHand.get(i).getValue();
            int currBase;
            if (cardValue == 14) {
                currBase = 1;
            } else {
                currBase = cardValue;
            }
            String currSuite = hand.pokerHand.get(i).getSuite();
            int count = 1;
            for (int j = 1; j < 5; j++) {
                int finalJ = j;
                if (hand.pokerHand.stream().anyMatch(card -> (card.getValue() == currBase + finalJ && card.getSuite().equals(currSuite)))) {
                    count++;
                }
            }
            if (count == 5) {
                if (currBase > startValue) {
                    hasStraightFlush = true;
                    startValue = currBase;
                }
            }
        }
        HashMap<String, Object> returnHashMap = new HashMap<>();
        returnHashMap.put("hasStraightFlush",hasStraightFlush);
        returnHashMap.put("startValue", startValue);
        return returnHashMap;
    }


    //CHECK HIGH CARD BIT
    public static HashMap<String, Object> FourOfAKindCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        HashMap<String, Object> returnHashMap = new HashMap<>();
        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 4) {
                returnHashMap.put("hasFourOfAKind", true);
                returnHashMap.put("fourOfAKindValue", key);

//                ArrayList<Card> filteredHand = (ArrayList<Card>) hand.pokerHand.stream().filter(card -> !Objects.equals(card.getValue(), key)).collect(Collectors.toList());
//                filteredHand = (ArrayList<Card>) filteredHand.stream().sorted((a, b) -> b.getValue() - a.getValue()).collect(Collectors.toList());
//                returnArr.add(filteredHand.get(0));

                return returnHashMap;
            }
        }
        returnHashMap.put("hasFourOfAKind", false);
        returnHashMap.put("fourOfAKindValue", 0);

        return returnHashMap;
    }

    //DONE
    public static HashMap<String, Object> FullHouseCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        int topValue1 = 0;
        int topValue2 = 0;
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;
        boolean hasFullHouse = false;

        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 3) {
                hasThreeOfAKind = true;
                if (valueMap.get(key).get(0).getValue() > topValue1) {
                    topValue1 = valueMap.get(key).get(0).getValue();
                }

            }
        }
        for (Integer key : valueMap.keySet()) {
            if (key != topValue1 && valueMap.get(key).size() >= 2) {
                hasPair = true;
                if (valueMap.get(key).get(0).getValue() > topValue2) {
                    topValue2 = valueMap.get(key).get(0).getValue();
                }

            }
        }
        HashMap<String, Object> returnHashMap = new HashMap<>();
        hasFullHouse = hasThreeOfAKind && hasPair;
        returnHashMap.put("hasFullHouse", hasFullHouse);
        if (hasFullHouse) {
            returnHashMap.put("threeOf",topValue1);
            returnHashMap.put("twoOf",topValue2);
        }

        return returnHashMap;
    }

    public static HashMap<String, Object> FlushCheck(PokerHand hand) {
        HashMap<String, ArrayList<Card>> suiteMap = splitBySuite(hand);
        HashMap<String, Object> returnHashMap = new HashMap<>();
        for (String key : suiteMap.keySet()) {
            if (suiteMap.get(key).size() >= 5) {
                returnHashMap.put("hasFlush",true);
                returnHashMap.put("flushCards", (ArrayList<Card>) suiteMap.get(key).stream().sorted((a, b) -> b.getValue() - a.getValue()).limit(5).collect(Collectors.toList()));
                return returnHashMap;
            }
        }
        returnHashMap.put("hasFlush", false);
        return returnHashMap;
    }

    public static HashMap<String, Object> StraightCheck(PokerHand hand) {
        int startValue = 0;
        boolean hasStraight = false;

        for (int i = 0; i < hand.pokerHand.size(); i++) {
            int cardValue = hand.pokerHand.get(i).getValue();
            int currBase;
            if (cardValue == 14) {
                currBase = 1;
            } else {
                currBase = cardValue;
            }
            int count = 1;
            for (int j = 1; j < 5; j++) {
                int finalJ = j;
                if (hand.pokerHand.stream().anyMatch(card -> card.getValue() == currBase + finalJ)) {
                    count++;
                }
            }
            if (count == 5) {
                if (currBase > startValue) {
                    hasStraight = true;
                    startValue = currBase;
                }
            }
        }
        HashMap<String, Object> returnHashMap = new HashMap<>();
        returnHashMap.put("hasStraight", hasStraight);
        if (hasStraight) {
            returnHashMap.put("startValue", startValue);
        }
        return returnHashMap;
    }

    public static HashMap<String, Object> ThreeOfAKindCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        HashMap<String, Object> returnHashMap = new HashMap<>();
        int topValue = 0;
        boolean hasThreeOfAKind = false;
        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 3) {
                hasThreeOfAKind = true;
                if (valueMap.get(key).get(0).getValue() > topValue) {
                    topValue = valueMap.get(key).get(0).getValue();
                    returnHashMap.put("hasThreeOfAKind", hasThreeOfAKind);
                    returnHashMap.put("value", topValue);
//                    ArrayList<Card> remainingCards = (ArrayList<Card>) hand.pokerHand.stream().filter(card -> !Objects.equals(card.getValue(), key)).sorted((a, b) -> b.getValue() - a.getValue()).limit(2).collect(Collectors.toList());
//                    returnHashMap.put("remainingCards", remainingCards);
                }

            }
        }
        if (returnHashMap.isEmpty()) {
            returnHashMap.put("hasThreeOfAKind", false);
        }
        return returnHashMap;
    }

    public static HashMap<String, Object> TwoPairCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        int topValue1 = 0;
        int topValue2 = 0;
        boolean hasOnePair = false;
        boolean hasTwoPair = false;
        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 2) {
                if (hasOnePair) {
                    hasTwoPair = true;
                }
                hasOnePair = true;
                if (valueMap.get(key).get(0).getValue() > topValue1) {
                    topValue2 = topValue1;
                    topValue1 = valueMap.get(key).get(0).getValue();
                } else if (valueMap.get(key).get(0).getValue() > topValue2) {
                    topValue2 = valueMap.get(key).get(0).getValue();
                }
            }
        }
        HashMap<String, Object> returnHashmap = new HashMap<>();
        returnHashmap.put("hasTwoPair", hasTwoPair);
        if (hasTwoPair) {
            returnHashmap.put("topValue1", topValue1);
            returnHashmap.put("topValue2", topValue2);
            int finalTopValue = topValue1;
            int finalTopValue1 = topValue2;
            ArrayList<Card> remainingCards = (ArrayList<Card>) hand.pokerHand.stream()
                    .filter(card -> (!Objects.equals(card.getValue(), finalTopValue) && !Objects.equals(card.getValue(), finalTopValue1)))
                    .sorted((a, b) -> b.getValue() - a.getValue()).limit(1).collect(Collectors.toList());
            returnHashmap.put("highCard", remainingCards.get(0));
        }
        return returnHashmap;
    }

    public static HashMap<String, Object> PairCheck(PokerHand hand) {
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
        HashMap<String, Object> returnHashMap = new HashMap<>();
        returnHashMap.put("hasPair", hasPair);
        if (hasPair) {
            returnHashMap.put("pairValue", topValue);
            int finalTopValue = topValue;
            ArrayList<Card> remainingCards = (ArrayList<Card>) hand.pokerHand.stream()
                    .filter(card -> !Objects.equals(card.getValue(), finalTopValue))
                    .sorted((a, b) -> b.getValue() - a.getValue()).limit(3).collect(Collectors.toList());
            returnHashMap.put("remainingCards", remainingCards);
        }
        return returnHashMap;
    }

    public static ArrayList<Card> HighCardCheck(PokerHand hand) {
        ArrayList<Card> remainingCards = (ArrayList<Card>) hand.pokerHand.stream()
                .sorted((a, b) -> b.getValue() - a.getValue()).limit(5).collect(Collectors.toList());
        return remainingCards;

    }


}
