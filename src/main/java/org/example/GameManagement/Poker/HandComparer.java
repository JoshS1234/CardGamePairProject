package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class HandComparer {

    public static HashMap<String, Object> compareTwoHands(PokerHand pokerHand1, PokerHand pokerHand2) {

        HashMap<String, Object> returnHashMap = new HashMap<>();
        //Done :)
        boolean hasRoyalFlush1 = (boolean) PokerHandChecker.RoyalFlushCheck(pokerHand1).get("hasRoyalFlush");
        boolean hasRoyalFlush2 = (boolean) PokerHandChecker.RoyalFlushCheck(pokerHand2).get("hasRoyalFlush");
        if (hasRoyalFlush1 && hasRoyalFlush2) {
            returnHashMap.put("result", 0);
            returnHashMap.put("reason", "Royal flush");
            return returnHashMap;
        } else if (hasRoyalFlush1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "Royal flush");
            return returnHashMap;
        } else if (hasRoyalFlush2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "Royal flush");
            return returnHashMap;
        }

        //Done
        boolean hasStraightFlush1 = (boolean) PokerHandChecker.StraightFlushCheck(pokerHand1).get("hasStraightFlush");
        boolean hasStraightFlush2 = (boolean) PokerHandChecker.StraightFlushCheck(pokerHand2).get("hasStraightFlush");
        if (hasStraightFlush1 && hasStraightFlush2) {
            int StraightFlushBaseValue1 = (int) PokerHandChecker.StraightFlushCheck(pokerHand1).get("startValue");
            int StraightFlushBaseValue2 = (int) PokerHandChecker.StraightFlushCheck(pokerHand1).get("startValue");

            if (StraightFlushBaseValue1 > StraightFlushBaseValue2) {
                returnHashMap.put("result", 1);
                returnHashMap.put("reason", "better straight flush");
                return returnHashMap;
            } else if (StraightFlushBaseValue2 > StraightFlushBaseValue1) {
                returnHashMap.put("result", 2);
                returnHashMap.put("reason", "better straight flush");
                return returnHashMap;
            } else {
                returnHashMap.put("result", 0);
                returnHashMap.put("reason", "same straight flush");
                return returnHashMap;
            }
        } else if (hasStraightFlush1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "straight flush");
            return returnHashMap;
        } else if (hasStraightFlush2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "better straight flush");
            return returnHashMap;
        }

        //Need to update as additional comparison is unnecessary
        boolean hasFourOfAKind1 = (boolean) PokerHandChecker.FourOfAKindCheck(pokerHand1).get("hasFourOfAKind");
        boolean hasFourOfAKind2 = (boolean) PokerHandChecker.FourOfAKindCheck(pokerHand2).get("hasFourOfAKind");
        if (hasFourOfAKind1 && hasFourOfAKind2) {
            int fourOfAKindValue1 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand1).get("fourOfAKindValue");
            int fourOfAKindValue2 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand2).get("fourOfAKindValue");
            if ( fourOfAKindValue1 >fourOfAKindValue2 ) {
                returnHashMap.put("result", 1);
                returnHashMap.put("reason", "better 4 of a kind");
                return returnHashMap;
            } else if (fourOfAKindValue2 > fourOfAKindValue1) {
                returnHashMap.put("result", 2);
                returnHashMap.put("reason", "better 4 of a kind");
                return returnHashMap;
            } else {
                System.out.println("Cannot have 2 lots of four of a kind that are the same suite");
                returnHashMap.put("result", 0);
                returnHashMap.put("reason", "same 4 of a kind");
                return returnHashMap;
//                int highCard1 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand1).get(2);
//                int highCard2 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand2).get(2);
//                if (highCard1 > highCard2 ) {
//                    return 1;
//                } else if (highCard2>highCard1) {
//                    return 2;
//                } else {
//                    return 0;
//                }
            }
        } else if (hasFourOfAKind1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "4 of a kind");
            return returnHashMap;
        } else if (hasFourOfAKind2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "4 of a kind");
            return returnHashMap;
        }


        //Done
        boolean hasFullHouse1 = (boolean) PokerHandChecker.FullHouseCheck(pokerHand1).get("hasFullHouse");
        boolean hasFullHouse2 = (boolean) PokerHandChecker.FullHouseCheck(pokerHand2).get("hasFullHouse");
        if (hasFullHouse1 && hasFullHouse2) {
            int threeLotsOfPlayer1 = (int) PokerHandChecker.FullHouseCheck(pokerHand1).get("threeOf");
            int threeLotsOfPlayer2 = (int) PokerHandChecker.FullHouseCheck(pokerHand2).get("threeOf");
            if (threeLotsOfPlayer1 > threeLotsOfPlayer2) {
                returnHashMap.put("result", 1);
                returnHashMap.put("reason", "better full house");
                return returnHashMap;
            } else if (threeLotsOfPlayer2 > threeLotsOfPlayer1) {
                returnHashMap.put("result", 2);
                returnHashMap.put("reason", "better full house");
                return returnHashMap;
            } else {
                //Not sure this should be here
                int twoLotsOfPlayer1 = (int) PokerHandChecker.FullHouseCheck(pokerHand1).get("twoOf");
                int twoLotsOfPlayer2 = (int) PokerHandChecker.FullHouseCheck(pokerHand2).get("twoOf");
                if (twoLotsOfPlayer1 > twoLotsOfPlayer2) {
                    returnHashMap.put("result", 1);
                    returnHashMap.put("reason", "better full house");
                    return returnHashMap;
                } else if (twoLotsOfPlayer2 > twoLotsOfPlayer1) {
                    returnHashMap.put("result", 2);
                    returnHashMap.put("reason", "better full house");
                    return returnHashMap;
                } else {
                    returnHashMap.put("result", 0);
                    returnHashMap.put("reason", "same full house");
                    return returnHashMap;
                }
            }
        } else if (hasFullHouse1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "full house");
            return returnHashMap;
        } else if (hasFullHouse2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "full house");
            return returnHashMap;
        }

        //Done
        boolean hasFlush1 = (boolean) PokerHandChecker.FlushCheck(pokerHand1).get("hasFlush");
        boolean hasFlush2 = (boolean) PokerHandChecker.FlushCheck(pokerHand2).get("hasFlush");
        if (hasFlush1 && hasFlush2) {
            ArrayList<Card> flushCards1 = (ArrayList<Card>) PokerHandChecker.FlushCheck(pokerHand1).get("flushCards");
            ArrayList<Card> flushCards2 = (ArrayList<Card>) PokerHandChecker.FlushCheck(pokerHand2).get("flushCards");

            for (int i=0; i<5; i++) {
                if (flushCards1.get(i).getValue() > flushCards2.get(i).getValue()) {
                    returnHashMap.put("result", 1);
                    returnHashMap.put("reason", "higher flush");
                    return returnHashMap;
                } else if (flushCards2.get(i).getValue() > flushCards1.get(i).getValue()) {
                    returnHashMap.put("result", 2);
                    returnHashMap.put("reason", "higher flush");
                    return returnHashMap;
                }
            }
            returnHashMap.put("result", 0);
            returnHashMap.put("reason", "same flush");
            return returnHashMap;
        } else if (hasFlush1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "flush");
            return returnHashMap;
        } else if (hasFlush2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "flush");
            return returnHashMap;
        }

        //Done
        boolean hasStraight1 = (boolean) PokerHandChecker.StraightCheck(pokerHand1).get("hasStraight");
        boolean hasStraight2 = (boolean) PokerHandChecker.StraightCheck(pokerHand2).get("hasStraight");
        if (hasStraight1 && hasStraight2) {
            int straightBaseValue1 = (int) PokerHandChecker.StraightCheck(pokerHand1).get("startValue");
            int straightBaseValue2 = (int) PokerHandChecker.StraightCheck(pokerHand2).get("startValue");
            if (straightBaseValue1 > straightBaseValue2) {
                returnHashMap.put("result", 1);
                returnHashMap.put("reason", "higher straight");
                return returnHashMap;
            } else if (straightBaseValue2 > straightBaseValue1) {
                returnHashMap.put("result", 2);
                returnHashMap.put("reason", "higher straight");
                return returnHashMap;
            } else {
                returnHashMap.put("result", 0);
                returnHashMap.put("reason", "same straight");
                return returnHashMap;
            }
        } else if (hasStraight1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "straight");
            return returnHashMap;
        } else if (hasStraight2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "straight");
            return returnHashMap;
        }

        //Need to update as additional comparison is unnecessary
        boolean hasThreeOfAKind1 = (boolean) PokerHandChecker.ThreeOfAKindCheck(pokerHand1).get("hasThreeOfAKind");
        boolean hasThreeOfAKind2 = (boolean) PokerHandChecker.ThreeOfAKindCheck(pokerHand2).get("hasThreeOfAKind");
        if (hasThreeOfAKind1 && hasThreeOfAKind2) {
            Integer threeOfWhichCard1 = (Integer) PokerHandChecker.ThreeOfAKindCheck(pokerHand1).get("value");
            Integer threeOfWhichCard2 = (Integer) PokerHandChecker.ThreeOfAKindCheck(pokerHand2).get("value");
            if (threeOfWhichCard1 > threeOfWhichCard2) {
                returnHashMap.put("result", 1);
                returnHashMap.put("reason", "higher 3 of a kind");
                return returnHashMap;
            } else if (threeOfWhichCard2 > threeOfWhichCard1) {
                returnHashMap.put("result", 2);
                returnHashMap.put("reason", "higher 3 of a kind");
                return returnHashMap;
            } else {
                System.out.println("Cannot have 2 lots of 3 of a kind that are the same suite");
                returnHashMap.put("result", 0);
                returnHashMap.put("reason", "same 3 of a kind");
                return returnHashMap;
//                ArrayList<Card> remainingCards1 = (ArrayList<Card>) PokerHandChecker.ThreeOfAKindCheck(pokerHand1).get(2);
//                ArrayList<Card> remainingCards2 = (ArrayList<Card>) PokerHandChecker.ThreeOfAKindCheck(pokerHand2).get(2);
//                System.out.println(remainingCards1);
//                System.out.println(remainingCards2);
//
//                for (int i=0; i<2; i++) {
//                    if (remainingCards1.get(i).getValue() > remainingCards2.get(i).getValue()) {
//                        return 1;
//                    } else if (remainingCards2.get(i).getValue() > remainingCards1.get(i).getValue()) {
//                        return 2;
//                    }
//                }
//                return 0;
            }
        } else if (hasThreeOfAKind1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "3 of a kind");
            return returnHashMap;
        } else if (hasThreeOfAKind2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "3 of a kind");
            return returnHashMap;
        }


        //Done
        boolean hasTwoPair1 = (boolean) PokerHandChecker.TwoPairCheck(pokerHand1).get("hasTwoPair");
        boolean hasTwoPair2 =  (boolean) PokerHandChecker.TwoPairCheck(pokerHand2).get("hasTwoPair");
        if (hasTwoPair1 && hasTwoPair2) {
            int higherPair1 = (int) PokerHandChecker.TwoPairCheck(pokerHand1).get("topValue1");
            int higherPair2 = (int) PokerHandChecker.TwoPairCheck(pokerHand2).get("topValue1");
            if (higherPair1 > higherPair2) {
                returnHashMap.put("result", 1);
                returnHashMap.put("reason", "higher top pair");
                return returnHashMap;
            } else if (higherPair2 > higherPair1) {
                returnHashMap.put("result", 2);
                returnHashMap.put("reason", "higher top pair");
                return returnHashMap;
            } else {
                int lowerPair1 = (int) PokerHandChecker.TwoPairCheck(pokerHand1).get("topValue2");
                int lowerPair2 = (int) PokerHandChecker.TwoPairCheck(pokerHand2).get("topValue2");
                if (lowerPair1 > lowerPair2) {
                    returnHashMap.put("result", 1);
                    returnHashMap.put("reason", "higher bottom pair");
                    return returnHashMap;
                } else if (lowerPair2 > lowerPair1) {
                    returnHashMap.put("result", 2);
                    returnHashMap.put("reason", "higher bottom pair");
                    return returnHashMap;
                } else {
                    Card finalCard1 = (Card) PokerHandChecker.TwoPairCheck(pokerHand1).get("highCard");
                    Card finalCard2 = (Card) PokerHandChecker.TwoPairCheck(pokerHand2).get("highCard");
                    if (finalCard1.getValue() > finalCard2.getValue()) {
                        returnHashMap.put("result", 1);
                        returnHashMap.put("reason", "higher final card after 2 pair");
                        return returnHashMap;
                    } else if (finalCard2.getValue() > finalCard1.getValue()) {
                        returnHashMap.put("result", 2);
                        returnHashMap.put("reason", "higher final card after 2 pair");
                        return returnHashMap;
                    } else {
                        returnHashMap.put("result", 0);
                        returnHashMap.put("reason", "same final card after 2 pair");
                        return returnHashMap;
                    }
                }
            }
        } else if (hasTwoPair1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "2 pair");
            return returnHashMap;
        } else if (hasTwoPair2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "2 pair");
            return returnHashMap;
        }


        //Done
        boolean hasPair1 = (boolean) PokerHandChecker.PairCheck(pokerHand1).get("hasPair");
        boolean hasPair2 = (boolean) PokerHandChecker.PairCheck(pokerHand2).get("hasPair");
        if (hasPair1 && hasPair2) {
            int pair1value = (int) PokerHandChecker.PairCheck(pokerHand1).get("pairValue");
            int pair2value = (int) PokerHandChecker.PairCheck(pokerHand2).get("pairValue");
//            System.out.println("pairs: " + pair1value + " vs " + pair2value);
            if (pair1value > pair2value) {
                returnHashMap.put("result", 1);
                returnHashMap.put("reason", "higher pair value");
                return returnHashMap;
            } else if (pair2value > pair1value) {
                returnHashMap.put("result", 2);
                returnHashMap.put("reason", "higher pair value");
                return returnHashMap;
            } else {
                ArrayList<Card> remainingCards1 = (ArrayList<Card>) PokerHandChecker.PairCheck(pokerHand1).get("remainingCards");
                ArrayList<Card> remainingCards2 = (ArrayList<Card>) PokerHandChecker.PairCheck(pokerHand2).get("remainingCards");


                for (int i=0; i<3; i++) {
                    if (remainingCards1.get(i).getValue() > remainingCards2.get(i).getValue()) {
                        returnHashMap.put("result", 1);
                        returnHashMap.put("reason", "higher remaining card after pair");
                        return returnHashMap;
                    } else if (remainingCards2.get(i).getValue() > remainingCards1.get(i).getValue()) {
                        returnHashMap.put("result", 2);
                        returnHashMap.put("reason", "higher remaining card after pair");
                        return returnHashMap;
                    }
                }
                returnHashMap.put("result", 0);
                returnHashMap.put("reason", "same remaining card after pair");
                return returnHashMap;
            }
        } else if (hasPair1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "pair");
            return returnHashMap;
        } else if (hasPair2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "pair");
            return returnHashMap;
        }


        //Done
        ArrayList<Card> highCard1 = PokerHandChecker.HighCardCheck(pokerHand1);
        ArrayList<Card> highCard2 = PokerHandChecker.HighCardCheck(pokerHand2);
        for (int i=0; i<5; i++) {
            if (highCard1.get(i).getValue() > highCard2.get(i).getValue()) {
                returnHashMap.put("result", 1);
                returnHashMap.put("reason", "higher card");
                return returnHashMap;
            } else if (highCard2.get(i).getValue() > highCard1.get(i).getValue()) {
                returnHashMap.put("result", 2);
                returnHashMap.put("reason", "higher card");
                return returnHashMap;
            }
        }

        System.out.println("Shouldn't really get here");
        returnHashMap.put("result", 0);
        returnHashMap.put("reason", "draw???");
        return returnHashMap;



    }



}
