package com.princeton.algs4.chp2.sorting.other;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/18/14
 * Time: 12:02 PM
 */
public class Card  implements Comparable<Card> {

    private Suit suit;
    private Rank rank;

    public enum Suit {

        CLUBS(2),
        SPADES(0),
        DIAMONDS(3),
        HEARTS(1);
        private final int priority;

        private Suit(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }
    public enum Rank {

        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        J(11),
        Q(12),
        K(13),
        A(14);

        public int getPriority() {
            return priority;
        }

        private final int priority;

        private Rank(int priority) {
            this.priority = priority;
        }
    }

    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                "}\n";
    }

    @Override
    public int compareTo(Card o) {

        int i = suit.getPriority();
        int j = o.suit.getPriority();

        if (i < j) { return -1;}
        else if(i > j) {return 1;}
        else {
            i = rank.getPriority();
            j = o.rank.getPriority();

            if (i < j) { return -1;}
            else if(i > j) {return 1;}
            else return 0;
        }
    }

}
