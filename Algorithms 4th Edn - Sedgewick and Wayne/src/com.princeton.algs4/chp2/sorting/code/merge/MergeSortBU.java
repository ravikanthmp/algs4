package com.princeton.algs4.chp2.sorting.code.merge;

import com.princeton.StdRandom;
import com.princeton.Stopwatch;
import com.princeton.algs4.chp2.sorting.code.MergeSort;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/26/14
 * Time: 1:31 PM
 */
public class MergeSortBU {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {

        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz=sz+sz ){
            for (int lo = 0 ; lo < N - sz ; lo+=sz+sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz -1, N - 1));
            }
        }

    }

    private static void merge(Comparable[] a, int lo, int mid, int high) {

        for (int i = lo; i <= high; i++) {
            aux[i] = a[i];
        }

        int j = lo, k = lo, l = mid + 1;
        while (k <= mid && l <= high) {
            if (aux[k].compareTo(aux[l]) <= 0) {
                a[j] = aux[k];
                j++;
                k++;
            } else {
                a[j] = aux[l];
                j++;
                l++;
            }
        }

        if (k < mid) {
            while (k <= mid) {
                a[j++] = aux[k++];
            }
        }

        if (l < high) {
            while (l <= high) {
                a[j++] = aux[l++];
            }
        }


    }


    public static void main(String[] args) {

        String fname = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\words3.txt";

        // String[] strings = In.readStrings(fname);
        int N = 15;
        Comparable[] strings = new Comparable[N];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = new Double(StdRandom.uniform(-50000.0, 50000.0));
        }


        System.out.println(strings.length);

        assert (MergeSort.isSorted(strings) == false);

        MergeSort.display(strings);

        Stopwatch s = new Stopwatch();
      //  MergeSort.optimise = true;
        MergeSortBU.sort(strings);
        System.out.println("Elapsed Time : " + s.elapsedTime());
        assert MergeSort.isSorted(strings);

        MergeSort.display(strings);
        // MergeSort.display(aux);

    }
}
