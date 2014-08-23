package com.princeton.algs4.chp2.sorting.code.pq.creative;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 7/27/14
 * Time: 7:03 PM
 */
public class IndexedMinHeap<Key extends Comparable<Key>> {

    private int N;
    private Map<Integer, Integer> pq;                     // pos - index
    private Map<Integer, Integer> qp;                     // index - pos
    private Key[] items;

    public IndexedMinHeap() {
        items = (Key[]) new Comparable[2];
        pq = new HashMap<Integer, Integer>(2);
        qp = new HashMap<Integer, Integer>(2);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(int index, Key k) {
        if (N == items.length - 1) {
            resize(items.length * 2);
        }
        items[++N] = k;
        pq.put(N, index);
        qp.put(index, N);
        swim(N);
    }

    public int minIndex() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("Can perform delete on an empty Priority Queue");
        }
        if (N == items.length / 4) {
            resize(items.length / 2);
        }

        exch(1, N--);
        items[N + 1] = null;
        int t = pq.get(N + 1);
        pq.remove(N + 1);
        qp.remove(t);
        sink(1);
        return t;

    }

    public int findMin() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("Can perform delete on an empty Priority Queue");
        }
        return pq.get(1);

    }

    public void change(int k, Key newItem) {
        if (!qp.containsKey(k)) {
            throw new UnsupportedOperationException("Cannot find key " + k + " in the Priority Queue");
        } else {
            int i = qp.get(k);
            Key origItem = items[i];
            items[i] = newItem;

            if (less(newItem, origItem)) {
                swim(i);
            } else{
                sink(i);
            }
        }

    }

    private void resize(int newsize) {
        Key[] temp = (Key[]) new Comparable[newsize];
        for (int i = 0; i <= N; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    private void exch(int i, int j) {
        Key temp = items[i];
        items[i] = items[j];
        items[j] = temp;

        int t = pq.get(i);
        pq.put(i, pq.get(j));
        pq.put(j, t);

        qp.put(pq.get(i), i);
        qp.put(pq.get(j), j);

    }

    private boolean less(int i, int j) {
        return less(items[i], items[j]);
    }

    private boolean less(Key i, Key j) {
        return i.compareTo(j) < 0;
    }

    private void sink(int i) {
        int ch = 2 * i;
        while (ch <= N) {

            if (ch<N && less(ch + 1, ch)) {
                ch++;
            }

            if (less(ch, i)) {
                exch(ch, i);
            } else break;

            i = ch;
            ch = 2 * i;
        }
    }

    private void swim(int i) {
        while (i > 1 && (less(i, i/2))) {
            exch(i / 2, i);
            i /= 2;
        }
    }


    public static void main(String[] args) {
        IndexedMinHeap<Integer> imh = new IndexedMinHeap<Integer>();
        imh.insert(2, -1);
        imh.insert(1, 1);
        imh.insert(10, -2);
        imh.insert(23, 43);
        imh.insert(-321, -32);


        System.out.println("Min at index " + imh.minIndex());
    }

}
