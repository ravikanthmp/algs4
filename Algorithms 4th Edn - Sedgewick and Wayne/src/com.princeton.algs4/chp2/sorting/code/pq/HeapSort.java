package com.princeton.algs4.chp2.sorting.code.pq;

import com.princeton.StdRandom;
import com.princeton.Stopwatch;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 6/16/14
 * Time: 9:25 PM
 */
public class HeapSort {

    public static void sort(Comparable[] a){
        PriorityQueue pq = new PriorityQueue(a);
        pq.sort();

    }

    public static void sort2(Comparable[] a){
        PriorityQueueNoExchange pq = new PriorityQueueNoExchange(a);
        pq.sort();

    }

    public static void main(String[] args) {

        String fname = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\large.txt";

        // String[] strings = In.readStrings(fname);
        int N = 12;
        Comparable[] strings = new Comparable[N];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = new Double(StdRandom.uniform(-50000.0, 50000.0));
        }


        System.out.println(strings.length);
        Comparable[] strings2 = Arrays.copyOf(strings, strings.length);


        Stopwatch s = new Stopwatch();
        HeapSort.sort2(strings);
        System.out.println("Elapsed Time w/o exchanges: " + s.elapsedTime());

        s = new Stopwatch();
        HeapSort.sort(strings2);
        System.out.println("Elapsed Time with exchanges: " + s.elapsedTime());


        // MergeSort.display(aux);

    }
}
