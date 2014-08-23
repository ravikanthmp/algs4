package com.princeton.algs4.chp2.sorting.code.pq;

import com.princeton.In;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 7/17/14
 * Time: 8:45 PM
 */
public class MinHeap<Key extends Comparable<Key>> {
    Key[] items = (Key[]) new Comparable[2];
    protected int N;

    public int getCOMPARE() {
        return COMPARE;
    }

    public void setCOMPARE(int COMPARE) {
        this.COMPARE = COMPARE;
    }

    public int getEXCHANGE() {
        return EXCHANGE;
    }

    public void setEXCHANGE(int EXCHANGE) {
        this.EXCHANGE = EXCHANGE;
    }

    int COMPARE = 0, EXCHANGE = 0;


    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key item) {
        if (N == items.length - 1) {
            resize(2 * items.length);
        }

        items[++N] = item;
        swim(N);
    }


    public Key delMin(){
        if (isEmpty()){
            throw new UnsupportedOperationException("Empty heap");
        }
        exch(1, N--);
        Key ans = items[N+1];
        items[N+1] = null;
        if (N == items.length/4){
            resize(items.length/2);
        }

        sink(1);
        return ans;
    }

    private void resize(int newsize) {
        Key[] temp = (Key[]) new Comparable[newsize];
        for (int i = 0; i <= N; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    protected void sink(int i) {
        int ch = 2 * i;
        while (ch <= N) {
            COMPARE++;
            if (ch < N && less(ch + 1, ch)) {
                ch++;
            }
            COMPARE++;
            COMPARE++;
            if (less(ch, i)) {
                exch(ch, i);
            } else break;

            i = ch;
            ch = 2 * i;
        }
    }

    public Key findMin(){
        if (isEmpty()){
            throw new UnsupportedOperationException("Empty min heap");
        }else {
            return items[1];
        }

    }

    protected void swim(int i) {
        while (i > 1 && less(i, i / 2)) {
            COMPARE++;
            exch(i, i / 2);
            i /= 2;
        }
    }

    public void display(){
        for (int i = 1; i<=N; i++){
            System.out.print(items[i] + " ");
        }
    }

    protected boolean less(int i, int j) {
        COMPARE++;
        return items[i].compareTo(items[j]) < 0;
    }

    protected boolean lessOrEqual(int i, int j) {
        return items[i].compareTo(items[j]) <= 0;
    }
    protected void exch(int i, int j) {
        EXCHANGE++;
        Key temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    public boolean verifyMinHeap(){
        for (int i = 1  ; i <= N/2 ; i++) {
           if (!lessOrEqual(i, 2 * i)){
               System.out.println("!@#$ " + items[i] + " and " + items[2*i]);
               display();
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MinHeap<String> pq = new MinHeap<String>();
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
