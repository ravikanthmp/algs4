package com.princeton.algs4.chp2.sorting.other;


import com.princeton.StdRandom;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/18/14
 * Time: 11:59 AM
 */
public class DequeSort {


    public static void sort(LinkedList<Card> a) {

        int N = a.size();
        Card highest = new Card(Card.Suit.CLUBS, Card.Rank.FOUR);

        int timeSinceLastExchange = 0;
        while (timeSinceLastExchange != N) {
            Card top = a.removeFirst();
            Card second = a.removeFirst();

            int cmp = top.compareTo(second);
            int tch = top.compareTo(highest);
            if ((tch == 0) || cmp<0 ){
                a.addFirst(second);
                a.addLast(top);
                if(tch == 0) timeSinceLastExchange++;
            } else if (cmp > 0) {
                a.addFirst(top);
                a.addFirst(second);
                timeSinceLastExchange=0;
            }
        }
    }

    public static void display(LinkedList<Card> a2){

        for (int i = 0; i < a2.size(); i++) {
            System.out.print(a2.get(i) + " ");
        }

        System.out.println();
    }


    public static boolean isSorted(Comparable[] a){
        for (int i = 0; i < a.length - 1; i++) {
            if (less(a[i], a[i+1])){
                return false;
            }
        }
        return true;
    }


    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {

        Card.Suit[] suits = Card.Suit.values();
        Card.Rank[] ranks = Card.Rank.values();
        suits = Arrays.copyOf(suits, 2);
        ranks = Arrays.copyOf(ranks, 4);

        Card[] cards = new Card[8];
        int i = 0;

        for (Card.Suit s : suits){
            for (Card.Rank r : ranks){
                cards[i++] = new Card(s, r);
            }
        }

        StdRandom.shuffle(cards);


        LinkedList<Card> ll = new LinkedList<Card>();
        for(Card c : cards){
            ll.add(c);
        }
        DequeSort.display(ll);
        System.out.println(i);


        DequeSort.sort(ll);
        DequeSort.display(ll);

    }


}
