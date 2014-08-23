package com.princeton.algs4.chp2.sorting.other;

import com.princeton.StdRandom;
import com.princeton.algs4.chp2.sorting.code.ShellSort;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/16/14
 * Time: 9:33 PM
 */
public class DeckSort {


    public static void main(String[] args) {

        Card.Suit[] suits = Card.Suit.values();
        Card.Rank[] ranks = Card.Rank.values();

        Card[] cards = new Card[52];
        int i = 0;

        for (Card.Suit s : suits){
            for (Card.Rank r : ranks){
                cards[i++] = new Card(s, r);
            }
        }

        StdRandom.shuffle(cards);
        ShellSort.display(cards);
        System.out.println(i);
        assert !ShellSort.isSorted(cards);

        ShellSort.sort(cards);
        ShellSort.display(cards);
        assert ShellSort.isSorted(cards);

    }

}
