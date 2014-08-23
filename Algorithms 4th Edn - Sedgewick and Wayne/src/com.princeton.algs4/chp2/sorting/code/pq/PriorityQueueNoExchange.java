package com.princeton.algs4.chp2.sorting.code.pq;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 6/30/14
 * Time: 9:57 PM
 */
public class PriorityQueueNoExchange<Key extends Comparable<Key>>{


    private int N;
    private Key[] items;

    public PriorityQueueNoExchange() {
        items = (Key[]) new Comparable[2];
    }

    public PriorityQueueNoExchange(Key[] temp) {
        items = (Key[]) new Comparable[temp.length+1];
        for (int i = 0; i < temp.length; i++){
            items[i+1] = temp[i];
        }
    }

    public void sort(){
        N = items.length-1;
        for (int i = N/2; i>0; i--){
            sink(i);
        }


        N = items.length-1;
        while (N > 0){
            exch(1, N--);
            sink(1);
        }

        System.out.println("qwerty");
    }

    public void display(){
        for (int i = 1; i < items.length; i++){
            System.out.print(items[i] + " ");
        }
    }

    public void add(Key k){
        items[++N] = k;
        swim(N);
    }

    public Key delMax(){
        if (isEmpty()){
            throw new UnsupportedOperationException("Cant do delMax on an empty PQ");
        }
        exch(1, N);
        Key ans = items[N--];
        items[N+1] = null;
        sink(1);
        return ans;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    protected void sink(int nodeIndex){
        Key temp = items[nodeIndex];

        int child = 2*nodeIndex;
        while (child <= N){
            if (child < N && less(child ,child+1)) {
                child++;
            }

            if (temp.compareTo(items[child]) < 0){
                items[nodeIndex] = items[child];
            }else {
                break;
            }

            nodeIndex = child;
            child*=2;
        }

        items[nodeIndex] = temp;
    }

    protected void swim(int nodeIndex){
        Key temp = items[nodeIndex];

        while (nodeIndex > 1 && items[nodeIndex/2].compareTo(temp) < 0){
           items[nodeIndex] = items[nodeIndex/2];
            nodeIndex/=2;
        }

        items[nodeIndex] = temp;
    }

    private void exch(int i, int j){
        Key temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    protected boolean less(int i, int j){
        return items[i].compareTo(items[j]) < 0;
    }

}
