package com.princeton.algs4.chp2.sorting.code.quick;

import com.princeton.StdRandom;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 4/20/14
 * Time: 6:05 PM
 */
public class QuickSort {

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length -1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a,int lo, int hi) {
        if (hi <= lo){
         return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }


    private static void sortX(Comparable[] a,int lo, int hi) {
        if (hi <= lo){
            return;
        }

        int j = partitionX(a, lo, hi);
        sortX(a, lo, j-1);
        sortX(a, j+1, hi);
    }

    public static void sortX(Comparable[] a){
        StdRandom.shuffle(a);

        int max = 0;
        for (int i = 1; i < a.length; i++){
            if (a[i].compareTo(a[max]) > 0)
                max = i;
        }

        exch(a, max, a.length - 1);

        sortX(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    private static void exch(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int partition(Comparable[] a, int lo, int hi){

        int i = lo, j = hi+1;
        Comparable v = a[lo];

        while (true){

            while (less(a[++i], v)){
                if (i == hi){
                    break;
                }
            }

            while (less(v, a[--j])){
                if (j == lo){
                    break;
                }
            }

            if (i >= j){
                break;
            }

            exch(a, i, j);

        }

        exch(a, lo, j);
        return j;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (!less(a[i], a[i + 1])) {
                return false;
            }
        }

        return true;
    }

    private static int partitionX(Comparable[] a, int lo, int hi){

        int i = lo, j = hi+1;
        Comparable v = a[lo];

        while (true){

            while (less(a[++i], v)){

            }

            while (less(v, a[--j])){

            }

            if (i >= j){
                break;
            }

            exch(a, i, j);

        }

        exch(a, lo, j);
        return j;
    }



    public static void display(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] myArr = new Integer[100];
        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = StdRandom.uniform(-10000, 10000);
        }
        display(myArr);
        QuickSort.sort(myArr);
        display(myArr);

    }





}
