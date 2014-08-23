package com.princeton.algs4.chp2.sorting.code.pq;

import com.princeton.In;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 7/20/14
 * Time: 4:26 PM
 */
public class MinHeapFastInsert<Key extends Comparable<Key>> extends MinHeap<Key> {

    protected void swim(int i) {
        int index = binsearch(i);
        System.out.println("index targetted " + index + " val " + items[index]);
        while (index < N){
            COMPARE++;
            exch(index, N);
            index*=2;
        }
    }

    private int binsearch(int index) {
        Key x = items[index];
        int lo = 1, hi = index, mid = 1;
        while (lo<=hi) {
            mid = lo + (hi -lo)/2;
            if (x.compareTo(items[mid]) <= 0){
                hi=mid/2;
            }else {
                lo=mid*2;
            }
        }

        return mid;
    }

    public static void main(String[] args) {
        MinHeap<String> pq = new MinHeap<String>();
        String filepath = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\pqminmax";
        for (String s : In.readStrings(filepath)){
            if ("-".equals(s)){

            }else {
                System.out.println("Added " + s);
                pq.insert(s);
            }

        }

        while (!pq.isEmpty()){
            System.out.println(" Min " + pq.delMin());
        }

    }

}
