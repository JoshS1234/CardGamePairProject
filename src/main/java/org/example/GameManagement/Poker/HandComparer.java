package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;

import java.util.ArrayList;
import java.util.Objects;

public class HandComparer {

    PokerHand pokerHand1;
    PokerHand pokerHand2;

    public HandComparer(PokerHand pokerHand1, PokerHand pokerHand2) {
        this.pokerHand1 = pokerHand1;
        this.pokerHand2 = pokerHand2;
    }

    public int compareTwoHands() {

        //Done
        boolean RoyalFlush1 = (boolean) PokerHandChecker.RoyalFlushCheck(pokerHand1).get(0);
        boolean RoyalFlush2 = (boolean) PokerHandChecker.RoyalFlushCheck(pokerHand2).get(0);
        if (RoyalFlush1 && RoyalFlush2) {
            return 0;
        } else if (RoyalFlush1) {
            return 1;
        } else if (RoyalFlush2) {
            return 2;
        }

        //Done
        boolean StraightFlush1 = (boolean) PokerHandChecker.StraightFlushCheck(pokerHand1).get(0);
        boolean StraightFlush2 = (boolean) PokerHandChecker.StraightFlushCheck(pokerHand2).get(0);
        if (StraightFlush1 && StraightFlush2) {
            int StraightFlushBaseValue1 = (int) PokerHandChecker.StraightFlushCheck(pokerHand1).get(1);
            int StraightFlushBaseValue2 = (int) PokerHandChecker.StraightFlushCheck(pokerHand1).get(1);

            if (StraightFlushBaseValue1 > StraightFlushBaseValue2) {
                return 1;
            } else if (StraightFlushBaseValue2 > StraightFlushBaseValue1) {
                return 2;
            } else {
                System.out.println("draw");
                return 0;
            }
        } else if (StraightFlush1) {
            return 1;
        } else if (StraightFlush2) {
            return 2;
        }

        //Need to update as additional comparison is unnecessary
        boolean FourKind1 = (boolean) PokerHandChecker.FourOfAKindCheck(pokerHand1).get(0);
        boolean FourKind2 = (boolean) PokerHandChecker.FourOfAKindCheck(pokerHand2).get(0);
        if (FourKind1 && FourKind2) {
            int fourLotsOfPlayer1 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand1).get(1);
            int fourLotsOfPlayer2 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand2).get(1);
            if ( fourLotsOfPlayer1>fourLotsOfPlayer2 ) {
                return 1;
            } else if (fourLotsOfPlayer2 > fourLotsOfPlayer1) {
                return 2;
            } else {
                int highCard1 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand1).get(2);
                int highCard2 = (int) PokerHandChecker.FourOfAKindCheck(pokerHand2).get(2);
                if (highCard1 > highCard2 ) {
                    return 1;
                } else if (highCard2>highCard1) {
                    return 2;
                } else {
                    return 0;
                }
            }
        } else if (FourKind1) {
            return 1;
        } else if (FourKind2) {
            return 2;
        }


        //Done
        boolean FullHouse1 = (boolean) PokerHandChecker.FullHouseCheck(pokerHand1).get(0);
        boolean FullHouse2 = (boolean) PokerHandChecker.FullHouseCheck(pokerHand2).get(0);
        if (FullHouse1 && FullHouse2) {
            int threeLotsOfPlayer1 = (int) PokerHandChecker.FullHouseCheck(pokerHand1).get(1);
            int threeLotsOfPlayer2 = (int) PokerHandChecker.FullHouseCheck(pokerHand2).get(1);
            if (threeLotsOfPlayer1 > threeLotsOfPlayer2) {
                return 1;
            } else if (threeLotsOfPlayer2 > threeLotsOfPlayer1) {
                return 2;
            } else {
                int twoLotsOfPlayer1 = (int) PokerHandChecker.FullHouseCheck(pokerHand1).get(2);
                int twoLotsOfPlayer2 = (int) PokerHandChecker.FullHouseCheck(pokerHand2).get(2);
                if (twoLotsOfPlayer1 > twoLotsOfPlayer2) {
                    return 1;
                } else if (twoLotsOfPlayer2 > twoLotsOfPlayer1) {
                    return 2;
                } else {
                    return 0;
                }
            }
        } else if (FullHouse1) {
            return 1;
        } else if (FullHouse2) {
            return 2;
        }

        //Done
        boolean Flush1 = (boolean) PokerHandChecker.FlushCheck(pokerHand1).get(0);
        boolean Flush2 = (boolean) PokerHandChecker.FlushCheck(pokerHand2).get(0);
        if (Flush1 && Flush2) {
            ArrayList<Card> flushCards1 = (ArrayList<Card>) PokerHandChecker.FlushCheck(pokerHand1).get(1);
            ArrayList<Card> flushCards2 = (ArrayList<Card>) PokerHandChecker.FlushCheck(pokerHand2).get(1);

            for (int i=0; i<5; i++) {
                if (flushCards1.get(i).getValue() > flushCards2.get(i).getValue()) {
                    return 1;
                } else if (flushCards2.get(i).getValue() > flushCards1.get(i).getValue()) {
                    return 2;
                }
            }
            return 0;
        } else if (Flush1) {
            return 1;
        } else if (Flush2) {
            return 2;
        }

        //Done
        boolean straight1 = (boolean) PokerHandChecker.StraightCheck(pokerHand1).get(0);
        boolean straight2 = (boolean) PokerHandChecker.StraightCheck(pokerHand2).get(0);
        if (straight1 && straight2) {
            int straightBaseValue1 = (int) PokerHandChecker.StraightCheck(pokerHand1).get(1);
            int straightBaseValue2 = (int) PokerHandChecker.StraightCheck(pokerHand2).get(1);
            if (straightBaseValue1 > straightBaseValue2) {
                return 1;
            } else if (straightBaseValue2 > straightBaseValue1) {
                return 2;
            } else {
                return 0;
            }
        } else if (straight1) {
            return 1;
        } else if (straight2) {
            return 2;
        }

        //Need to update as additional comparison is unnecessary
        boolean ThreeKind1 = (boolean) PokerHandChecker.ThreeOfAKindCheck(pokerHand1).get(0);
        boolean ThreeKind2 = (boolean) PokerHandChecker.ThreeOfAKindCheck(pokerHand2).get(0);
        if (ThreeKind1 && ThreeKind2) {
            Integer threeOfWhichCard1 = (Integer) PokerHandChecker.ThreeOfAKindCheck(pokerHand1).get(1);
            Integer threeOfWhichCard2 = (Integer) PokerHandChecker.ThreeOfAKindCheck(pokerHand2).get(1);
            if (threeOfWhichCard1 > threeOfWhichCard2) {
                return 1;
            } else if (threeOfWhichCard2 > threeOfWhichCard1) {
                return 2;
            } else {
                //realised this was overkill as we couldn't have 2 lots of "three of a kind" that are equal
                ArrayList<Card> remainingCards1 = (ArrayList<Card>) PokerHandChecker.ThreeOfAKindCheck(pokerHand1).get(2);
                ArrayList<Card> remainingCards2 = (ArrayList<Card>) PokerHandChecker.ThreeOfAKindCheck(pokerHand2).get(2);
                System.out.println(remainingCards1);
                System.out.println(remainingCards2);

                for (int i=0; i<2; i++) {
                    if (remainingCards1.get(i).getValue() > remainingCards2.get(i).getValue()) {
                        return 1;
                    } else if (remainingCards2.get(i).getValue() > remainingCards1.get(i).getValue()) {
                        return 2;
                    }
                }
                return 0;
            }
        } else if (ThreeKind1) {
            return 1;
        } else if (ThreeKind2) {
            return 2;
        }


        //Done
        boolean TwoPair1 = (boolean) PokerHandChecker.TwoPairCheck(pokerHand1).get(0);
        boolean TwoPair2 =  (boolean) PokerHandChecker.TwoPairCheck(pokerHand2).get(0);
        if (TwoPair1 && TwoPair2) {
            int higherPair1 = (int) PokerHandChecker.TwoPairCheck(pokerHand1).get(1);
            int higherPair2 = (int) PokerHandChecker.TwoPairCheck(pokerHand2).get(1);
            if (higherPair1 > higherPair2) {
                return 1;
            } else if (higherPair2 > higherPair1) {
                return 2;
            } else {
                int lowerPair1 = (int) PokerHandChecker.TwoPairCheck(pokerHand1).get(2);
                int lowerPair2 = (int) PokerHandChecker.TwoPairCheck(pokerHand2).get(2);
                if (lowerPair1 > lowerPair2) {
                    return 1;
                } else if (lowerPair2 > lowerPair1) {
                    return 2;
                } else {
                    Card finalCard1 = (Card) PokerHandChecker.TwoPairCheck(pokerHand1).get(3);
                    Card finalCard2 = (Card) PokerHandChecker.TwoPairCheck(pokerHand2).get(3);
                    if (finalCard1.getValue() > finalCard2.getValue()) {
                        return 1;
                    } else if (finalCard2.getValue() > finalCard1.getValue()) {
                        return 2;
                    } else {
                        return 0;
                    }
                }
            }
        } else if (TwoPair1) {
            return 1;
        } else if (TwoPair2) {
            return 2;
        }


        //Done
        boolean pair1 = (boolean) PokerHandChecker.PairCheck(pokerHand1).get(0);
        boolean pair2 = (boolean) PokerHandChecker.PairCheck(pokerHand2).get(0);
        if (pair1 && pair2) {
            int pair1value = (int) PokerHandChecker.PairCheck(pokerHand1).get(1);
            int pair2value = (int) PokerHandChecker.PairCheck(pokerHand2).get(1);
            if (pair1value > pair2value) {
                return 1;
            } else if (pair2value > pair1value) {
                return 2;
            } else {
                ArrayList<Card> remainingCards1 = (ArrayList<Card>) PokerHandChecker.PairCheck(pokerHand1).get(2);
                ArrayList<Card> remainingCards2 = (ArrayList<Card>) PokerHandChecker.PairCheck(pokerHand2).get(2);
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
        } else if (pair1) {
            return 1;
        } else if (pair2) {
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
