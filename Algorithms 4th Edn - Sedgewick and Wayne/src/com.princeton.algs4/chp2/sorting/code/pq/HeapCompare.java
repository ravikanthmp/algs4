package com.princeton.algs4.chp2.sorting.code.pq;

import com.princeton.Stopwatch;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 7/20/14
 * Time: 6:09 PM
 */
public class HeapCompare {

    public static void main(String[] args) {

        MinHeap<Integer> minHeap =  new MinHeap<Integer>();
        MinHeapFastInsert<Integer> minHeapFastInsert = new MinHeapFastInsert<Integer>();

        Integer[] ints = new Integer[]{-1, -72, -94, 72, 70, 80, -62, 46, -21, -71};
        for (int i = 0; i < ints.length; i++) {
           // ints[i] = new Integer(StdRandom.uniform(-100, 100));
            System.out.print(ints[i] + " ");
        }


        System.out.println();
        Stopwatch s1 = new Stopwatch();
        for (int i = 0; i < ints.length; i++) {
            minHeap.insert( ints[i]);

        }

        assert minHeap.verifyMinHeap();
//        System.out.println("For normal Min Heap Insertion took " + s1.elapsedTime());
//        System.out.println("For normal Heap Compares " + minHeap.getCOMPARE() + " Exchanges " + minHeap.getEXCHANGE());

        s1 = new Stopwatch();
        for (int i = 0; i < ints.length; i++) {
            minHeapFastInsert.insert( ints[i]);
            minHeapFastInsert.display();
        }
        minHeapFastInsert.display();
        assert minHeapFastInsert.verifyMinHeap();
//        System.out.println("For fast Min Heap Insertion took " + s1.elapsedTime());
//        System.out.println("For fast Heap Compares " + minHeapFastInsert.getCOMPARE() + " Exchanges " + minHeapFastInsert.getEXCHANGE());



        while (!minHeap.isEmpty()){
           minHeap.delMin();
        }
        while (!minHeapFastInsert.isEmpty()){
            minHeapFastInsert.delMin() ;
        }
    }
}
