package com.princeton.algs4.chp2.sorting.code.applications;

import com.princeton.In;
import com.princeton.algs4.chp2.sorting.code.pq.MinHeap;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 8/9/14
 * Time: 7:03 PM
 */
public class MinHeapStable<Key extends  Comparable<Key>> extends MinHeap{

    protected void sink(int i) {
        int ch = 2 * i;
        while (ch <= N) {
            if (ch < N && less(ch + 1, ch)) {
                ch++;
            }
            if (less(ch, i)) {
                exch(ch, i);
            } else break;

            i = ch;
            ch = 2 * i;
        }
    }
    public static void main(String[] args) {
        MinHeapStable<String> pq = new MinHeapStable<String>();
        String filepath = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\pqminmax";
        for (String s : In.readStrings(filepath)){
            if ("-".equals(s)){
                System.out.println(" Min " + pq.delMin());
            }else {
                System.out.println("Added " + s);
                pq.insert(s);
            }

        }

    }
}
