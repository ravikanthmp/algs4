package com.princeton.algs4.chp2.sorting.code.pq;

import com.princeton.In;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 7/17/14
 * Time: 8:58 PM
 */
public class MaxHeap<Key extends Comparable<Key>> {
    private Key[] items = (Key[]) new Comparable[2];
    private int N;

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void insert(Key key){
        if (N == items.length-1){
            resize(2*items.length);
        }

        items[++N] = key;
        swim(N);
    }


    public Key delMax(){
        if (isEmpty()){
            throw new UnsupportedOperationException("Max Heap is empty");
        }else {
            exch(1, N--);
            Key ans = items[N+1];
            items[N+1] = null;
            if (N == items.length/4){
                resize(items.length/2);
            }

            sink(1);
            return ans;
        }

    }

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    public Key findMax(){
        if (isEmpty()){
            throw new UnsupportedOperationException("Empty max heap");
        }else {
            return items[1];
        }

    }

    private void resize(int newsize) {
        Key[] temp = (Key[]) new Comparable[newsize];
        for (int i = 0; i <= N; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    private void sink(int i){
        int ch = 2*i;
        while (ch <= N){
            if (ch<N && less(ch, ch+1)){
                ch++;
            }

            if (less(i, ch)){
                exch(i, ch);
            }else break;


            i = ch;
            ch*=2;
        }
    }

    private void swim(int i){
        while (i>1 && less(i/2, i)){
            exch(i, i/2);
            i/=2;
        }
    }

    public static void main(String[] args) {
        MaxHeap<String> pq = new MaxHeap<String>();
        String filepath = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\pqminmax";
        for (String s : In.readStrings(filepath)){
            if ("-".equals(s)){
                System.out.println(" Max " + pq.delMax());
            }else {
                System.out.println("Added " + s);
                pq.insert(s);
            }

        }

    }
}
