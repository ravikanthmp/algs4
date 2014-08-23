package com.princeton.algs4.chp2.sorting.code.quick;

import com.princeton.StdRandom;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 5/31/14
 * Time: 4:15 PM
 */
public class QuickSortStack {

    public static void sort(Comparable[] a){
        //StdRandom.shuffle(a);
        Stack<SA> saStack = new Stack();
        saStack.push(new SA(0, a.length-1));
        SA t1, t2;
        int lo,hi;
        while (!saStack.isEmpty()){
            t1 = saStack.pop();
            lo = t1.lo; hi = t1.hi;
            int p = QuickSort.partition(a, lo, hi);
            t1 = new SA(lo, p-1);
            t2 = new SA(p+1, hi);

            if (t1.getSize()>0 && t2.getSize()>0){
                if (t1.getSize()> t2.getSize()){
                    saStack.push(t1);
                    saStack.push(t2);
                }else {
                    saStack.push(t2);
                    saStack.push(t1);
                }
            }else if(t1.getSize() < 0 && t2.getSize()>0){
                saStack.push(t2);
            }else if (t2.getSize() < 0  && t1.getSize()>0){
                saStack.push(t1);
            }

            t1 =null; t2 = null;
        }


    }

    public static void display(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] myArr = new Integer[100000];
        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = StdRandom.uniform(-10000, 10000);
        }
        display(myArr);
        sort(myArr);
        display(myArr);

    }

    static class SA {
        int lo, hi, size;

        int getSize() {
            return size;
        }

        SA(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            this.size = (hi>lo)?hi-lo+1:-1;

        }

        int getLo() {
            return lo;
        }

        void setLo(int lo) {
            this.lo = lo;
        }

        int getHi() {
            return hi;
        }

        void setHi(int hi) {
            this.hi = hi;
        }


}
}