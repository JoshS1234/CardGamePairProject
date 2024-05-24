package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;

import java.util.ArrayList;

public class HandComparer {

    PokerHand pokerHand1;
    PokerHand pokerHand2;

    public HandComparer(PokerHand pokerHand1, PokerHand pokerHand2) {
        this.pokerHand1 = pokerHand1;
        this.pokerHand2 = pokerHand2;
    }

    public int compareTwoHands() {

        //Done :)
        boolean hasRoyalFlush1 = (boolean) PokerHandChecker.RoyalFlushCheck(pokerHand1).get("hasRoyalFlush");
        boolean hasRoyalFlush2 = (boolean) PokerHandChecker.RoyalFlushCheck(pokerHand2).get("hasRoyalFlush");
        if (hasRoyalFlush1 && hasRoyalFlush2) {
            return 0;
        } else if (hasRoyalFlush1) {
            return 1;
        } else if (hasRoyalFlush2) {
            return 2;
        }

        //Done
        boolean hasStraightFlush1 = (boolean) PokerHandChecker.StraightFlushCheck(pokerHand1).get("hasStraightFlush");
        boolean hasStraightFlush2 = (boolean) PokerHandChecker.StraightFlushCheck(pokerHand2).get("hasStraightFlush");
        if (hasStraightFlush1 && hasStraightFlush2) {
            int StraightFlushBaseValue1 = (int) PokerHandChecker.StraightFlushCheck(pokerHand1).get("startValue");
            int StraightFlushBaseValue2 = (int) PokerHandChecker.StraightFlushCheck(pokerHand1).get("startValue");

            if (StraightFlushBaseValue1 > StraightFlushBaseValue2) {
                return 1;
            } else if (StraightFlushBaseValue2 > StraightFlushBaseValue1) {
                return 2;
            } else {
                System.out.println("draw");
                return 0;
            }
        } else if (hasStraightFlush1) {
            return 1;
        } else if (hasStraightFlush2) {
            return 2;
        }

        //Need to update as additional comparison is unnecessary
        boolean hasFourOfAKind1 = (boolean) PokerHandChecker.FourOfAKindCheck(pokerHand1).get("hasFourOfAKind");
        boolean hasFourOfAKind2 = (boolean) PokerHandChecker.FourOfAKindCheck(pokerHand2).get("hasFourOfAKind");
        if (hasFourOfAKind1 && hasFourOfAKind2) {
            int fourOfAKindValue1 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand1).get("fourOfAKindValue");
            int fourOfAKindValue2 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand2).get("fourOfAKindValue");
            if ( fourOfAKindValue1 >fourOfAKindValue2 ) {
                return 1;
            } else if (fourOfAKindValue2 > fourOfAKindValue1) {
                return 2;
            } else {
                System.out.println("Cannot have 2 lots of four of a kind that are the same suite");
                return 0;
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
            return 1;
        } else if (hasFourOfAKind2) {
            return 2;
        }


        //Done
        boolean hasFullHouse1 = (boolean) PokerHandChecker.FullHouseCheck(pokerHand1).get("hasFullHouse");
        boolean hasFullHouse2 = (boolean) PokerHandChecker.FullHouseCheck(pokerHand2).get("hasFullHouse");
        if (hasFullHouse1 && hasFullHouse2) {
            int threeLotsOfPlayer1 = (int) PokerHandChecker.FullHouseCheck(pokerHand1).get("threeOf");
            int threeLotsOfPlayer2 = (int) PokerHandChecker.FullHouseCheck(pokerHand2).get("threeOf");
            if (threeLotsOfPlayer1 > threeLotsOfPlayer2) {
                return 1;
            } else if (threeLotsOfPlayer2 > threeLotsOfPlayer1) {
                return 2;
            } else {
                int twoLotsOfPlayer1 = (int) PokerHandChecker.FullHouseCheck(pokerHand1).get("twoOf");
                int twoLotsOfPlayer2 = (int) PokerHandChecker.FullHouseCheck(pokerHand2).get("twoOf");
                if (twoLotsOfPlayer1 > twoLotsOfPlayer2) {
                    return 1;
                } else if (twoLotsOfPlayer2 > twoLotsOfPlayer1) {
                    return 2;
                } else {
                    return 0;
                }
            }
        } else if (hasFullHouse1) {
            return 1;
        } else if (hasFullHouse2) {
            return 2;
        }

        //Done
        boolean hasFlush1 = (boolean) PokerHandChecker.FlushCheck(pokerHand1).get("hasFlush");
        boolean hasFlush2 = (boolean) PokerHandChecker.FlushCheck(pokerHand2).get("hasFlush");
        if (hasFlush1 && hasFlush2) {
            ArrayList<Card> flushCards1 = (ArrayList<Card>) PokerHandChecker.FlushCheck(pokerHand1).get("flushCards");
            ArrayList<Card> flushCards2 = (ArrayList<Card>) PokerHandChecker.FlushCheck(pokerHand2).get("flushCards");

            for (int i=0; i<5; i++) {
                if (flushCards1.get(i).getValue() > flushCards2.get(i).getValue()) {
                    return 1;
                } else if (flushCards2.get(i).getValue() > flushCards1.get(i).getValue()) {
                    return 2;
                }
            }
            return 0;
        } else if (hasFlush1) {
            return 1;
        } else if (hasFlush2) {
            return 2;
        }

        //Done
        boolean hasStraight1 = (boolean) PokerHandChecker.StraightCheck(pokerHand1).get("hasStraight");
        boolean hasStraight2 = (boolean) PokerHandChecker.StraightCheck(pokerHand2).get("hasStraight");
        if (hasStraight1 && hasStraight2) {
            int straightBaseValue1 = (int) PokerHandChecker.StraightCheck(pokerHand1).get("startValue");
            int straightBaseValue2 = (int) PokerHandChecker.StraightCheck(pokerHand2).get("startValue");
            if (straightBaseValue1 > straightBaseValue2) {
                return 1;
            } else if (straightBaseValue2 > straightBaseValue1) {
                return 2;
            } else {
                return 0;
            }
        } else if (hasStraight1) {
            return 1;
        } else if (hasStraight2) {
            return 2;
        }

        //Need to update as additional comparison is unnecessary
        boolean hasThreeOfAKind1 = (boolean) PokerHandChecker.ThreeOfAKindCheck(pokerHand1).get("hasThreeOfAKind");
        boolean hasThreeOfAKind2 = (boolean) PokerHandChecker.ThreeOfAKindCheck(pokerHand2).get("hasThreeOfAKind");
        if (hasThreeOfAKind1 && hasThreeOfAKind2) {
            Integer threeOfWhichCard1 = (Integer) PokerHandChecker.ThreeOfAKindCheck(pokerHand1).get("value");
            Integer threeOfWhichCard2 = (Integer) PokerHandChecker.ThreeOfAKindCheck(pokerHand2).get("value");
            if (threeOfWhichCard1 > threeOfWhichCard2) {
                return 1;
            } else if (threeOfWhichCard2 > threeOfWhichCard1) {
                return 2;
            } else {
                System.out.println("Cannot have 2 lots of four of a kind that are the same suite");
                return 0;
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
            return 1;
        } else if (hasThreeOfAKind2) {
            return 2;
        }


        //Done
        boolean hasTwoPair1 = (boolean) PokerHandChecker.TwoPairCheck(pokerHand1).get("hasTwoPair");
        boolean hasTwoPair2 =  (boolean) PokerHandChecker.TwoPairCheck(pokerHand2).get("hasTwoPair");
        if (hasTwoPair1 && hasTwoPair2) {
            int higherPair1 = (int) PokerHandChecker.TwoPairCheck(pokerHand1).get("topValue1");
            int higherPair2 = (int) PokerHandChecker.TwoPairCheck(pokerHand2).get("topValue1");
            if (higherPair1 > higherPair2) {
                return 1;
            } else if (higherPair2 > higherPair1) {
                return 2;
            } else {
                int lowerPair1 = (int) PokerHandChecker.TwoPairCheck(pokerHand1).get("topValue2");
                int lowerPair2 = (int) PokerHandChecker.TwoPairCheck(pokerHand2).get("topValue2");
                if (lowerPair1 > lowerPair2) {
                    return 1;
                } else if (lowerPair2 > lowerPair1) {
                    return 2;
                } else {
                    Card finalCard1 = (Card) PokerHandChecker.TwoPairCheck(pokerHand1).get("highCard");
                    Card finalCard2 = (Card) PokerHandChecker.TwoPairCheck(pokerHand2).get("highCard");
                    if (finalCard1.getValue() > finalCard2.getValue()) {
                        return 1;
                    } else if (finalCard2.getValue() > finalCard1.getValue()) {
                        return 2;
                    } else {
                        return 0;
                    }
                }
            }
        } else if (hasTwoPair1) {
            return 1;
        } else if (hasTwoPair2) {
            return 2;
        }


        //Done
        boolean hasPair1 = (boolean) PokerHandChecker.PairCheck(pokerHand1).get("hasPair");
        boolean hasPair2 = (boolean) PokerHandChecker.PairCheck(pokerHand2).get("hasPair");
        if (hasPair1 && hasPair2) {
            int pair1value = (int) PokerHandChecker.PairCheck(pokerHand1).get("pairValue");
            int pair2value = (int) PokerHandChecker.PairCheck(pokerHand2).get("pairValue");
            if (pair1value > pair2value) {
                return 1;
            } else if (pair2value > pair1value) {
                return 2;
            } else {
                ArrayList<Card> remainingCards1 = (ArrayList<Card>) PokerHandChecker.PairCheck(pokerHand1).get("remainingCards");
                ArrayList<Card> remainingCards2 = (ArrayList<Card>) PokerHandChecker.PairCheck(pokerHand2).get("remainingCards");
                System.out.println(remainingCards1);
                System.out.println(remainingCards2);

                for (int i=0; i<3; i++) {
                    if (remainingCards1.get(i).getValue() > remainingCards2.get(i).getValue()) {
                        return 1;
                    } else if (remainingCards2.get(i).getValue() > remainingCards1.get(i).getValue()) {
                        return 2;
                    }
                }
                return 0;
            }
        } else if (hasPair1) {
            return 1;
        } else if (hasPair2) {
            return 2;
        }


        //Done
        ArrayList<Card> highCard1 = PokerHandChecker.HighCardCheck(pokerHand1);
        ArrayList<Card> highCard2 = PokerHandChecker.HighCardCheck(pokerHand2);
        for (int i=0; i<5; i++) {
            if (highCard1.get(i).getValue() > highCard2.get(i).getValue()) {
                return 1;
            } else if (highCard2.get(i).getValue() > highCard1.get(i).getValue()) {
                return 2;
            }
        }

        System.out.println("Shouldn't really get here");
        return 0;



    }



}
