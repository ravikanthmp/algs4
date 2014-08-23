package com.princeton.algs4.chp2.sorting.code;

import com.princeton.In;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/16/14
 * Time: 9:04 PM
 */
public class InsertionSort {

    public static void sort(Comparable[] a, int lo, int high) {

        if (high <= lo) return;
        int min = lo, i = lo + 1;
        while (i <= high) {
            if (a[i].compareTo(a[min]) < 0) {
                min = i;
            }
            i++;
        }

        exch(a, min, lo);

        for ( i = lo + 1; i <= high; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }


    }

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                }
            }
        }

        assert isSorted(a);
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

        assert (InsertionSort.isSorted(strings) == false);

        InsertionSort.display(strings);

        InsertionSort.sort(strings, 2, 6);

        assert InsertionSort.isSorted(strings);

        InsertionSort.display(strings);

    }
}
