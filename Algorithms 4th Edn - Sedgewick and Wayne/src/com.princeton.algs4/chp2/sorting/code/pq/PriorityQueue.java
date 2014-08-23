package com.princeton.algs4.chp2.sorting.code.pq;

import com.princeton.In;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 6/16/14
 * Time: 9:12 PM
 */
public class PriorityQueue<Key extends Comparable<Key>> {

    private int N;
    private Key[] items;
    private Key min;

    public Key getMin() {
        return min;
    }

    public PriorityQueue() {
        items = (Key[]) new Comparable[2];
    }

    public PriorityQueue(int n) {
        items = (Key[]) new Comparable[n+1];
    }

    public PriorityQueue(Key[] temp) {
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

    public void add(Key k)  {
        if (N == items.length - 1){
            resize(2*items.length);
        }


        items[++N] = k;
        if (min == null){
            min = k;
        }
        else if(k.compareTo(min) < 0){
            min = k;
        }
        swim(N);
    }

    private void resize(int newsize) {
        Key[] temp = (Key[]) new Comparable[newsize];
        for (int i = 0; i <= N; i++){
            temp[i] = items[i];
        }

        items = temp;
    }

    public Key delMax(){
        if (isEmpty()){
            throw new UnsupportedOperationException("Cant do delMax on an empty PQ");
        }
        exch(1, N);
        Key ans = items[N--];
        items[N+1] = null;

        sink(1);

        if (N > 10 && N == items.length/4){
            resize(items.length/2);
        }
        return ans;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    protected void sink(int nodeIndex){
       int child = 2*nodeIndex;
       while (child <= N){
           if (child < N && less(child ,child+1)) {
               child++;
           }

           if (less(nodeIndex, child)){
               exch(nodeIndex, child);
           }else {
               break;
           }

           nodeIndex = child;
           child*=2;
       }
    }

    protected void swim(int nodeIndex){
        while (nodeIndex > 1 && less(nodeIndex/2, nodeIndex)){
            exch(nodeIndex/2, nodeIndex);
            nodeIndex/=2;
        }
    }

    private void exch(int i, int j){
        Key temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    protected boolean less(int i, int j){
        return items[i].compareTo(items[j]) < 0;
    }


    public static void main(String[] args) {
        PriorityQueue<String> pq  = new PriorityQueue<String>();
        String filepath = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\pqminmax";
        for (String s : In.readStrings(filepath)){
            if ("-".equals(s)){
                System.out.println("Max " + pq.delMax() + " Min " + pq.getMin());
            }else {
                System.out.println("Added " + s);
                pq.add(s);
            }

        }

    }

}
