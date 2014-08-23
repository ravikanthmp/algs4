package com.princeton.algs4.chp2.sorting.other;

import com.princeton.StdRandom;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/26/14
 * Time: 1:50 PM
 */
public class QueueBUSort {

    private static Queue<Queue<Comparable>> mergeQueues(Queue<Queue<Comparable>> q) {
        Queue<Comparable> q1, q2;
        while (q.size() != 1) {
            q1 = q.remove();
            q2 = q.remove();
            q.add(QueueSort.mergeSortedQueue(q1, q2));
        }
        return q;
    }

    public static Queue<Queue<Comparable>> sort(Comparable[] a) {
        Queue ja;
        Queue<Queue<Comparable>> q = new ArrayDeque<Queue<Comparable>>(a.length);
        for (int i = 0; i < a.length; i++) {
            ja = new ArrayDeque<Comparable>(1);
            ja.add(a[i]);
            q.add(ja);
        }

        Queue<Queue<Comparable>> ans = mergeQueues(q);
        return ans;
    }


    public static void main(String[] args) {

        String fname = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\words3.txt";

        // String[] strings = In.readStrings(fname);
        int N = 10000;
        Comparable[] strings = new Comparable[N];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = new Double(StdRandom.uniform(-50000.0, 50000.0));
        }

        System.out.println(strings.length);
        Queue<Queue<Comparable>> q = QueueBUSort.sort(strings);
        System.out.println(q);
    }
}
