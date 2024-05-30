package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class HandComparer {

    public static HashMap<String, Object> royalFlushComparison(PokerHand pokerHand1, PokerHand pokerHand2) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

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
        } else {
            return null;
        }
    }

    public static HashMap<String, Object> straightFlushComparison(PokerHand pokerHand1, PokerHand pokerHand2) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

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
        } else {
            return null;
        }
    }

    public static HashMap<String, Object> fourOfAKindComparison(PokerHand pokerHand1, PokerHand pokerHand2) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

        boolean hasFourOfAKind1 = (boolean) PokerHandChecker.FourOfAKindCheck(pokerHand1).get("hasFourOfAKind");
        boolean hasFourOfAKind2 = (boolean) PokerHandChecker.FourOfAKindCheck(pokerHand2).get("hasFourOfAKind");
        if (hasFourOfAKind1 && hasFourOfAKind2) {
            int fourOfAKindValue1 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand1).get("fourOfAKindValue");
            int fourOfAKindValue2 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand2).get("fourOfAKindValue");
            if (fourOfAKindValue1 > fourOfAKindValue2) {
                returnHashMap.put("result", 1);
                returnHashMap.put("reason", "better 4 of a kind");
                return returnHashMap;
            } else if (fourOfAKindValue2 > fourOfAKindValue1) {
                returnHashMap.put("result", 2);
                returnHashMap.put("reason", "better 4 of a kind");
                return returnHashMap;
            } else {
                int highCard1 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand1).get("finalCardValue");
                int highCard2 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand2).get("finalCardValue");
                if (highCard1 > highCard2) {
                    returnHashMap.put("result", 1);
                    returnHashMap.put("reason", "same 4 of a kind, better high card");
                    return returnHashMap;
                } else if (highCard2 > highCard1) {
                    returnHashMap.put("result", 2);
                    returnHashMap.put("reason", "same 4 of a kind, better high card");
                    return returnHashMap;
                } else {
                    returnHashMap.put("result", 0);
                    returnHashMap.put("reason", "same 4 of a kind, same high card");
                    return returnHashMap;
                }
            }
        } else if (hasFourOfAKind1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "4 of a kind");
            return returnHashMap;
        } else if (hasFourOfAKind2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "4 of a kind");
            return returnHashMap;
        } else {
            return null;
        }
    }

    public static HashMap<String, Object> fullHouseComparison(PokerHand pokerHand1, PokerHand pokerHand2) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

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
        } else {
            return null;
        }
    }

    public static HashMap<String, Object> flushComparison(PokerHand pokerHand1, PokerHand pokerHand2) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

        boolean hasFlush1 = (boolean) PokerHandChecker.FlushCheck(pokerHand1).get("hasFlush");
        boolean hasFlush2 = (boolean) PokerHandChecker.FlushCheck(pokerHand2).get("hasFlush");
        if (hasFlush1 && hasFlush2) {
            ArrayList<Card> flushCards1 = (ArrayList<Card>) PokerHandChecker.FlushCheck(pokerHand1).get("flushCards");
            ArrayList<Card> flushCards2 = (ArrayList<Card>) PokerHandChecker.FlushCheck(pokerHand2).get("flushCards");

            for (int i = 0; i < 5; i++) {
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
        } else {
            return null;
        }
    }

    public static HashMap<String, Object> straightComparison(PokerHand pokerHand1, PokerHand pokerHand2) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

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
        } else {
            return null;
        }
    }

    public static HashMap<String, Object> threeOfAKindComparison(PokerHand pokerHand1, PokerHand pokerHand2) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

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
                ArrayList<Card> remainingCards1 = (ArrayList<Card>) PokerHandChecker.ThreeOfAKindCheck(pokerHand1).get("remainingCards");
                ArrayList<Card> remainingCards2 = (ArrayList<Card>) PokerHandChecker.ThreeOfAKindCheck(pokerHand2).get("remainingCards");

                for (int i = 0; i < 2; i++) {
                    if (remainingCards1.get(i).getValue() > remainingCards2.get(i).getValue()) {
                        returnHashMap.put("result", 1);
                        returnHashMap.put("reason", "same 3 of a kind, better high cards");
                        return returnHashMap;
                    } else if (remainingCards2.get(i).getValue() > remainingCards1.get(i).getValue()) {
                        returnHashMap.put("result", 2);
                        returnHashMap.put("reason", "same 3 of a kind, better high cards");
                        return returnHashMap;
                    }
                }
                returnHashMap.put("result", 0);
                returnHashMap.put("reason", "same 3 of a kind, same high cards");
                return returnHashMap;
            }
        } else if (hasThreeOfAKind1) {
            returnHashMap.put("result", 1);
            returnHashMap.put("reason", "3 of a kind");
            return returnHashMap;
        } else if (hasThreeOfAKind2) {
            returnHashMap.put("result", 2);
            returnHashMap.put("reason", "3 of a kind");
            return returnHashMap;
        } else {
            return null;
        }
    }

    public static HashMap<String, Object> twoPairComparison(PokerHand pokerHand1, PokerHand pokerHand2) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

        boolean hasTwoPair1 = (boolean) PokerHandChecker.TwoPairCheck(pokerHand1).get("hasTwoPair");
        boolean hasTwoPair2 = (boolean) PokerHandChecker.TwoPairCheck(pokerHand2).get("hasTwoPair");
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
        } else {
            return null;
        }
    }

    public static HashMap<String, Object> pairComparison(PokerHand pokerHand1, PokerHand pokerHand2) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

        boolean hasPair1 = (boolean) PokerHandChecker.PairCheck(pokerHand1).get("hasPair");
        boolean hasPair2 = (boolean) PokerHandChecker.PairCheck(pokerHand2).get("hasPair");
        if (hasPair1 && hasPair2) {
            int pair1value = (int) PokerHandChecker.PairCheck(pokerHand1).get("pairValue");
            int pair2value = (int) PokerHandChecker.PairCheck(pokerHand2).get("pairValue");
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


                for (int i = 0; i < 3; i++) {
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
        } else {
            return null;
        }
    }

    public static HashMap<String, Object> highCardComparison(PokerHand pokerHand1, PokerHand pokerHand2) {
        HashMap<String, Object> returnHashMap = new HashMap<>();

        ArrayList<Card> highCard1 = PokerHandChecker.HighCardCheck(pokerHand1);
        ArrayList<Card> highCard2 = PokerHandChecker.HighCardCheck(pokerHand2);
        for (int i = 0; i < 5; i++) {
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

        returnHashMap.put("result", 0);
        returnHashMap.put("reason", "all same high cards");
        return returnHashMap;
    }



    public static HashMap<String, Object> compareTwoHands(PokerHand pokerHand1, PokerHand pokerHand2) {
        if (royalFlushComparison(pokerHand1, pokerHand2)!=null) {
            return royalFlushComparison(pokerHand1, pokerHand2);
        }

        if (straightFlushComparison(pokerHand1, pokerHand2)!=null) {
            return straightFlushComparison(pokerHand1, pokerHand2);
        }

        if (fourOfAKindComparison(pokerHand1, pokerHand2) != null) {
            return fourOfAKindComparison(pokerHand1,pokerHand2);
        }

        if (fullHouseComparison(pokerHand1, pokerHand2) != null) {
            return fullHouseComparison(pokerHand1,pokerHand2);
        }

        if (flushComparison(pokerHand1, pokerHand2) != null) {
            return flushComparison(pokerHand1,pokerHand2);
        }

        if (straightComparison(pokerHand1, pokerHand2) != null) {
            return straightComparison(pokerHand1,pokerHand2);
        }

        if (threeOfAKindComparison(pokerHand1, pokerHand2) != null) {
            return threeOfAKindComparison(pokerHand1,pokerHand2);
        }

        if (twoPairComparison(pokerHand1, pokerHand2) != null) {
            return twoPairComparison(pokerHand1,pokerHand2);
        }

        if (pairComparison(pokerHand1, pokerHand2) != null) {
            return pairComparison(pokerHand1,pokerHand2);
        }

        return highCardComparison(pokerHand1, pokerHand2);
    }


}
