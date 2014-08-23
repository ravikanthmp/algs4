package com.princeton.algs4.chp2.sorting.code;

import com.princeton.In;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/19/14
 * Time: 11:47 AM
 */
public class InsertionXSort {


    public static void sort(Comparable[] a, boolean superOptimised) {

        // sentinel
        int min = 0, j = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[min]) < 0) {
                min = i;
            }
        }

        exch(a, min, 0);

        // half exchanges
        Comparable t1, t2;
        for (int i = 2; i < a.length; i++) {
            j = i;
            t1 = a[j];
            t2 = a[j - 1];
            while (t1.compareTo(t2) < 0) {
                a[j] = t2;
                j--;
                t2 = a[j - 1];
            }
            a[j] = t1;
        }

    }

    public static void sortPrimitives(int[] a) {
        // sentinel
        int min = 0, j = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[min]) {
                min = i;
            }
        }

        j = a[min];
        a[min] = a[0];
        a[0] = j;

        // half exchanges
        int t1,t2;
        for (int i = 2; i < a.length; i++) {
            j = i;
            t1 = a[j];
            t2 = a[j - 1];
            while (t1<t2) {
                a[j] = t2;
                j--;
                t2 = a[j - 1];
            }
            a[j] = t1;
        }

    }

    public static void sort(Comparable[] a) {

        //sentinel
        int min = 0;
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[min])) {
                min = i;
            }
        }
        exch(a, min, 0);

        for (int i = 2; i < a.length; i++) {
            for (int j = i; less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }

    }

    public static void exch(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (!less(a[i], a[i + 1])) {
                return false;
            }
        }

        return true;
    }

    public static void display(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        String fname = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\words3.txt";
        String[] strings = In.readStrings(fname);

        if ((InsertionXSort.isSorted(strings) != false)) {
            throw new AssertionError();
        }

        InsertionXSort.display(strings);

        InsertionXSort.sort(strings, true);

        if (!InsertionXSort.isSorted(strings)) {
            throw new AssertionError();
        }

        InsertionXSort.display(strings);

    }

}
