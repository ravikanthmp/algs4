package com.princeton.algs4.chp2.sorting.code.pq.creative;

import com.princeton.StdIn;
import com.princeton.algs4.chp2.sorting.code.pq.MaxHeap;
import com.princeton.algs4.chp2.sorting.code.pq.MinHeap;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 7/16/14
 * Time: 10:26 PM
 */
public class DynamicMedian<Key extends Number & Comparable<Key>> {
    Key m = null;
    private MinHeap<Key> minheap = new MinHeap<Key>();
    private MaxHeap<Key> maxheap = new MaxHeap<Key>();

    public DynamicMedian(Key k) {
        this(k, null);
    }

    public DynamicMedian(Key k1, Key k2) {
        if (k1 == null) {
            minheap.insert(k2);
        } else if (k2 == null) {
            maxheap.insert(k1);
        } else {
            if (less(k1, k2)) {
                minheap.insert(k2);
                maxheap.insert(k1);
            } else {

                minheap.insert(k1);
                maxheap.insert(k2);
            }
        }


    }

    public int size() {
        int ans =  minheap.size() + maxheap.size();
        if (m != null){
           ans++;
        }
        return ans;
    }

    public Double median(){
        if (size() == 1){
            return (Double) maxheap.findMax();
        }else if (size() % 2 == 0){
            return Double.sum((Double)maxheap.findMax(), (Double)minheap.findMin())/2;
        }else {
            return (Double)m;
        }
    }

    public void insert(Key key){
        if (less(key, maxheap.findMax())){
            maxheap.insert(key);
            if (size()%2 != 0){
                Key temp = maxheap.delMax();
                m = temp;
            }else {
                minheap.insert(m);
                m = null;
            }
        }else {
            minheap.insert(key);
            if (size()%2 != 0){
                Key temp = minheap.delMin();
                m = temp;
            }else {
                maxheap.insert(m);
                m = null;
            }
        }
    }

    private boolean less(Key i1, Key i2) {
        return i1.compareTo(i2) < 0;
    }

    public static void main(String[] args) {

        DynamicMedian<Double> dm = new DynamicMedian<Double>(StdIn.readDouble(), StdIn.readDouble());
        while(true){
            System.out.println("Median till now : " + dm.median());
            dm.insert(StdIn.readDouble());
        }
    }
}
