package org.example.GameManagement.Poker;

import org.example.CardSetup.Card;

import java.util.Objects;

public class HandComparer {

    PokerHand pokerHand1;
    PokerHand pokerHand2;

    public HandComparer(PokerHand pokerHand1, PokerHand pokerHand2) {
        this.pokerHand1 = pokerHand1;
        this.pokerHand2 = pokerHand2;
    }

    public int compareTwoHands() {
        boolean RoyalFlush1 = PokerHandChecker.RoyalFlushCheck(pokerHand1);
        boolean RoyalFlush2 = PokerHandChecker.RoyalFlushCheck(pokerHand2);
        if (RoyalFlush1 && RoyalFlush2) {
            return 0;
        } else if (RoyalFlush1) {
            return 1;
        } else if (RoyalFlush2) {
            return 2;
        }

        boolean StraightFlush1 = PokerHandChecker.StraightFlushCheck(pokerHand1);
        boolean StraightFlush2 = PokerHandChecker.StraightFlushCheck(pokerHand2);
        if (StraightFlush1 && StraightFlush2) {
            //Need to compare
            return 0;
        } else if (StraightFlush1) {
            return 1;
        } else if (StraightFlush2) {
            return 2;
        }

        boolean FourKind1 = PokerHandChecker.FourOfAKindCheck(pokerHand1);
        boolean FourKind2 = PokerHandChecker.FourOfAKindCheck(pokerHand2);
        if (FourKind1 && FourKind2) {
            //Need to compare
            return 0;
        } else if (FourKind1) {
            return 1;
        } else if (FourKind2) {
            return 2;
        }

        boolean FullHouse1 = PokerHandChecker.FullHouseCheck(pokerHand1);
        boolean FullHouse2 = PokerHandChecker.FullHouseCheck(pokerHand2);
        if (FullHouse1 && FullHouse2) {
            //Need to compare
            return 0;
        } else if (FullHouse1) {
            return 1;
        } else if (FullHouse2) {
            return 2;
        }

        boolean Flush1 = PokerHandChecker.FlushCheck(pokerHand1);
        boolean Flush2 = PokerHandChecker.FlushCheck(pokerHand2);
        if (Flush1 && Flush2) {
            //Need to compare
            return 0;
        } else if (Flush1) {
            return 1;
        } else if (Flush2) {
            return 2;
        }

        boolean Straight1 = PokerHandChecker.StraightCheck(pokerHand1);
        boolean Straight2 = PokerHandChecker.StraightCheck(pokerHand2);
        if (Straight1 && Straight2) {
            //Need to compare
            return 0;
        } else if (Straight1) {
            return 1;
        } else if (Straight2) {
            return 2;
        }

        boolean ThreeKind1 = PokerHandChecker.ThreeOfAKindCheck(pokerHand1);
        boolean ThreeKind2 = PokerHandChecker.ThreeOfAKindCheck(pokerHand2);
        if (ThreeKind1 && ThreeKind2) {
            //Need to compare
            return 0;
        } else if (ThreeKind1) {
            return 1;
        } else if (ThreeKind2) {
            return 2;
        }

        boolean TwoPair1 = PokerHandChecker.TwoPairCheck(pokerHand1);
        boolean TwoPair2 = PokerHandChecker.TwoPairCheck(pokerHand2);
        if (TwoPair1 && TwoPair2) {
            //Need to compare
            return 0;
        } else if (TwoPair1) {
            return 1;
        } else if (TwoPair2) {
            return 2;
        }

        boolean pair1 = PokerHandChecker.PairCheck(pokerHand1);
        boolean pair2 = PokerHandChecker.PairCheck(pokerHand2);
        if (pair1 && pair2) {
            //Need to compare
            return 0;
        } else if (pair1) {
            return 1;
        } else if (pair2) {
            return 2;
        }

        Card highCard1 = PokerHandChecker.HighCardCheck(pokerHand1);
        Card highCard2 = PokerHandChecker.HighCardCheck(pokerHand2);
        if (Objects.equals(highCard1.getValue(), highCard2.getValue())) {
            //Need to compare
            return 0;
        } else if (highCard1.getValue() > highCard2.getValue()) {
            return 1;
        } else if (highCard2.getValue() > highCard1.getValue()) {
            return 2;
        }

        System.out.println("Made it to the end");
        return 0;

    }



}
