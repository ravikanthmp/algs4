package com.princeton.algs4.chp2.sorting.other;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/25/14
 * Time: 9:19 PM
 */
public class QueueSort {

    public static Queue<Comparable> mergeSortedQueue(Queue<Comparable> q1, Queue<Comparable> q2) {

        int q1N = q1.size();
        int q2N = q2.size();
        Comparable q1f, q2f ;
        while (q1N != 0 && q2N != 0) {
            q1f = q1.peek();
            q2f = q2.peek();
            if (q1f.compareTo(q2f) <= 0) {
                q1.add(q1.remove());
                q1N--;
            } else {
                q1.add(q2.remove());
                q2N--;
            }
        }

        if (q1N!=0){
            while (q1N!=0){
                q1.add(q1.remove());
                q1N--;
            }
        }

        if (q2N!=0){
            while (q2N!=0){
                q1.add(q2.remove());
                q2N--;
            }
        }

        return q1;
    }

    public static void main(String[] args) {
        Queue<Comparable> q1= new ArrayDeque();
        Queue<Comparable> q2 = new ArrayDeque();

        q1.add(2.0);
        q1.add(3.3);
        q1.add(3.4);
        q1.add(7.8);
        q1.add(56.3);

        q2.add(1.2);
        q2.add(5.5);
        q2.add(66.9);
        q2.add(324.6);

        //QueueSort.mergeSortedQueue(q1, q2);
        Queue<Comparable> q = QueueSort.mergeSortedQueue(q1, q2);

        q1 = null;
        q2 = null;

        System.out.println(q);
    }

}
