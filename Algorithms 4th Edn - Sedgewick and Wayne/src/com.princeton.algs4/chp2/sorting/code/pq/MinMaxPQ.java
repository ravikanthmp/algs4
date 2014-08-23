package com.princeton.algs4.chp2.sorting.code.pq;

import com.princeton.In;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 7/6/14
 * Time: 5:55 PM
 */
public class MinMaxPQ<Key extends Comparable<Key>> {

    private Key[] items = (Key[]) new Comparable[2];
    private int N=0;

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key item) {
        if (N == items.length - 1) {
            resize(2 * items.length);
        }


        items[++N] = item;
        int parent = N / 2;
        if (N>1){
            if (isMaxLevel(parent)) {
                if (less(N, parent)) swimMin(N);
                else {
                    exch(N, parent);
                    swimMax(parent);
                }
            } else {
                if (less(parent, N)) {
                    swimMax(N);
                } else {
                    exch(parent, N);
                    swimMin(parent);
                }

            }
        }


    }

    public Key findMin() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("Empty minmax PQ");
        } else {
            return items[1];
        }
    }

    public Key delMin() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("Cant do delmin on an empty minmax PQ");
        } else {
            exch(1, N--);
            Key ans = items[N + 1];
            items[N + 1] = null;
            sinkMin(1);

            return ans;
        }
    }

    public Key delMax() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("Cant do delmax on an empty minmax PQ");
        } else {
            int index;
            if (N == 1) {
                index = 1;
            } else if (N == 2) {
                index = 2;
            } else {
                index = less(2, 3) ? 3 : 2;
            }

            exch(index, N--);
            Key ans = items[N + 1];
            items[N + 1] = null;

            if (isMinLevel(index)){
                sinkMin(index);
            }else {
                sinkMax(index);
            }

            return ans;

        }

    }

    public Key findMax() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("Empty minmax PQ");
        } else {
            if (N == 1) {
                return items[1];
            } else if (N == 2) {
                return items[2];
            } else {
                return less(2, 3) ? items[3] : items[2];
            }
        }
    }

    private void sinkMin(int i) {

        if (4 * i <= N) {
            int min = 4 * i;
            int child = 4 * i;

            for (int j = 0; j < 3 && (child < N && less(child + 1, min)); j++) {
                min = child++;
            }

            if (less(min, i)) {
                exch(min, i);
                if (less(min / 2, min)) {
                    exch(min / 2, min);
                }
                sinkMin(min);
            }
        } else if (2 * i <= N) {
            int ch = 2 * i;
            if (ch < N && less(ch + 1, ch)) {
                ch++;
            }
            if (less(ch, i)) {
                exch(i, ch);
            }
        }

    }

    private void sinkMax(int i) {
        if (4 * i <= N) {
            int max = 4 * i;
            int child = 4 * i;

            for (int j = 0; j < 3 && (child < N && less(max, child + 1)); j++) {
                max = child++;
            }

            if (less(i, max)) {
                exch(max, i);
                if (less(max, max / 2)) {
                    exch(max / 2, max);
                }
                sinkMax(max);
            }
        } else if (2 * i <= N) {
            int ch = 2 * i;
            if (ch < N && less(ch, ch + 1)) {
                ch++;
            }
            if (less(i, ch)) {
                exch(i, ch);
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

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    public void display(){
        for (int i = 1; i <= N; i++) {
            System.out.print(items[i] + " ");
        }
    }

    private void exch(int i, int j) {
        Key temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private boolean isMaxLevel(int i) {
        return ((int)(Math.log(i) / Math.log(2)) % 2) != 0;
    }

    private boolean isMinLevel(int i) {
        return ((int)(Math.log(i) / Math.log(2)) % 2) == 0;
    }

    // i should be min level
    private void swimMin(int i) {
        while (i > 1 && less(i, i / 4)) {
            exch(i, i / 4);
        }
    }

    // i should be max level
    private void swimMax(int i) {
        while (i > 3 && less(i / 4, i)) {
            exch(i / 4, i);
        }
    }


    public static void main(String[] args) {
        MinMaxPQ<String> minmaxpq = new MinMaxPQ<String>();
        String file = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\pqminmax";
        for (String s : In.readStrings(file)){
            if ("-".equals(s)){

            }else {
                minmaxpq.insert(s);
                System.out.println("Inserted " + s + " min: " + minmaxpq.findMin() + " max: "  + minmaxpq.findMax() +  " N " + minmaxpq.size());
            }

        }

        minmaxpq.display();

    }
}


