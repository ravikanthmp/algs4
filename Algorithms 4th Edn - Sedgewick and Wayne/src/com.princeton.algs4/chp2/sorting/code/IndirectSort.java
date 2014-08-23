package com.princeton.algs4.chp2.sorting.code;

import com.princeton.StdRandom;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 2/19/14
 * Time: 9:12 PM
 */
public class IndirectSort {

    private static int[] aux, perm;
    private static int ans = 0;

    public static int getAns() {
        return ans;
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        perm = new int[N];
        aux = new int[N];
        for (int i = 0; i < N; i++) {
            perm[i] = i;
        }
        sort(a, 0, N - 1);
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (!less(a[perm[i]], a[perm[i + 1]])) {
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

    public static void displayIndirect(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[perm[i]] + " ");
        }

        System.out.println();
    }

    private static void merge(Comparable[] a, int lo, int mid, int high) {


        for (int i = lo; i <= high; i++) {
            aux[i] = perm[i];
        }

        int j = lo, k = lo, l = mid + 1;
        while (k <= mid && l <= high) {
            if (a[aux[k]].compareTo(a[aux[l]]) <= 0) {
                perm[j++] = aux[k++];
            } else{
                perm[j++] = aux[l++];
            }
        }

        if (k <= mid) {
            while (k <= mid) {
                perm[j++] = aux[k++];
            }
        }

        if (l <= high) {
            while (l <= high) {
                perm[j++] = aux[l++];
            }
        }
    }

    private static void sort(Comparable[] a, int lo, int hi) {

        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args) {
        int N = 100000;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Integer(StdRandom.uniform(-50,50));
        }

        //display(arr);
        sort(arr);
        //displayIndirect(arr);
        assert isSorted(arr);
    }
}
