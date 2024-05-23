package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
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

    public static ArrayList<Object> RoyalFlushCheck(PokerHand hand) {
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
        ArrayList<Object> returnArr = new ArrayList<>();
        returnArr.add(hasRoyalFlush);
        return returnArr;

    }

    public static ArrayList<Object> StraightFlushCheck(PokerHand hand) {
        int baseValue = 0;
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
                if (currBase > baseValue) {
                    hasStraightFlush = true;
                    baseValue = currBase;
                }
            }
        }
        ArrayList<Object> returnArr = new ArrayList<>();
        returnArr.add(hasStraightFlush);
        returnArr.add(baseValue);
        return returnArr;
    }


    //CHECK HIGH CARD BIT
    public static ArrayList<Object> FourOfAKindCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        ArrayList<Object> returnArr = new ArrayList<>();
        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 4) {
                returnArr.add(true);
                returnArr.add(key);

                ArrayList<Card> filteredHand = (ArrayList<Card>) hand.pokerHand.stream().filter(card -> !Objects.equals(card.getValue(), key)).collect(Collectors.toList());
                filteredHand = (ArrayList<Card>) filteredHand.stream().sorted((a, b) -> b.getValue() - a.getValue()).collect(Collectors.toList());
                returnArr.add(filteredHand.get(0));

                return returnArr;
            }
        }
        returnArr.add(false);
        returnArr.add(0);

        return returnArr;
    }

    //DONE
    public static ArrayList<Object> FullHouseCheck(PokerHand hand) {
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
        ArrayList<Object> returnArr = new ArrayList<>();
        hasFullHouse = hasThreeOfAKind && hasPair;
        returnArr.add(hasFullHouse);
        if (hasFullHouse) {
            returnArr.add(topValue1);
            returnArr.add(topValue2);
        }

        return returnArr;
    }

    public static ArrayList<Object> FlushCheck(PokerHand hand) {
        HashMap<String, ArrayList<Card>> suiteMap = splitBySuite(hand);
        ArrayList<Object> returnArr = new ArrayList<>();
        for (String key : suiteMap.keySet()) {
            if (suiteMap.get(key).size() >= 5) {
                returnArr.add(true);
                returnArr.add((ArrayList<Card>) suiteMap.get(key).stream().sorted((a, b) -> b.getValue() - a.getValue()).limit(5).collect(Collectors.toList()));
                return returnArr;
            }
        }
        returnArr.add(false);
        return returnArr;
    }

    public static ArrayList<Object> StraightCheck(PokerHand hand) {
        int baseValue = 0;
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
                if (currBase > baseValue) {
                    hasStraight = true;
                    baseValue = currBase;
                }
            }
        }
        ArrayList<Object> returnArr = new ArrayList<>();
        returnArr.add(hasStraight);
        if (hasStraight) {
            returnArr.add(baseValue);
        }
        return returnArr;
    }

    public static ArrayList<Object> ThreeOfAKindCheck(PokerHand hand) {
        HashMap<Integer, ArrayList<Card>> valueMap = splitByValue(hand);
        ArrayList<Object> returnArr = new ArrayList<>();
        int topValue = 0;
        boolean hasThreeOfAKind = false;
        for (Integer key : valueMap.keySet()) {
            if (valueMap.get(key).size() >= 3) {
                hasThreeOfAKind = true;
                if (valueMap.get(key).get(0).getValue() > topValue) {
                    topValue = valueMap.get(key).get(0).getValue();
                    returnArr.add(hasThreeOfAKind);
                    returnArr.add(topValue);
                    ArrayList<Card> remainingCards = (ArrayList<Card>) hand.pokerHand.stream().filter(card -> !Objects.equals(card.getValue(), key)).sorted((a, b) -> b.getValue() - a.getValue()).limit(2).collect(Collectors.toList());
                    returnArr.add(remainingCards);
                }

            }
        }
        if (returnArr.isEmpty()) {
            returnArr.add(false);
        }
        return returnArr;
    }

    public static ArrayList<Object> TwoPairCheck(PokerHand hand) {
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
        ArrayList<Object> returnArray = new ArrayList<>();
        returnArray.add(hasTwoPair);
        if (hasTwoPair) {
            returnArray.add(topValue1);
            returnArray.add(topValue2);
            int finalTopValue = topValue1;
            int finalTopValue1 = topValue2;
            ArrayList<Card> remainingCards = (ArrayList<Card>) hand.pokerHand.stream()
                    .filter(card -> (!Objects.equals(card.getValue(), finalTopValue) && !Objects.equals(card.getValue(), finalTopValue1)))
                    .sorted((a, b) -> b.getValue() - a.getValue()).limit(1).collect(Collectors.toList());
            returnArray.add(remainingCards.get(0));
        }
        return returnArray;
    }

    public static ArrayList<Object> PairCheck(PokerHand hand) {
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
        ArrayList<Object> returnArr = new ArrayList<>();
        returnArr.add(hasPair);
        if (hasPair) {
            returnArr.add(topValue);
            int finalTopValue = topValue;
            ArrayList<Card> remainingCards = (ArrayList<Card>) hand.pokerHand.stream()
                    .filter(card -> !Objects.equals(card.getValue(), finalTopValue))
                    .sorted((a, b) -> b.getValue() - a.getValue()).limit(3).collect(Collectors.toList());
            returnArr.add(remainingCards);
        }
        return returnArr;
    }

    public static ArrayList<Card> HighCardCheck(PokerHand hand) {
        ArrayList<Card> remainingCards = (ArrayList<Card>) hand.pokerHand.stream()
                .sorted((a, b) -> b.getValue() - a.getValue()).limit(5).collect(Collectors.toList());
        System.out.println(remainingCards);

        return remainingCards;

    }


}
